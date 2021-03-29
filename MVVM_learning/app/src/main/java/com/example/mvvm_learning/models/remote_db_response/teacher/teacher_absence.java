package com.example.mvvm_learning.models.remote_db_response.teacher;

public class teacher_absence {


    private String s_id, s_name, p_id;

    public teacher_absence(String s_id, String s_name, String p_id) {
        this.s_id = s_id;
        this.s_name = s_name;
        this.p_id = p_id;
    }

    public String getS_id() {
        return s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public String getP_id() {
        return p_id;
    }
}
