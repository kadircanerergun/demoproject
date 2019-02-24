package com.kce.trendyol.network;

/**
 * Created by kadircanerergun on 24/02/2019.
 */

import android.support.annotation.NonNull;
import android.text.TextUtils;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class FlickrInterceptor implements Interceptor {
    String apiKey = "d1a7a828832f05081ad7403fbca6da07";
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        request = request.newBuilder().url(request.url().newBuilder()
                .addQueryParameter("api_key", apiKey)
                .addQueryParameter("format", "json")
                .addQueryParameter("nojsoncallback", "1")
                .addQueryParameter("tags", "world")
                .build())
                .build();
        return chain.proceed(request);
    }
}
