package com.example.stz.myapplication.network;

import com.example.stz.myapplication.network.responses.GetNewsResponse;
import com.example.stz.myapplication.utils.MMNewsConstants;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface NewsApi {
    @FormUrlEncoded
    @POST(MMNewsConstants.GET_NEWS)
    Call<GetNewsResponse>loadNewsList(
            @Field(MMNewsConstants.PARAM_ACCESS_TOKEN)String accessToken,
           @Field(MMNewsConstants.PARAM_PAGE) int page);

}
