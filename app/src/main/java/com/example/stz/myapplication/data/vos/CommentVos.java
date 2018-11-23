package com.example.stz.myapplication.data.vos;

import com.google.gson.annotations.SerializedName;

public class CommentVos
{
    @SerializedName("comment-id")
    private String commentId;

    @SerializedName("comment")
    private String comments;

    @SerializedName("comment-date")
    private String commentDate;

    @SerializedName("acted-user")
    private ActedUserVos actedUser;

    public String getCommentId() {
        return commentId;
    }

    public String getComments() {
        return comments;
    }

    public String getCommentDate() {
        return commentDate;
    }

    public ActedUserVos getActedUser() {
        return actedUser;
    }
}
