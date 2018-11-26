package com.example.stz.myapplication.network;

public interface NewsDataAgent {
    void loadNewsList(int page,String accessToken,boolean isforceRefresh);
}
