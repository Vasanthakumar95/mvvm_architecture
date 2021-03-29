package com.example.mvvm_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvm_learning.databinding.LoginActivityBinding;
import com.example.mvvm_learning.viewModels.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginActivityViewModel mLoginActivityViewModel;
    private LoginActivityBinding mLoginActivityBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        mLoginActivityViewModel = new  ViewModelProvider(this).get(LoginActivityViewModel.class);

        mLoginActivityBinding = DataBindingUtil.setContentView(this,R.layout.login_activity);
        mLoginActivityBinding.setLoginActivityViewModel(mLoginActivityViewModel);

        ObserveAnyChange();

    }

    private void ObserveAnyChange()
    {
        mLoginActivityViewModel.getResponse().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String res) {
                Toast.makeText(LoginActivity.this, res,Toast.LENGTH_SHORT).show();

                if (res.equals("Login Successful"))
                {
                    //set up the call to check the Teacher fragment menu  drawer
                    startActivity(new Intent(LoginActivity.this, Teacher.class));
                    finish();
                }

            }
        });

    }


}