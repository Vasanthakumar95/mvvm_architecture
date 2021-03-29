package com.example.mvvm_learning.models.remote_db_response.teacher;


import java.util.ArrayList;

public class teacher_response {

    private boolean error;

        //arraylist - working
    private ArrayList<teacher_data> message;

    public teacher_response(boolean error, ArrayList<teacher_data> message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public ArrayList<teacher_data> getMessage() {
        return message;
    }
}
