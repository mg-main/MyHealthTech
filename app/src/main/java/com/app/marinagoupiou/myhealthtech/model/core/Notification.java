package com.app.marinagoupiou.myhealthtech.model.core;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class Notification {

    @SerializedName("Message")
    @Expose
    public String message;

    @SerializedName("DateReceived")
    @Expose
    public String dateReceived;

    @SerializedName("TimeReceived")
    @Expose
    public String timeReceived;

    public Notification(String message, String dateReceived, String timeReceived) {
        this.message = message;
        this.dateReceived = dateReceived;
        this.timeReceived = timeReceived;
    }
}


