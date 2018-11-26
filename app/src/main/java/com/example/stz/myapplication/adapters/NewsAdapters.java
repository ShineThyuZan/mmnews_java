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
import com.example.stz.myapplication.viewholders.BaseNewsViewHolder;
import com.example.stz.myapplication.viewholders.NewsBriefViewHolder;
import com.example.stz.myapplication.viewholders.NewsViewHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NewsAdapters extends RecyclerView.Adapter<BaseNewsViewHolder> {

    private static final int  VIEWTYPE_NEWS_COMPLETE=1000;
    private static final int VIEWTYPE_NEWS_BRIEF=2000;
    private NewsDelegates mNewsDelegate;
    private List<NewsVos> mNewsList;


    public NewsAdapters(NewsDelegates newsDelegates) {
        mNewsDelegate = newsDelegates;
        mNewsList = new ArrayList<>();

    }

    @NonNull
    @Override
    public BaseNewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {



        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());
        if(viewType==VIEWTYPE_NEWS_COMPLETE) {

            View view = layoutInflater.inflate(R.layout.viewholder_news, viewGroup, false);

            return new NewsViewHolder(view, mNewsDelegate);
        }else {
            View view = layoutInflater.inflate(R.layout.viewholder_news, viewGroup, false);

            return new NewsBriefViewHolder(view);


        }

    }

    @Override
    public void onBindViewHolder(@NonNull BaseNewsViewHolder holder, int position) {
        holder.bindData(mNewsList.get(position));

    }


    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(position==0){
            return VIEWTYPE_NEWS_COMPLETE;
        }else {
            return VIEWTYPE_NEWS_BRIEF;
        }

    }

    public void setNewsList(List<NewsVos> newsList) {
        mNewsList = newsList;
        notifyDataSetChanged();

    }

    public void appendNewsList(List<NewsVos> newsList) {
        mNewsList.addAll(newsList);
        notifyDataSetChanged();

    }
}
