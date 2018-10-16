package com.example.stz.myapplication.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.stz.myapplication.delegates.NewsDelegates;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    private NewsDelegates mNewsDelegates;

    public NewsViewHolder(@NonNull View itemView, NewsDelegates newDelegates) {
        super(itemView);
        mNewsDelegates=newDelegates;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsDelegates.onTapNews();
            }
        });
    }
}
