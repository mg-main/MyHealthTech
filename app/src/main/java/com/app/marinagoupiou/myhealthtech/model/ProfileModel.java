package com.app.marinagoupiou.myhealthtech.model;

import com.app.marinagoupiou.myhealthtech.model.core.User;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class ProfileModel {
    User user = new User();

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
