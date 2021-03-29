package com.example.mvvm_learning.Api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    //change according to current IP
    // 13.58.111.54(aws)
    // 192.168.43.201(vasant-phone)
    // 10.193.151.111(wifi-makerlab)
    // 10.24.0.105(UKM_WIFI_2)
    // 10.24.8.165(UKM_WIFI_KKM)
    // 10.24.0.36 (UKM WARGA)
    // 10.1.156.23(pkukmweb.ukm.my)
    // aws* must change with each restart of instances

    private static final String BASE_URL = "http://ec2-54-209-165-18.compute-1.amazonaws.com/SAS_API/public/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;


    private RetrofitClient()
    {
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getmInstance()
    {
        if(mInstance == null)
        {
            mInstance = new RetrofitClient();
        }
        return mInstance;
    }

    public API getApi()
    {
        return retrofit.create(API.class);
    }}