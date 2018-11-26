package com.example.stz.myapplication.viewholders;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.stz.myapplication.R;
import com.example.stz.myapplication.data.vos.NewsVos;
import com.example.stz.myapplication.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsBriefViewHolder extends BaseNewsViewHolder {

    @BindView(R.id.iv_publication)
    ImageView ivPublication;


    @BindView(R.id.tv_publication_title)
    TextView tvPublicationTitle;
    @BindView(R.id.tv_news_brief)
    TextView tvNewsBrief;
    @BindView(R.id.iv_news_hero)
    ImageView ivNewsHero;

    private NewsVos mNews;

    public NewsBriefViewHolder(View itemView) {


        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bindData(NewsVos news) {
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


        tvPublicationTitle.setText(news.getPublication().getTitle());

    }
}
