package com.example.mvvm_learning.viewModels.teacher;

import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_get_class_att;
import com.example.mvvm_learning.repository.remote_db.teacher.TeacherAttendanceRepository;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AttendanceViewModel extends ViewModel {

    private TeacherAttendanceRepository mTeacherAttendanceRepository;

    public AttendanceViewModel() {
        mTeacherAttendanceRepository = TeacherAttendanceRepository.getInstance();
        mTeacherAttendanceRepository.teacherattendanceApi("THR0001");
    }

    public MutableLiveData<List<teacher_get_class_att>> getAtt_list() {
        return mTeacherAttendanceRepository.getAtt_list();
    }



}