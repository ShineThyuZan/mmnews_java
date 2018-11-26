package com.example.stz.myapplication.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.stz.myapplication.data.vos.NewsVos;

public abstract class BaseNewsViewHolder extends RecyclerView.ViewHolder {
    public BaseNewsViewHolder(View itemView) {
        super(itemView);
    }

    public void bindData(NewsVos news){

    }
}
