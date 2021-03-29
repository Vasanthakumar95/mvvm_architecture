package com.example.mvvm_learning.api_request;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.example.mvvm_learning.Api.RetrofitClient;
import com.example.mvvm_learning.AppExecutors;
import com.example.mvvm_learning.models.remote_db_response.login_response;

import java.io.IOException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginApiRequest {

    //mutable-life-data can be changed while LiveData couldn't
    private MutableLiveData<String> msgResponse;

    private static LoginApiRequest instance;

    private RetrieveLoginRunnable mRetrieveLoginRunnable;

    public static LoginApiRequest getInstance()
    {
        if(instance == null){
        instance = new LoginApiRequest();
    }
        return instance;
    }

    private LoginApiRequest()
    {
        msgResponse = new MutableLiveData<>();
    }


    public LiveData<String> getResponse()
    {
        return msgResponse;
    }

    // this method that will be called through the classes
    public void loginApi(String username, String password)
    {
        if(mRetrieveLoginRunnable != null)
        {
            mRetrieveLoginRunnable = null;
        }

        mRetrieveLoginRunnable = new RetrieveLoginRunnable(username, password);

        final Future myHandler = AppExecutors.getInstance().getmNetworkIO().submit(mRetrieveLoginRunnable);

        AppExecutors.getInstance().getmNetworkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);

    }

    //retrieve data from RestAPI by runnable class
    private class RetrieveLoginRunnable implements Runnable
    {
        private String username, password;
        boolean cancelRequest;

        public RetrieveLoginRunnable(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public void run() {
            try
            {
                Call<login_response> call = RetrofitClient.getmInstance().getApi().userlogin(username, password);
                call.enqueue(new Callback<login_response>() {
                    @Override
                    public void onResponse(Call<login_response> call, Response<login_response> response) {
                        if(response.isSuccessful())
                        {
                            msgResponse.postValue(response.body().getMessage());
                            Log.v("LoginResponse", response.body().getMessage());
                        }else
                            {
                                Log.v("LoginResponse", "response not sucessful ");
                            }
                    }

                    @Override
                    public void onFailure(Call<login_response> call, Throwable t) {

                    }
                });

            }catch (Exception e) {
                e.printStackTrace();
                cancelRequest();
            }

        }

        private void cancelRequest()
        {
            Log.v("TAG", "Canselling Search Request");
            cancelRequest = true;
        }

    }

}
