package com.example.mvvm_learning.models.remote_db_response.teacher;

import java.util.ArrayList;

public class teacher_get_class_att_response {

    private boolean error;

    private ArrayList<teacher_get_class_att> message;

    public teacher_get_class_att_response(boolean error, ArrayList<teacher_get_class_att> message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public ArrayList<teacher_get_class_att> getMessage() {
        return message;
    }
}
