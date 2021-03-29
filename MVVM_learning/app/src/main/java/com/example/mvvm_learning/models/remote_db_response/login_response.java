package com.example.mvvm_learning.models.remote_db_response;

public class login_response {

    private boolean error;
    private String message;

    public login_response(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public boolean isError() { return error; }

    public String getMessage() {
        return message;
    }

}
