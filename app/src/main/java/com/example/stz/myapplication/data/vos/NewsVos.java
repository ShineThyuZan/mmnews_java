package com.example.stz.myapplication.data.vos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class NewsVos {

    @SerializedName("news-id")
    private String newId;

    @SerializedName("brief")
    private String brief;

    @SerializedName("details")
    private String details;

    @SerializedName("images")
    private List<String> images;

    @SerializedName("posted-date")
    private String postedDate;

    @SerializedName("publication")
    private PublicationVos publication;

    @SerializedName("favorites")
    private List<FavoriteVos> favorite;

    @SerializedName("comments")
    private List<CommentVos> comment;

    @SerializedName("sent-tos")
    private List<SendToVos> sendTo;

    public NewsVos() {
    }

    public String getNewId() {
        return newId;
    }

    public String getBrief() {
        return brief;
    }

    public String getDetails() {
        return details;
    }

    public List<String> getImages() {
        if (images == null) {
            return new ArrayList<>();
        }
        return images;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVos getPublication() {
        return publication;
    }

    public List<FavoriteVos> getFavorite() {
        return favorite;
    }

    public List<CommentVos> getComment() {
        return comment;
    }

    public List<SendToVos> getSendTo() {
        return sendTo;
    }
}
