package com.example.stz.myapplication.data.vos;

import com.google.gson.annotations.SerializedName;

public class FavoriteVos {
    @SerializedName("favorite-id")
    private String favoriteId;

    @SerializedName("favorite-date")
    private String favoriteDate;

    @SerializedName("acted-user")
    private ActedUserVos actedUser;

    public String getFavoriteId() {
        return favoriteId;
    }

    public String getFavoriteDate() {
        return favoriteDate;
    }

    public ActedUserVos getActedUser() {
        return actedUser;
    }
}
