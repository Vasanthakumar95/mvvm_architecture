package com.example.mvvm_learning.models.remote_db_response.teacher;

public class teacher_data {
//      private ArrayList data_array;
//
//    public parents(ArrayList data_array) {
//        this.data_array = data_array;
//    }
//
//    public ArrayList getData_array() {
//        return data_array;
//    }

    private String t_id, t_name, c_id, c_name, form, max_students;

    public teacher_data(String t_id, String t_name, String c_id, String c_name, String form, String max_students) {
        this.t_id = t_id;
        this.t_name = t_name;
        this.c_id = c_id;
        this.c_name = c_name;
        this.form = form;
        this.max_students = max_students;
    }

    public String getT_id() {
        return t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public String getC_id() {
        return c_id;
    }

    public String getC_name() {
        return c_name;
    }

    public String getForm() {
        return form;
    }

    public String getMax_students() {
        return max_students;
    }
}



