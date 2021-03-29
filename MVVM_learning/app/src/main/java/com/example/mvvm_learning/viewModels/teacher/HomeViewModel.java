package com.example.mvvm_learning.viewModels.teacher;


import com.example.mvvm_learning.repository.remote_db.teacher.TeacherHomeRepository;
import com.example.mvvm_learning.ui.teacher.HomeFragment;

import java.util.Objects;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private TeacherHomeRepository mTeacherHomeRepository;

    public HomeViewModel() {
        mTeacherHomeRepository = TeacherHomeRepository.getInstance();
        mTeacherHomeRepository.teacherhomeApi("THR0001");
    }

    public LiveData<String> getCurrent_date()
    {
        return mTeacherHomeRepository.getCurrent_date();
    }

    public LiveData<String> getCurrent_day()
    {
        return mTeacherHomeRepository.getCurrent_day();
    }

    public MutableLiveData<String> getTeacher_name() {
        return mTeacherHomeRepository.getTeacher_name();
    }

    public MutableLiveData<String> getTeacher_class() {
        return mTeacherHomeRepository.getTeacher_class();
    }

    public MutableLiveData<String> getTeacher_class_form() {
        return mTeacherHomeRepository.getTeacher_class_form();
    }

    public MutableLiveData<String> getClass_total_student() {
        return mTeacherHomeRepository.getClass_total_student();
    }

    public MutableLiveData<String> getClass_total_attend() {
        return mTeacherHomeRepository.getClass_total_attend();
    }

    public MutableLiveData<String> getClass_total_absence() {
        return mTeacherHomeRepository.getClass_total_absence();
    }


}