package com.app.marinagoupiou.myhealthtech.model.core;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class User {

    @SerializedName("Type")
    @Expose
    public UserType type;

    @SerializedName("UserName")
    @Expose
    public String userName;

    @SerializedName("Password")
    @Expose
    public String password;

    @SerializedName("FirstName")
    @Expose
    public String firstName;

    @SerializedName("LastName")
    @Expose
    public String lastName;

    @SerializedName("CurrentMedication")
    @Expose
    public Med[] currentMedication;

    @SerializedName("PendingMedication")
    @Expose
    public Med[] pendingMedication;

    @SerializedName("ConfirmedMedication")
    @Expose
    public Med[] confirmedMedication;
}
