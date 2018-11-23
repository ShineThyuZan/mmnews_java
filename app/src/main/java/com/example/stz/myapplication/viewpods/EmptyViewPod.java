package com.example.stz.myapplication.viewpods;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.stz.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EmptyViewPod extends RelativeLayout {

    @BindView(R.id.iv_empty)
    ImageView imageViewEmpty;

    @BindView(R.id.tv_empty)
    TextView textViewEmpty;


    public EmptyViewPod(Context context) {
        super(context);
    }

    public EmptyViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EmptyViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }

    public void setEmptyData(String emptyImageUrl,String emptyMsg){
        Glide.with(getContext())
                .load(emptyImageUrl)
                .into(imageViewEmpty);
        textViewEmpty.setText(emptyMsg);

    }


    public void setEmptyData(int emptyImageResource,String emptyMsg){
        imageViewEmpty.setImageResource(emptyImageResource);
        textViewEmpty.setText(emptyMsg);
    }
}
