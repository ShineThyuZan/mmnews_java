package com.example.stz.myapplication.activities;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.stz.myapplication.R;
import com.example.stz.myapplication.data.models.NewsModels;
import com.example.stz.myapplication.data.vos.NewsVos;
import com.example.stz.myapplication.viewpods.EmptyViewPod;

import org.mmtextview.MMFontUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsDetailsActivity extends BaseActivity {
    @BindView(R.id.tv_news_details)
    TextView tvNewsDetail;

     @BindView(R.id.vp_empty)
     EmptyViewPod vpEmpty;

     @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MMFontUtils.initMMTextView(this);
        setContentView(R.layout.activity_news_details);

        ButterKnife.bind(this, this);
        String newsId = getIntent().getStringExtra("newsId");
        Log.d("NewsDetailsId", "newsId" + newsId);
        NewsVos news = NewsModels.getObjInstance().getNewByID(newsId);

        if(news !=null) {

            bindData(news);
        }else{
            vpEmpty.setVisibility(View.VISIBLE);
            coordinatorLayout.setVisibility(View.GONE);
        }
        vpEmpty.setEmptyData("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSVYdaHRtgaLt9dJvddo9HqBcWCiaoJmw-QEnrZsXiIjBx6ddKnRg", getApplicationContext()
                .getResources()
                .getString(R.string.empty_msg));
    }
    public void bindData(NewsVos news){
        tvNewsDetail.setText(news.getDetails());
    }
}
