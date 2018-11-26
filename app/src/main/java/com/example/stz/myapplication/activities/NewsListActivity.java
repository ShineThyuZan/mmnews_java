package com.example.stz.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.stz.myapplication.R;
import com.example.stz.myapplication.adapters.NewsAdapters;
import com.example.stz.myapplication.data.models.NewsModels;

import com.example.stz.myapplication.data.vos.NewsVos;
import com.example.stz.myapplication.delegates.NewsDelegates;
import com.example.stz.myapplication.events.ApiErrorEvent;
import com.example.stz.myapplication.events.SuccessForceRefreshGetNewsEvent;
import com.example.stz.myapplication.events.SuccessGetNewsEvent;
import com.example.stz.myapplication.viewpods.EmptyViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.mmtextview.MMFontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewsListActivity extends BaseActivity implements NewsDelegates {
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_news)
    RecyclerView recyclerViewNews;

    @BindView(R.id.vp_empty)
    EmptyViewPod vpEmpty;

    private NewsAdapters mNewsAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        ButterKnife.bind(this, this);


        setSupportActionBar(toolbar);


        mNewsAdapter = new NewsAdapters(this);
        recyclerViewNews.addOnScrollListener(new RecyclerView.OnScrollListener() {

            private boolean isListEndReached = false;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.d("", "OnScrollListenerOnScrollStateChange -" + newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE &&
                        ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition()
                                == recyclerView.getAdapter().getItemCount() && !isListEndReached) {
                    NewsModels.getObjInstance().loadNewsList();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.d("NewsListActivity", "OnScrollListenerOnScrollStateChange -dx" + dx + "dy" + dy);
                int visibleItemCount = recyclerView.getLayoutManager().getChildCount();
                int totalItemCount = recyclerView.getLayoutManager().getItemCount();
                int pastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findFirstVisibleItemPosition();

                if (visibleItemCount + pastVisibleItem < totalItemCount) {
                    isListEndReached = false;
                }
            }
        });

        recyclerViewNews.setAdapter(mNewsAdapter);


        recyclerViewNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));


           /*  recyclerViewNews.setLayoutManager(new GridLayoutManager(getApplicationContext(),
                    2));        */

        MMFontUtils.initMMTextView(this);


        NewsModels.getObjInstance().loadNewsList();
        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewsModels.getObjInstance().loadNewsList();
            }
        });

        vpEmpty.setEmptyData(R.drawable.empty, getApplicationContext()
                .getResources()
                .getString(R.string.empty_msg));
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onTapNews(NewsVos news) {
        Intent intent = new Intent(getApplicationContext(), NewsDetailsActivity.class);
        intent.putExtra("newsId", news.getNewId());
        startActivity(intent);

    }


    @Override
    public void onTapFavorite(NewsVos news) {

    }

    @Override
    public void onTapComment(NewsVos news) {

    }

    @Override
    public void onTapSentTo(NewsVos news) {

    }

    @Override
    public void onTapStatistic(NewsVos news) {

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetNews(SuccessGetNewsEvent event) {
        Log.d("onSuccessGetNews", "OnSuccessGetNews :" + event.getNewsList().size());
        mNewsAdapter.appendNewsList(event.getNewsList());
        swipeRefreshLayout.setRefreshing(false);
        vpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailGetNews(ApiErrorEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        Snackbar.make(swipeRefreshLayout, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();

        if(mNewsAdapter.getItemCount()<=0){
            vpEmpty.setVisibility(View.VISIBLE);
        }



    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessForceRefreshGetNews(SuccessForceRefreshGetNewsEvent event) {

        mNewsAdapter.setNewsList(event.getNewsList());
        swipeRefreshLayout.setRefreshing(false);
        vpEmpty.setVisibility(View.GONE);
    }


}
