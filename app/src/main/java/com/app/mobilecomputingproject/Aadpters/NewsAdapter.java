package com.app.mobilecomputingproject.Aadpters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.mobilecomputingproject.Models.NewsData;
import com.app.mobilecomputingproject.databinding.NewsItemBinding;
import com.app.mobilecomputingproject.listeners.OnNewsClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
    ArrayList<NewsData> newsData;
    OnNewsClickListener onNewsClickListener;

    public NewsAdapter(ArrayList<NewsData> newsData, OnNewsClickListener onNewsClickListener) {
        this.newsData = newsData;
        this.onNewsClickListener = onNewsClickListener;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHolder(NewsItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
        NewsData newsData = this.newsData.get(position);
        holder.newsItemBinding.setId(position);
        holder.newsItemBinding.setTitle(newsData.getNews_text());
        holder.newsItemBinding.setOnNewClick(onNewsClickListener);
        holder.newsItemBinding.executePendingBindings();
        Picasso.get().load(newsData.getImage()).into(holder.newsItemBinding.imageView);

    }

    @Override
    public int getItemCount() {
        return newsData.size() ;
    }

    public class NewsHolder extends RecyclerView.ViewHolder {
        NewsItemBinding newsItemBinding;
        public NewsHolder(NewsItemBinding newsItemBinding) {
            super(newsItemBinding.getRoot());
            this.newsItemBinding = newsItemBinding;
        }
    }
}
