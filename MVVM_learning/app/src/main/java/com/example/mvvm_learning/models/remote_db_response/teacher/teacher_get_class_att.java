package com.example.mvvm_learning.models.remote_db_response.teacher;

public class teacher_get_class_att {

    private String s_id, s_name, a_date, a_time, c_id, c_name, c_form;

    public teacher_get_class_att(String s_id, String s_name, String a_date, String a_time, String c_id, String c_name, String c_form) {
        this.s_id = s_id;
        this.s_name = s_name;
        this.a_date = a_date;
        this.a_time = a_time;
        this.c_id = c_id;
        this.c_name = c_name;
        this.c_form = c_form;
    }

    public String getS_id() {
        return s_id;
    }

    public String getS_name() {
        return s_name;
    }

    public String getA_date() {
        return a_date;
    }

    public String getA_time() {
        return a_time;
    }

    public String getC_id() {
        return c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public String getC_form() {
        return c_form;
    }
}
