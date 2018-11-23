package com.example.stz.myapplication.delegates;

import com.example.stz.myapplication.data.vos.NewsVos;

public interface NewsDelegates {
    void onTapNews(NewsVos news);
    void onTapFavorite(NewsVos news);
    void onTapComment(NewsVos news);
    void onTapSentTo(NewsVos news);
    void onTapStatistic(NewsVos news);
}
