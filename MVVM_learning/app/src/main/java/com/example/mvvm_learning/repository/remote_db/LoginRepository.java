package com.example.mvvm_learning.repository.remote_db;

import com.example.mvvm_learning.api_request.LoginApiRequest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class LoginRepository {

    private static LoginRepository instance;

    private LoginApiRequest mLoginApiRequest;

    public static LoginRepository getInstance()
    {
        if(instance == null){
            instance = new LoginRepository();
        }
        return instance;
    }

    private LoginRepository()
    {
        mLoginApiRequest = LoginApiRequest.getInstance();
    }


    public LiveData<String> getResponse()
    {
        return mLoginApiRequest.getResponse();
    }

    // call the method from loginapirequest
    public void loginapi(String username, String password)
    {
        mLoginApiRequest.loginApi(username,password);
    }


}
