package com.example.mvvm_learning.viewModels;

import com.example.mvvm_learning.repository.remote_db.LoginRepository;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;

import androidx.lifecycle.ViewModel;

public class LoginActivityViewModel extends ViewModel{

    private LoginRepository mLoginRepository;

    public ObservableField<String> username = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();

    public LoginActivityViewModel()
    {
        mLoginRepository = LoginRepository.getInstance();
    }

    public LiveData<String> getResponse()
    {
        return mLoginRepository.getResponse();
    }

    // call the method from loginrepository
    public void loginapi()
    {
        mLoginRepository.loginapi(username.get(),password.get());
    }
}
