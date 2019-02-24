package com.kce.trendyol.main;

import android.content.Context;
import android.content.Intent;

import com.kce.trendyol.detail.DetailActivity;
import com.kce.trendyol.models.Photo;
import com.kce.trendyol.models.ApiResponse;
public class MainPresenter implements GetPhotosInteractor.Listener {
    private MainView mainView;
    private GetPhotosInteractor getPhotosInteractor;
    int currentPage = 0;
    ApiResponse apiResponse;

    MainPresenter(MainView mainView, GetPhotosInteractor getPhotosInteractor) {
        this.mainView = mainView;
        this.getPhotosInteractor = getPhotosInteractor;
    }

    void onCreate() {
        if (mainView != null) {
            mainView.showProgress();
        }
        loadMore();
    }

    void onItemClicked(Context context, Photo photo) {
        if (mainView != null) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("photo", photo);
            context.startActivity(intent);
        }
    }

    void onDestroy() {
        mainView = null;
    }

    void loadMore() {
        if (apiResponse != null && apiResponse.getResponse().getPages() <= currentPage + 1) {
            return;
        }
        mainView.showProgress();
        currentPage++;
        getPhotosInteractor.getItems(currentPage, this);
    }

    @Override
    public void onSuccess(ApiResponse response) {
        apiResponse = response;
        if (mainView != null) {
            mainView.setResponse(response);
            mainView.hideProgress();
        }
    }

    @Override
    public void onError(Throwable t) {
        if (mainView != null) {
            mainView.hideProgress();
            mainView.showMessage(t.getMessage());
        }
    }



    public MainView getMainView() {
        return mainView;
    }
}
