package com.example.stz.myapplication.data.vos;

import com.google.gson.annotations.SerializedName;

public class PublicationVos {
    @SerializedName("publication-id")
    private String publicationId;

    @SerializedName("title")
    private String title;

    @SerializedName("logo")
    private String logo;

    public String getPublicationId() {
        return publicationId;
    }

    public String getTitle() {
        return title;
    }

    public String getLogo() {
        return logo;
    }
}
