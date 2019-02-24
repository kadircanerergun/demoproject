package com.kce.trendyol.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kce.trendyol.R;
import com.kce.trendyol.models.Photo;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {


    public MainAdapter(List<Photo> items, final Listener listener) {
        this.items = items;
        this.listener = listener;
    }

    interface Listener {
        void onItemClicked(Context c, Photo photo);
    }

    private List<Photo> items;
    private Listener listener;

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_photo_item, parent, false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        final Photo item = items.get(position);
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(view.getContext(), item);
            }
        });
        ImageView imageViewPhoto = holder.container.findViewById(R.id.photoIv);
        ImageView avatarView = holder.container.findViewById(R.id.avatarIv);
        TextView profileTv = holder.container.findViewById(R.id.profileTv);
        Glide.with(holder.container.getContext()).load(item.getPhotoURL()).placeholder(R.drawable.placeholder).into(imageViewPhoto);
        Glide.with(holder.container.getContext()).load(item.getProfilePicture()).placeholder(R.drawable.avatar_placeholder).into(avatarView);
        profileTv.setText(item.getOwnername());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {

        CardView container;
        ImageView photoView;

        MainViewHolder(CardView cardView) {
            super(cardView);
            this.container = cardView;
        }
    }
}

