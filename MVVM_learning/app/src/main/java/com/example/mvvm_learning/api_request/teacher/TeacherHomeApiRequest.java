package com.example.mvvm_learning.api_request.teacher;

import android.util.Log;

import com.example.mvvm_learning.Api.RetrofitClient;
import com.example.mvvm_learning.AppExecutors;
import com.example.mvvm_learning.api_request.LoginApiRequest;
import com.example.mvvm_learning.models.remote_db_response.default_response;
import com.example.mvvm_learning.models.remote_db_response.login_response;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_absence_response;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_data;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_get_class_att_response;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_response;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherHomeApiRequest {

    private static TeacherHomeApiRequest instance;
    private RetrieveTeacherDataRunnable mRetrieveTeacherDataRunnable;
    private RetrieveTeacherClassDataRunnable mRetrieveTeacherClassDataRunnable;
    private RetrieveTeacherClassAttendanceRunnable mRetrieveTeacherClassAttendanceRunnable;
    private RetrieveTeacherClassAbsenceRunnable mRetrieveTeacherClassAbsenceRunnable;

    private MutableLiveData<String> current_date;
    private MutableLiveData<String> current_day;
    private MutableLiveData<String> teacher_name;
    private MutableLiveData<String> teacher_class;
    private MutableLiveData<String> teacher_class_id;
    private MutableLiveData<String> teacher_class_form;
    private MutableLiveData<String> class_total_student;
    private MutableLiveData<String> class_total_attend;
    private MutableLiveData<String> class_total_absence;



    public static TeacherHomeApiRequest getInstance()
    {
        if(instance == null){
            instance = new TeacherHomeApiRequest();
        }
        return instance;
    }

    private TeacherHomeApiRequest()
    {
        current_date = new MutableLiveData<>();
        current_day = new MutableLiveData<>();
        teacher_name = new MutableLiveData<>();
        teacher_class = new MutableLiveData<>();
        teacher_class_id = new MutableLiveData<>();
        teacher_class_form = new MutableLiveData<>();
        class_total_student = new MutableLiveData<>();
        class_total_attend = new MutableLiveData<>();
        class_total_absence = new MutableLiveData<>();
    }

    public MutableLiveData<String> getCurrent_date() {
        get_current_date();
        return current_date;
    }

    public MutableLiveData<String> getCurrent_day() {
        get_current_day();
        return current_day;
    }

    public MutableLiveData<String> getTeacher_name() {
        return teacher_name;
    }

    public MutableLiveData<String> getTeacher_class() {
        return teacher_class;
    }

    public MutableLiveData<String> getTeacher_class_form() {
        return teacher_class_form;
    }

    public MutableLiveData<String> getClass_total_student() {
        return class_total_student;
    }

    public MutableLiveData<String> getClass_total_attend() {
        return class_total_attend;
    }

    public MutableLiveData<String> getClass_total_absence() {
        return class_total_absence;
    }

    private void get_current_day()
    {
        String weekDay;
        SimpleDateFormat dayFormat = new SimpleDateFormat("EEEE", Locale.US);
        Calendar calendar = Calendar.getInstance();
        weekDay = dayFormat.format(calendar.getTime());
        current_day.postValue(weekDay);
    }

    private void get_current_date()
    {

        //        Date Formats :
//                "yyyy.MM.dd G 'at' HH:mm:ss z" ---- 2001.07.04 AD at 12:08:56 PDT
//                "hh 'o''clock' a, zzzz" ----------- 12 o'clock PM, Pacific Daylight Time
//                "EEE, d MMM yyyy HH:mm:ss Z"------- Wed, 4 Jul 2001 12:08:56 -0700
//                "yyyy-MM-dd'T'HH:mm:ss.SSSZ"------- 2001-07-04T12:08:56.235-0700
//                "yyMMddHHmmssZ"-------------------- 010704120856-0700
//                "K:mm a, z" ----------------------- 0:08 PM, PDT
//                "h:mm a" -------------------------- 12:08 PM
//                "EEE, MMM d, ''yy" ---------------- Wed, Jul 4, '01
        String date;
        DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm:ss", Locale.getDefault());
        date = df.format(Calendar.getInstance().getTime());
        current_date.postValue(date);
    }












    // this method that will be called through the classes
    public void teacherhomeApi(String username)
    {
        if(mRetrieveTeacherDataRunnable != null)
        {
            mRetrieveTeacherDataRunnable = null;
        }

        mRetrieveTeacherDataRunnable = new TeacherHomeApiRequest.RetrieveTeacherDataRunnable(username);

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

                            teacher_name.postValue(response.body().getMessage().get(0).getT_name());
                            teacher_class.postValue(response.body().getMessage().get(0).getC_name());
                            teacher_class_form.postValue(response.body().getMessage().get(0).getForm());
                            teacher_class_id.setValue(response.body().getMessage().get(0).getC_id());

                            teacherhomeclassdataApi(teacher_class_id.getValue());


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














    public void teacherhomeclassdataApi(String class_id)
    {
        if(mRetrieveTeacherClassDataRunnable != null)
        {
            mRetrieveTeacherClassDataRunnable = null;
        }

        mRetrieveTeacherClassDataRunnable = new TeacherHomeApiRequest.RetrieveTeacherClassDataRunnable(class_id);

        final Future myHandler = AppExecutors.getInstance().getmNetworkIO().submit(mRetrieveTeacherClassDataRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 5000, TimeUnit.MILLISECONDS);

    }
    //retrieve data from RestAPI by runnable class
    private class RetrieveTeacherClassDataRunnable implements Runnable {
        private String class_id;
        boolean cancelRequest;

        public RetrieveTeacherClassDataRunnable(String class_id) {
            this.class_id = class_id;
        }

        @Override
        public void run() {
            try {
                Call<default_response> call = RetrofitClient.getmInstance().getApi().getTeacher_Class_Total_Student(class_id);
                call.enqueue(new Callback<default_response>() {
                    @Override
                    public void onResponse(Call<default_response> call, Response<default_response> response) {
                        if (response.isSuccessful()) {
                            class_total_student.setValue(response.body().getMessage());
                            teacherhomeclassattendanceApi(teacher_class_id.getValue(), "2020-10-01");
                        } else {
                            Log.v("LoginResponse", "response not sucessful ");
                        }
                    }

                    @Override
                    public void onFailure(Call<default_response> call, Throwable t) {

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



    public void teacherhomeclassattendanceApi(String class_id, String date)
    {
        if(mRetrieveTeacherClassAttendanceRunnable != null)
        {
            mRetrieveTeacherClassAttendanceRunnable = null;
        }

        mRetrieveTeacherClassAttendanceRunnable = new TeacherHomeApiRequest.RetrieveTeacherClassAttendanceRunnable(class_id, date);

        final Future myHandler = AppExecutors.getInstance().getmNetworkIO().submit(mRetrieveTeacherClassAttendanceRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 5000, TimeUnit.MILLISECONDS);

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
                        if (response.isSuccessful()) {
                            class_total_attend.postValue(String.valueOf(response.body().getMessage().size()));
                            teacherhomeclassabsenceApi(class_id, "2020-10-01");
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













    public void teacherhomeclassabsenceApi(String class_id, String date)
    {
        if(mRetrieveTeacherClassAbsenceRunnable != null)
        {
            mRetrieveTeacherClassAbsenceRunnable = null;
        }

        mRetrieveTeacherClassAbsenceRunnable = new TeacherHomeApiRequest.RetrieveTeacherClassAbsenceRunnable(class_id, date);

        final Future myHandler = AppExecutors.getInstance().getmNetworkIO().submit(mRetrieveTeacherClassAbsenceRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 5000, TimeUnit.MILLISECONDS);

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
                        if (response.isSuccessful()) {
                            class_total_absence.postValue(String.valueOf(response.body().getMessage().size()));
                        } else {
                            Log.v("LoginResponse", "response not sucessful ");
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
