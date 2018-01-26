package com.app.marinagoupiou.myhealthtech.model.core;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public enum UserType {
    @SerializedName("0")
    @Expose
    Patient,

    @SerializedName("1")
    @Expose
    Employee
}
