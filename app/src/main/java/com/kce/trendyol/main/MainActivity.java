package com.kce.trendyol.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kce.trendyol.R;
import com.kce.trendyol.models.Photo;
import com.kce.trendyol.models.ApiResponse;
import com.kce.trendyol.models.PhotoResponse;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MainPresenter presenter;
    private MainAdapter recycleAdapter;
    private int visibleThreshold = 2;
    private int lastVisibleItem, totalItemCount;
    private boolean loading;
    List<Photo> photos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        presenter = new MainPresenter(this, new GetPhotosInteractor());
        initAdapter();
        presenter.onCreate();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
    private void initAdapter()
    {
        photos = new ArrayList<>();
        recycleAdapter =  new MainAdapter(photos, new MainAdapter.Listener() {
            @Override
            public void onItemClicked(Context c, Photo p) {
                presenter.onItemClicked(c, p);
            }
        });
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recycleAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount = layoutManager.getItemCount();
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if(!loading && totalItemCount <= (lastVisibleItem + visibleThreshold)){
                    if(presenter != null){
                        presenter.loadMore();
                        loading = true;
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    private void initViews()
    {
        recyclerView = findViewById(R.id.list);
        progressBar = findViewById(R.id.progress);

    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setResponse(ApiResponse response) {
        loading = false;
        if (response.getStat().equals("ok")) {
            PhotoResponse dataResponse = response.getResponse();
            photos.addAll(dataResponse.getPhotos());
            recycleAdapter.notifyItemRangeInserted(dataResponse.getPage()*dataResponse.getPerpage(), dataResponse.getPerpage());
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
