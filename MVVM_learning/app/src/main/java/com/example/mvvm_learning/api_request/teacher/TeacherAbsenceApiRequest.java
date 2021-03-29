package com.example.mvvm_learning.api_request.teacher;

import android.util.Log;

import com.example.mvvm_learning.Api.RetrofitClient;
import com.example.mvvm_learning.AppExecutors;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_absence;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_absence_response;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_get_class_att;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_get_class_att_response;
import com.example.mvvm_learning.repository.remote_db.teacher.TeacherAbsenceRepository;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherAbsenceApiRequest {


    private static TeacherAbsenceApiRequest instance;
    private RetrieveTeacherClassAbsenceRunnable mRetrieveTeacherClassAbsenceRunnable;

    private MutableLiveData<String> teacher_class_id;
    private MutableLiveData<List<teacher_absence>> abb_list;


    public static TeacherAbsenceApiRequest getInstance() {
        if(instance == null){
            instance = new TeacherAbsenceApiRequest();
        }
        return instance;
    }


    private TeacherAbsenceApiRequest()
    {
        abb_list = new MutableLiveData<>();

    }

    public MutableLiveData<List<teacher_absence>> getAbb_list() {
        return abb_list;
    }





    public void teacherhomeclassabsenceApi(String class_id, String date)
    {
        if(mRetrieveTeacherClassAbsenceRunnable != null)
        {
            mRetrieveTeacherClassAbsenceRunnable = null;
        }

        mRetrieveTeacherClassAbsenceRunnable = new TeacherAbsenceApiRequest.RetrieveTeacherClassAbsenceRunnable(class_id, date);

        final Future myHandler = AppExecutors.getInstance().getmNetworkIO().submit(mRetrieveTeacherClassAbsenceRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 6000, TimeUnit.MILLISECONDS);

    }

    //retrieve data from RestAPI by runnable class
    private class RetrieveTeacherClassAbsenceRunnable implements Runnable {
        private String class_id, date;
        boolean cancelRequest;

        public RetrieveTeacherClassAbsenceRunnable(String class_id, String date) {
            this.class_id = class_id;
            this.date = date;
        }

        @Override
        public void run() {
            try {
                Call<teacher_absence_response> call = RetrofitClient.getmInstance().getApi().getTeacher_absent_student(class_id, date);
                call.enqueue(new Callback<teacher_absence_response>() {
                    @Override
                    public void onResponse(Call<teacher_absence_response> call, Response<teacher_absence_response> response) {
                        if (response.isSuccessful() && !response.body().getMessage().isEmpty()) {
                            abb_list.postValue(response.body().getMessage());
                        } else {
                            Log.v("LoginResponse", "response not sucessful");
                        }
                    }

                    @Override
                    public void onFailure(Call<teacher_absence_response> call, Throwable t) {

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
