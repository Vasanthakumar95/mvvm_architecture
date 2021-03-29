package com.example.mvvm_learning.repository.remote_db.teacher;

import com.example.mvvm_learning.api_request.teacher.TeacherHomeApiRequest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TeacherHomeRepository {

    private static TeacherHomeRepository instance;

    private TeacherHomeApiRequest mTeacherHomeApiRequest;

    public static TeacherHomeRepository getInstance()
    {
        if(instance == null){
            instance = new TeacherHomeRepository();
        }
        return instance;
    }

    private TeacherHomeRepository()
    {
        mTeacherHomeApiRequest = TeacherHomeApiRequest.getInstance();
    }

    public LiveData<String> getCurrent_date()
    {
        return mTeacherHomeApiRequest.getCurrent_date();
    }

    public LiveData<String> getCurrent_day()
    {
        return mTeacherHomeApiRequest.getCurrent_day();
    }

    public MutableLiveData<String> getTeacher_name() {
        return mTeacherHomeApiRequest.getTeacher_name();
    }

    public MutableLiveData<String> getTeacher_class() {
        return mTeacherHomeApiRequest.getTeacher_class();
    }

    public MutableLiveData<String> getTeacher_class_form() {
        return mTeacherHomeApiRequest.getTeacher_class_form();
    }

    public MutableLiveData<String> getClass_total_student() {
        return mTeacherHomeApiRequest.getClass_total_student();
    }

    public MutableLiveData<String> getClass_total_attend() {
        return mTeacherHomeApiRequest.getClass_total_attend();
    }

    public MutableLiveData<String> getClass_total_absence() {
        return mTeacherHomeApiRequest.getClass_total_absence();
    }

    public void teacherhomeApi(String username)
    {
        mTeacherHomeApiRequest.teacherhomeApi(username);
    }

}
