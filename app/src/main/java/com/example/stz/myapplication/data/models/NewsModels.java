package com.example.stz.myapplication.data.models;

import com.example.stz.myapplication.data.vos.NewsVos;
import com.example.stz.myapplication.events.SuccessForceRefreshGetNewsEvent;
import com.example.stz.myapplication.events.SuccessGetNewsEvent;
import com.example.stz.myapplication.network.HttpUrlConnectionDataAgentImpl;
import com.example.stz.myapplication.network.NewsDataAgent;
import com.example.stz.myapplication.network.OkHttpDataAgentImpl;
import com.example.stz.myapplication.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;

public class NewsModels {
    private static NewsModels objInstance;
    private NewsDataAgent mDataAgent;
    private static final String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private Map<String, NewsVos> mNewsMap;
    private int mPage;


    private NewsModels() {

//        mDataAgent= HttpUrlConnectionDataAgentImpl.getObjInstance();
        // mDataAgent= OkHttpDataAgentImpl.getInstance();
        mDataAgent = RetrofitDataAgentImpl.getsObjInstance();

        mNewsMap = new HashMap<>();
        mPage = 1;
        EventBus.getDefault().register(this);
    }

    public static NewsModels getObjInstance() {
        if (objInstance == null) {
            objInstance = new NewsModels();
        }
        return objInstance;
    }

    public void loadNewsList() {
        mDataAgent.loadNewsList(mPage, DUMMY_ACCESS_TOKEN,false);

    }

    public void forceRefreshNewsList() {
        mPage=1;
        loadNewsList();
        mDataAgent.loadNewsList(1, DUMMY_ACCESS_TOKEN,true);

    }

    public NewsVos getNewByID(String newsId) {
        //    return null;//TODO remove after testing empty view layout in detail screen
        return mNewsMap.get(newsId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetNews(SuccessGetNewsEvent event) {
        setDataIntoRepository(event.getNewsList());
        mPage++;
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessForceRefreshGetNews(SuccessForceRefreshGetNewsEvent event) {
        setDataIntoRepository(event.getNewsList());
    }

    private void setDataIntoRepository(List<NewsVos> newsList) {
        for (NewsVos news : newsList) {
            mNewsMap.put(news.getNewId(), news);
        }
    }
}
