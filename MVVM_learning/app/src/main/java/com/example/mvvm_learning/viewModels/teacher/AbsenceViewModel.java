package com.example.mvvm_learning.viewModels.teacher;

import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_absence;
import com.example.mvvm_learning.repository.remote_db.teacher.TeacherAbsenceRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AbsenceViewModel extends ViewModel {

    private TeacherAbsenceRepository mTeacherAbsenceRepository;


    public AbsenceViewModel() {
        mTeacherAbsenceRepository = TeacherAbsenceRepository.getInstance();
        mTeacherAbsenceRepository.teacherhomeclassabsenceApi("1", "2020-10-01");
    }

    public MutableLiveData<List<teacher_absence>> getAbb_list() {
        return mTeacherAbsenceRepository.getAbb_list();
    }


}