package com.example.stz.myapplication.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.stz.myapplication.R;
import com.example.stz.myapplication.data.vos.NewsVos;
import com.example.stz.myapplication.delegates.NewsDelegates;
import com.example.stz.myapplication.network.NewsDataAgent;
import com.example.stz.myapplication.viewholders.NewsViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewsAdapters extends RecyclerView.Adapter<NewsViewHolder> {
    private NewsDelegates mNewsDelegate;
    private List<NewsVos> mNewsList;




    public NewsAdapters(NewsDelegates newsDelegates){
        mNewsDelegate=newsDelegates;
        mNewsList = new ArrayList<>();

    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
       LayoutInflater layoutInflater= LayoutInflater.from(viewGroup.getContext());
       View view=layoutInflater.inflate(R.layout.viewholder_news,viewGroup,false);

        return new NewsViewHolder(view,mNewsDelegate);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.setNewsData(mNewsList.get(position));

    }


    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setNewsList(List<NewsVos> newsList){
        mNewsList=newsList;
        notifyDataSetChanged();

    }
}
