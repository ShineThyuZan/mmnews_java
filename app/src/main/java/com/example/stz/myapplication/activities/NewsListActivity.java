package com.example.stz.myapplication.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import com.example.stz.myapplication.R;
import com.example.stz.myapplication.adapters.NewsAdapters;
import org.mmtextview.MMFontUtils;


public class NewsListActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView rvNews=findViewById(R.id.rv_news);
        NewsAdapters newsAdapters=new NewsAdapters();

        rvNews.setAdapter(newsAdapters);
        rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL,false));

        MMFontUtils.initMMTextView(this);
    }
}
