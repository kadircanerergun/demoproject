package com.kce.trendyol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ApiResponse implements Serializable
{

    @SerializedName("photos")
    @Expose
    private PhotoResponse response;
    @SerializedName("stat")
    @Expose
    private String stat;
    private final static long serialVersionUID = 4510563015884271437L;

    public ApiResponse() {

    }

    public PhotoResponse getResponse() {
        return response;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public ApiResponse(PhotoResponse response, String stat) {
        this.response = response;
        this.stat = stat;
    }


}