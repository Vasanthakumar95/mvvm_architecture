package com.example.mvvm_learning.models.remote_db_response.teacher;

import java.util.ArrayList;

public class teacher_absence_response {

    private boolean error;

    private ArrayList<teacher_absence> message;

    public teacher_absence_response(boolean error, ArrayList<teacher_absence> message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public ArrayList<teacher_absence> getMessage() {
        return message;
    }
}
