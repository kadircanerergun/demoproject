package com.kce.trendyol.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by kadircanerergun on 24/02/2019.
 */

public class RestClient {

    private static final String API_BASE_URL = "https://api.flickr.com";

    private Retrofit retrofitClient;
    private OkHttpClient okHttpClient;

    public RestClient() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new FlickrInterceptor())
                .addInterceptor(loggingInterceptor).build();

        retrofitClient = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();
    }


    public <T> T getService(Class<T> type) {
        return this.retrofitClient.create(type);
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }
}
