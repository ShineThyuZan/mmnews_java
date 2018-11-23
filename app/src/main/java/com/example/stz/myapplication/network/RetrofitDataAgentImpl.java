package com.example.stz.myapplication.network;

import com.example.stz.myapplication.events.ApiErrorEvent;
import com.example.stz.myapplication.events.SuccessGetNewsEvent;
import com.example.stz.myapplication.network.responses.GetNewsResponse;
import com.example.stz.myapplication.utils.MMNewsConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements NewsDataAgent {
    private static RetrofitDataAgentImpl sObjInstance;
    private NewsApi mTheApi;


    private RetrofitDataAgentImpl() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MMNewsConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        mTheApi = retrofit.create(NewsApi.class);
    }

    public static RetrofitDataAgentImpl getsObjInstance() {
        if (sObjInstance == null) {
            sObjInstance = new RetrofitDataAgentImpl();
        }


        return sObjInstance;
    }

    @Override
    public void loadNewsList(int page, String accessToken) {
        Call<GetNewsResponse> loadNewsCall=mTheApi.loadNewsList(accessToken,page);
        loadNewsCall.enqueue(new Callback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                GetNewsResponse newsResponse = response.body();

                if(newsResponse!=null&&newsResponse.isResponseOk()) {
                    SuccessGetNewsEvent event= new SuccessGetNewsEvent(newsResponse.getMmNews());
                    EventBus.getDefault().post(event);


                }else
                {
                    if(newsResponse==null){
                        ApiErrorEvent event= new ApiErrorEvent("Empty response network Call");
                        EventBus.getDefault().post(event);

                    }
                    else
                    {
                        ApiErrorEvent event= new ApiErrorEvent(newsResponse.getMessage());
                        EventBus.getDefault().post(event);

                    }
                }


            }

            @Override
            public void onFailure(Call<GetNewsResponse> call, Throwable t) {
                ApiErrorEvent event= new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);

            }
        });

    }
}
