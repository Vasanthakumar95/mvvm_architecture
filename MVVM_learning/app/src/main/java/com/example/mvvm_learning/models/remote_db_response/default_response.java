package com.example.mvvm_learning.models.remote_db_response;

public class default_response {

    private boolean error;

    private String message;

    public default_response(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() {
        return error;
    }

    public String getMessage() {
        return message;
    }
}
