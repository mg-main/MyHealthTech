package com.app.marinagoupiou.myhealthtech.model.core;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class Med {

    @SerializedName("Name")
    @Expose
    String name;

    @SerializedName("Status")
    @Expose
    int status;  // possible values: 1 for no quantity of medication - 2 for little - 3 for adequate

    public Med(String name, int status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
}
