package com.example.stz.myapplication.data.vos;

import com.google.gson.annotations.SerializedName;

public class SendToVos {

    @SerializedName("send-to-id")
    private String sentToId;

    @SerializedName("sent-date")
    private String sentDate;

    @SerializedName("acted-user")
    private ActedUserVos actedUser;

    @SerializedName("received-user")
    private ActedUserVos receivedUser;

    public String getSentToId() {
        return sentToId;
    }

    public String getSentDate() {
        return sentDate;
    }

    public ActedUserVos getActedUser() {
        return actedUser;
    }

    public ActedUserVos getReceivedUser() {
        return receivedUser;
    }
}
