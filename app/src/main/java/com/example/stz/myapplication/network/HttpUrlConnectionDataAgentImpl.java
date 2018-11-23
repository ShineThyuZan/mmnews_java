package com.example.stz.myapplication.network;

import android.os.AsyncTask;

public class HttpUrlConnectionDataAgentImpl implements NewsDataAgent {
    @Override
    public void loadNewsList(int page, String accessToken) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
            }
        }.execute();
        
    }
}
