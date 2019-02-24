package com.kce.trendyol.detail;

import com.kce.trendyol.models.Photo;

public class DetailPresenter {
    private DetailView detailView;
    private Photo photo;;

    DetailPresenter(DetailView detailView, Photo photo) {
        this.detailView = detailView;
        this.photo = photo;
    }

    void onResume() {
        if (detailView != null) {
            detailView.loadImage(photo);
        }

    }

    void onDestroy() {
        detailView = null;
    }


    public DetailView getDetailView() {
        return detailView;
    }
}
