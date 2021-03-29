package com.example.mvvm_learning;

import com.example.mvvm_learning.api_request.LoginApiRequest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    //singleton pattern

    private static AppExecutors instance;

    public static AppExecutors getInstance()
    {
        if(instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }

    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService getmNetworkIO() {
        return mNetworkIO;
    }


}
