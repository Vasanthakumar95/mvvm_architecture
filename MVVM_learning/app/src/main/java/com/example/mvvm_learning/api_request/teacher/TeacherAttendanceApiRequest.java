package com.example.mvvm_learning.api_request.teacher;

import android.util.Log;

import com.example.mvvm_learning.Api.RetrofitClient;
import com.example.mvvm_learning.AppExecutors;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_get_class_att;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_get_class_att_response;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_response;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherAttendanceApiRequest {

    private static TeacherAttendanceApiRequest instance;
    private RetrieveTeacherDataRunnable mRetrieveTeacherDataRunnable;
    private RetrieveTeacherClassAttendanceRunnable mRetrieveTeacherClassAttendanceRunnable;

    private MutableLiveData<String> teacher_class_id;
    private MutableLiveData<List<teacher_get_class_att>> att_list;



    public static TeacherAttendanceApiRequest getInstance()
    {
        if(instance == null){
            instance = new TeacherAttendanceApiRequest();
        }
        return instance;
    }

    private TeacherAttendanceApiRequest()
    {
        teacher_class_id = new MutableLiveData<>();
        att_list = new MutableLiveData<>();
    }


    public MutableLiveData<List<teacher_get_class_att>> getAtt_list() {
        return att_list;
    }








    // this method that will be called through the classes
    public void teacherattendanceApi(String username)
    {
        if(mRetrieveTeacherDataRunnable != null)
        {
            mRetrieveTeacherDataRunnable = null;
        }

        mRetrieveTeacherDataRunnable = new TeacherAttendanceApiRequest.RetrieveTeacherDataRunnable(username);

        final Future myHandler = AppExecutors.getInstance().getmNetworkIO().submit(mRetrieveTeacherDataRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);

    }

    //retrieve data from RestAPI by runnable class
    private class RetrieveTeacherDataRunnable implements Runnable
    {
        private String username;
        boolean cancelRequest;

        public RetrieveTeacherDataRunnable(String username) {
            this.username = username;
        }

        @Override
        public void run() {
            try
            {
                Call<teacher_response> call = RetrofitClient.getmInstance().getApi().getTeacher_Class_info(username);
                call.enqueue(new Callback<teacher_response>() {
                    @Override
                    public void onResponse(Call<teacher_response> call, Response<teacher_response> response) {
                        if(response.isSuccessful())
                        {
                            teacher_class_id.setValue(response.body().getMessage().get(0).getC_id());
                            teacherhomeclassattendanceApi(teacher_class_id.getValue(), "2020-10-01");
                            Log.v("LoginResponse", teacher_class_id.getValue());

                        }else
                        {
//                            Log.v("LoginResponse", "response not sucessful ");
                        }
                    }

                    @Override
                    public void onFailure(Call<teacher_response> call, Throwable t) {

                    }
                });

            }catch (Exception e) {
                e.printStackTrace();
                cancelRequest();
            }

        }

        private void cancelRequest()
        {
            Log.v("TAG", "Canselling Search Request");
            cancelRequest = true;
        }

    }









    public void teacherhomeclassattendanceApi(String class_id, String date)
    {
        if(mRetrieveTeacherClassAttendanceRunnable != null)
        {
            mRetrieveTeacherClassAttendanceRunnable = null;
        }

        mRetrieveTeacherClassAttendanceRunnable = new TeacherAttendanceApiRequest.RetrieveTeacherClassAttendanceRunnable(class_id, date);

        final Future myHandler = AppExecutors.getInstance().getmNetworkIO().submit(mRetrieveTeacherClassAttendanceRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 6000, TimeUnit.MILLISECONDS);

    }

    //retrieve data from RestAPI by runnable class
    private class RetrieveTeacherClassAttendanceRunnable implements Runnable {
        private String class_id, date;
        boolean cancelRequest;

        public RetrieveTeacherClassAttendanceRunnable(String class_id, String date) {
            this.class_id = class_id;
            this.date = date;
        }

        @Override
        public void run() {
            try {
                Call<teacher_get_class_att_response> call = RetrofitClient.getmInstance().getApi().getTeacher_Class_Att(class_id, date);
                call.enqueue(new Callback<teacher_get_class_att_response>() {
                    @Override
                    public void onResponse(Call<teacher_get_class_att_response> call, Response<teacher_get_class_att_response> response) {
                        if (response.isSuccessful() && !response.body().getMessage().isEmpty()) {
                            att_list.postValue(response.body().getMessage());
                        } else {
                            Log.v("LoginResponse", "response not sucessful ");
                        }
                    }

                    @Override
                    public void onFailure(Call<teacher_get_class_att_response> call, Throwable t) {

                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
                cancelRequest();
            }

        }

        private void cancelRequest() {
            Log.v("TAG", "Canselling Search Request");
            cancelRequest = true;
        }
    }




}
