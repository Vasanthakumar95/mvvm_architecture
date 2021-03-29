package com.example.mvvm_learning.repository.remote_db.teacher;

import com.example.mvvm_learning.api_request.teacher.TeacherAttendanceApiRequest;
import com.example.mvvm_learning.models.remote_db_response.teacher.teacher_get_class_att;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TeacherAttendanceRepository {

    private static TeacherAttendanceRepository instance;

    private TeacherAttendanceApiRequest mTeacherAttendanceApiRequest;

    public static TeacherAttendanceRepository getInstance() {
        if(instance == null){
            instance = new TeacherAttendanceRepository();
        }
        return instance;
    }

    private TeacherAttendanceRepository()
    {
        mTeacherAttendanceApiRequest = TeacherAttendanceApiRequest.getInstance();
    }

    public MutableLiveData<List<teacher_get_class_att>> getAtt_list() {
        return mTeacherAttendanceApiRequest.getAtt_list();
    }

    public void teacherattendanceApi(String username)
    {
        mTeacherAttendanceApiRequest.teacherattendanceApi(username);
    }


}
