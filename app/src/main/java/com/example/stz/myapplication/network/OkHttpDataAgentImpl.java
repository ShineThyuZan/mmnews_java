package com.example.stz.myapplication.network;

import android.os.AsyncTask;
import android.util.Log;

import com.example.stz.myapplication.events.ApiErrorEvent;
import com.example.stz.myapplication.events.SuccessGetNewsEvent;
import com.example.stz.myapplication.network.responses.GetNewsResponse;
import com.example.stz.myapplication.utils.MMNewsConstants;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpDataAgentImpl implements NewsDataAgent {

    private static OkHttpDataAgentImpl sObjInstance;
    private OkHttpClient mHttpClient;

    private OkHttpDataAgentImpl() {
        mHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .build();

    }

    public static OkHttpDataAgentImpl getInstance() {


        if (sObjInstance == null) {
            sObjInstance = new OkHttpDataAgentImpl();
        }
        return sObjInstance;

    }

    @Override
    public void loadNewsList(final int page, final String accessToken,final boolean isFrocedRefresh) {

        NetworkCallTask networkCallTask=new NetworkCallTask(accessToken,page,isFrocedRefresh);
        networkCallTask.execute();


//        new AsyncTask<Void, Void, String>() {
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                RequestBody formBody = new FormBody.Builder() //2.
//                        .add(MMNewsConstants.PARAM_ACCESS_TOKEN, accessToken)
//                        .add(MMNewsConstants.PARAM_PAGE, String.valueOf(page))
//                        .build();
//
//                Request request = new Request.Builder() //3
//                        .url(MMNewsConstants.API_BASE + MMNewsConstants.GET_NEWS)
//                        .post(formBody)
//                        .build();
//
//                try {
//                    Response response = mHttpClient.newCall(request).execute(); //4.
//                    if (response.isSuccessful()) {
//                        String responseString = response.body().string();
//
//                        return responseString;
//
//
//                    }
//                } catch (IOException e) {
//                    Log.e("LoadNewsList", e.getMessage());
//
//                }
//
//                return null;
//            }
//
//            @Override
//            protected void onPostExecute(String responseString) {
//                super.onPostExecute(responseString);
//                Gson gson = new Gson();
//                GetNewsResponse newsResponse = gson.fromJson(responseString, GetNewsResponse.class);
//                Log.d("onPostExecute", "News List Size:" + newsResponse.getMmNews().size());
//
//                if (newsResponse.isResponseOk()) {
//                    SuccessGetNewsEvent event = new SuccessGetNewsEvent(newsResponse.getMmNews());
//                    EventBus.getDefault().post(event);
//
//                } else {
//                    ApiErrorEvent event = new ApiErrorEvent(newsResponse.getMessage());
//                    EventBus.getDefault().post(event);
//
//                }
//
//            }
//        }.execute();

    }

    public static class NetworkCallTask extends AsyncTask<Void, Void, String> {

        private String mAccessToken;
        private int mPage;
        private boolean misForcedRefresh;
        private OkHttpClient mHttpClient;

        public NetworkCallTask(String AccessToken, int Page,boolean isFrocedRefresh) {
            this.mAccessToken = AccessToken;
            this.mPage = Page;
            this.misForcedRefresh=isFrocedRefresh;
        }

        @Override
        protected String doInBackground(Void... voids) {
            RequestBody formBody = new FormBody.Builder() //2.
                    .add(MMNewsConstants.PARAM_ACCESS_TOKEN, mAccessToken)
                    .add(MMNewsConstants.PARAM_PAGE, String.valueOf(mPage))
                    .build();

            Request request = new Request.Builder() //3
                    .url(MMNewsConstants.API_BASE + MMNewsConstants.GET_NEWS)
                    .post(formBody)
                    .build();

            try {
                Response response = mHttpClient.newCall(request).execute(); //4.
                if (response.isSuccessful()) {
                    String responseString = response.body().string();

                    return responseString;


                }
            } catch (IOException e) {
                Log.e("LoadNewsList", e.getMessage());

            }

            return null;
        }
    }


}
