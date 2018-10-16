package com.example.stz.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stz.myapplication.R;
import com.example.stz.myapplication.delegates.NewsDelegates;
import com.example.stz.myapplication.viewholders.NewsViewHolder;

public class NewsAdapters extends RecyclerView.Adapter {
    private NewsDelegates mNewsDelegate;
    public NewsAdapters(NewsDelegates newsDelegates){
        mNewsDelegate=newsDelegates;

    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       LayoutInflater layoutInflater= LayoutInflater.from(viewGroup.getContext());
       View view=layoutInflater.inflate(R.layout.viewholder_news,viewGroup,false);

        return new NewsViewHolder(view,mNewsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }
}
