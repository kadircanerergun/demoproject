package com.kce.trendyol;

import android.app.Application;

import com.kce.trendyol.network.RestClient;

public class MyApplication extends Application {
    private RestClient restClient;
    public static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static MyApplication getInstance() {
        return instance;
    }


    public RestClient getRestClient() {
        if (restClient == null)
            restClient = new RestClient();
        return restClient;
    }


}
