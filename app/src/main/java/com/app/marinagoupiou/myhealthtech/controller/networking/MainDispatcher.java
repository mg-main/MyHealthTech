package com.app.marinagoupiou.myhealthtech.controller.networking;

import android.content.Context;

import com.app.marinagoupiou.myhealthtech.model.core.LoginUser;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.mockwebserver.Dispatcher;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.RecordedRequest;

/**
 * Created by marinagoupiou on 26/01/2018.
 */

public class MainDispatcher extends Dispatcher {
    Context context = null;

    public MainDispatcher(Context context) {
        this.context = context;
    }

    private static final Pattern userloginPattern = Pattern.compile("user/login");
    @Override
    public MockResponse dispatch(RecordedRequest request) throws InterruptedException {
        String path = request.getPath();
        String[] pathToken = path.split("/");
        if(userloginPattern.matcher(path).find()) {
            if(request.getMethod().compareTo("POST") == 0) { // login
                return this.login(new GsonBuilder().create().fromJson(request.getBody().readUtf8(), LoginUser.class));
            } else if(request.getMethod().compareTo("DELETE") == 0) { // logout
                String username = pathToken[pathToken.length - 1];
                return this.logout(username);
            }
        }
        return new MockResponse().setResponseCode(400);
    }

    private MockResponse logout(String username) {
        return new MockResponse().setResponseCode(200);
    }

    private MockResponse login(LoginUser user) {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open(user.userName + ".json")));
            StringBuffer stringBuffer = new StringBuffer();
            String line = null;

            while((line =reader.readLine())!=null){

                stringBuffer.append(line).append("\n");
            }

            reader.close();

            return new MockResponse().setBody(stringBuffer.toString()).setResponseCode(200);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new MockResponse().setResponseCode(401); // Otherwise return unauthorized
    }

}
