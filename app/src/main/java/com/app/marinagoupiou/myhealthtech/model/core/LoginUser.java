package com.app.marinagoupiou.myhealthtech.model.core;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class LoginUser {

    @SerializedName("UserName")
    @Expose
    public String userName;

    @SerializedName("Password")
    @Expose
    public String password;

    public LoginUser() {
        this("", "");
    }

    public LoginUser(String username, String password) {
        this.userName = username;
        this.password = password;
    }
}
