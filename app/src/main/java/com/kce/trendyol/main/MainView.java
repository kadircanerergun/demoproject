package com.kce.trendyol.main;

import com.kce.trendyol.models.ApiResponse;

public interface MainView {
    void showProgress();

    void hideProgress();

    void setResponse(ApiResponse response);

    void showMessage(String message);
}
