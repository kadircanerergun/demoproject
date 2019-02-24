package com.kce.trendyol.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.kce.trendyol.R;
import com.kce.trendyol.models.Photo;

public class DetailActivity extends AppCompatActivity implements DetailView {
    private DetailPresenter presenter;
    ImageView ivPhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);
        initViews();
        presenter = new DetailPresenter(this, (Photo) getIntent().getExtras().getSerializable("photo"));
    }
    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    private void initViews() {
        ivPhoto = findViewById(R.id.photoIv);
    }

    @Override
    public void loadImage(Photo photo) {
        Glide.with(this).load(photo.getPhotoURL()).placeholder(R.drawable.placeholder).into(ivPhoto);
    }
}
