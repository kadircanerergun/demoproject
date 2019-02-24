package com.kce.trendyol.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PhotoResponse {

        @SerializedName("page")
        @Expose
        private Integer page;
        @SerializedName("pages")
        @Expose
        private Integer pages;
        @SerializedName("perpage")
        @Expose
        private Integer perpage;
        @SerializedName("total")
        @Expose
        private Integer total;
        @SerializedName("photo")
        @Expose
        private List<Photo> photo = null;
        private final static long serialVersionUID = 6152634299146291376L;

        public Integer getPage() {
            return page;
        }

        public Integer getPages() {
            return pages;
        }

        public Integer getPerpage() {
            return perpage;
        }

        public Integer getTotal() {
            return total;
        }

        public List<Photo> getPhotos() {
            return photo;
        }

        public PhotoResponse(Integer page, Integer pages, Integer perpage, Integer total, List<Photo> photo) {
            this.page = page;
            this.pages = pages;
            this.perpage = perpage;
            this.total = total;
            this.photo = photo;
        }
}
