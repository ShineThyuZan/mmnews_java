package com.example.stz.myapplication.events;

import com.example.stz.myapplication.data.vos.NewsVos;

import java.util.List;

public class SuccessForceRefreshGetNewsEvent extends SuccessGetNewsEvent
{


    public SuccessForceRefreshGetNewsEvent(List<NewsVos> newsList) {

        super (newsList);
    }
}
