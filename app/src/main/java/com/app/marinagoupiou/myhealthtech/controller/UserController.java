package com.app.marinagoupiou.myhealthtech.controller;

import com.app.marinagoupiou.myhealthtech.controller.networking.UserService;
import com.app.marinagoupiou.myhealthtech.model.core.LoginUser;
import com.app.marinagoupiou.myhealthtech.model.core.User;

import java.util.concurrent.TimeUnit;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class UserController {

    UserService userService = null;

    public UserController(Retrofit retrofit) {
        this.userService = retrofit.create(UserService.class);
    }

    public Single<User> login(String username, String password) {
        // Added delay to simulate network latency
        return this.userService.login(new LoginUser(username, password)).delay(3, TimeUnit.SECONDS);
    }

    public Completable logout(String username) {
        return this.userService.logout(username);
    }

}
