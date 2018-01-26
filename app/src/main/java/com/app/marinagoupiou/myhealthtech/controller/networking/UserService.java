package com.app.marinagoupiou.myhealthtech.controller.networking;

import com.app.marinagoupiou.myhealthtech.model.core.LoginUser;
import com.app.marinagoupiou.myhealthtech.model.core.User;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;
import retrofit2.http.Path;


/**
 * Created by marinagoupiou on 26/01/2018.
 */

public interface UserService {

    @POST("user/login")
    Single<User> login(@Body LoginUser user);

    @DELETE("user/login/{username}")
    Completable logout(@Path("username") String username);

}
