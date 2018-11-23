package com.example.stz.myapplication.viewholders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.stz.myapplication.R;
import com.example.stz.myapplication.data.vos.NewsVos;
import com.example.stz.myapplication.delegates.NewsDelegates;
import com.example.stz.myapplication.utils.GlideApp;
import com.example.stz.myapplication.utils.MMNewsGlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsViewHolder extends RecyclerView.ViewHolder {
    private NewsDelegates mNewsDelegates;
    private NewsVos mNews;

    @BindView(R.id.tv_news_brief)
    TextView tvNewsBrief;

    @BindView(R.id.iv_news_hero)
    ImageView ivNewsHero;

    @BindView(R.id.iv_publication)
    ImageView ivPublication;

    @BindView(R.id.tv_publication_title)
     TextView tvPublicationTitle;

    @BindView(R.id.tv_posted_date)
    TextView tvPostedDate;

    public NewsViewHolder(@NonNull View itemView, NewsDelegates newDelegates) {
        super(itemView);

        ButterKnife.bind(this, itemView);

        mNewsDelegates = newDelegates;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mNewsDelegates.onTapNews(mNews);
            }
        });
    }

    public void setNewsData(NewsVos news) {
      mNews = news;
        tvNewsBrief.setText(news.getBrief());

        if (!news.getImages().isEmpty()) {
            GlideApp.with(ivNewsHero.getContext())
                    .load(news.getImages().get(0))
                    .placeholder(R.drawable.placeholderimage)
                    .error(R.drawable.errorimage)
                    .into(ivNewsHero);
        } else {
            ivNewsHero.setVisibility(View.GONE);
        }

        Glide.with(ivPublication.getContext())
                .load(news.getPublication().getLogo())
                .apply(RequestOptions
                        .circleCropTransform()
                        .placeholder(R.drawable.placeholderimage)
                        .error(R.drawable.errorimage))
                .into(ivPublication);

        tvPublicationTitle.setText(news.getPublication().getTitle());

        tvPostedDate.setText(tvPostedDate.getContext()
                .getResources().getString(R.string.format_posted_date,news.getPostedDate()));
    }
}
