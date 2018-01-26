package com.app.marinagoupiou.myhealthtech;

import android.app.Application;

import com.app.marinagoupiou.myhealthtech.controller.UserController;
import com.app.marinagoupiou.myhealthtech.controller.networking.MainDispatcher;
import com.app.marinagoupiou.myhealthtech.model.NotificationModel;
import com.app.marinagoupiou.myhealthtech.model.ProfileModel;

import java.io.IOException;

import io.reactivex.Completable;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class MyHealthTechApplication extends Application {

    private static MockWebServer mockWebServer;
    private static UserController userController;
    private static Retrofit retrofit;

    private static ProfileModel profileModel;
    private static NotificationModel notificationModel;

    public MockWebServer getMockWebServer() {
        if(mockWebServer == null) {
            mockWebServer = new MockWebServer();
            mockWebServer.setDispatcher(new MainDispatcher(this));
            //startWebServer = Completable.fromCallable(() -> {
                try {
                    mockWebServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            //}).subscribeOn(Schedulers.newThread()).observeOn(Schedulers.newThread());
            //startWebServer.subscribe();
        }
        return mockWebServer;
    }

    public Retrofit getRetrofit() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(getMockWebServer().url("/"))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(new OkHttpClient.Builder().build())
                    .build();
        }
        return retrofit;
    }

    public UserController getUserController() {
        if(userController == null) {
            userController = new UserController(this.getRetrofit());
        }
        return userController;
    }

    public ProfileModel getProfileModel() {
        if(profileModel == null) {
            profileModel = new ProfileModel();
        }
        return profileModel;
    }

    public NotificationModel getNotificationModel() {
        if(notificationModel == null) {
            notificationModel = new NotificationModel();
        }
        return notificationModel;
    }
}
