package com.example.stz.myapplication.events;

import com.example.stz.myapplication.data.vos.NewsVos;

import java.util.List;

public class SuccessGetNewsEvent {
    private List<NewsVos> newsList;

    public SuccessGetNewsEvent(List<NewsVos> newsList) {
        this.newsList = newsList;
    }

    public List<NewsVos> getNewsList() {
        return newsList;
    }
}
