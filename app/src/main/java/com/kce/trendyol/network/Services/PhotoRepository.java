package com.kce.trendyol.network.Services;

import com.kce.trendyol.models.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PhotoRepository {


    //https://api.flickr.com/services/rest/?method=flickr.photos.getRecent&api_key=&format=json&per_page=10&extras=owner_name
    @GET("/services/rest?extras=owner_name,icon_server&tags=world")
    Call<ApiResponse> fetchResult(
            @Query("method") String method,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
    @GET("/services/rest?extras=owner_name,icon_server")
    Call<ApiResponse> fetchResultWithTag(
            @Query("method") String method,
            @Query("page") int page,
            @Query("per_page") int perPage,
            @Query("tags") String tags
    );
}
