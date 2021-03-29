package com.example.mvvm_learning.repository.remote_db.teacher;


import com.example.mvvm_learning.api_request.teacher.TeacherAbsenceApiRequest;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_absence;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class TeacherAbsenceRepository {

    private static TeacherAbsenceRepository instance;

    private TeacherAbsenceApiRequest mTeacherAbsenceApiRequest;

    public static TeacherAbsenceRepository getInstance() {
        if(instance == null){
            instance = new TeacherAbsenceRepository();
        }
        return instance;
    }

    public TeacherAbsenceRepository()
    {
        mTeacherAbsenceApiRequest = TeacherAbsenceApiRequest.getInstance();
    }

    public MutableLiveData<List<teacher_absence>> getAbb_list() {
        return mTeacherAbsenceApiRequest.getAbb_list();
    }

    public void teacherhomeclassabsenceApi(String class_id, String date)
    {
        mTeacherAbsenceApiRequest.teacherhomeclassabsenceApi(class_id,date);
    }

}
