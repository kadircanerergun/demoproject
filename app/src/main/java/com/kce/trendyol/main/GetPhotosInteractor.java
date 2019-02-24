package com.kce.trendyol.main;

import com.kce.trendyol.MyApplication;
import com.kce.trendyol.models.ApiResponse;
import com.kce.trendyol.network.Services.PhotoRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPhotosInteractor {

    interface Listener {
        void onSuccess(ApiResponse response);
        void onError(Throwable t);
    }

    public void getItems(int page, final Listener listener) {
        MyApplication.getInstance().getRestClient().getService(PhotoRepository.class).fetchResult("flickr.photos.search", page,10).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

    public void getItems(int page, int perPage, final Listener listener) {
        MyApplication.getInstance().getRestClient().getService(PhotoRepository.class).fetchResult("flickr.photos.search", page, perPage).enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                listener.onError(t);
            }
        });
    }

}
