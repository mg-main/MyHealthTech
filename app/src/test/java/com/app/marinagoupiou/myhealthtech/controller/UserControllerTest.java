package com.app.marinagoupiou.myhealthtech.controller;

import com.app.marinagoupiou.myhealthtech.controller.networking.MainDispatcher;
import com.app.marinagoupiou.myhealthtech.model.core.User;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import io.reactivex.Completable;
import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

/**
 * Created by marinagoupiou on 26/01/2018.
 */
public class UserControllerTest {

    private MockWebServer mockWebServer;
    private Retrofit retrofit;

    @Before
    public void setUp() throws Exception {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
        retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("/"))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        mockWebServer.shutdown();
    }

    @Test
    public void login() throws Exception {

        // Assign:
        UserController userController = new UserController(retrofit);
        mockWebServer.enqueue(new MockResponse().setBody("{ \"FirstName\":\"User\", \"LastName\":\"1\", \"Password\":\"password\", \"Type\":\"0\", \"UserName\":\"user1\", \"CurrentMedication\":[] }").setResponseCode(200));

        // Act:
        Object user = userController.login("123", "456");

        // Assert:
        Assert.assertNotNull("Returned Reactive object is null",user);
        Assert.assertTrue("Returned reactive object not of the correct type",user instanceof Single);

        Single<User> userSingle = (Single<User>) user;
        userSingle.subscribe(u -> {
            Assert.assertNotNull("Networking returned Null object",u);
            Assert.assertTrue("Returned Result not expected: FirstName", u.firstName.equals("User"));
            Assert.assertTrue("Returned Result not expected: LastName", u.lastName.equals("1"));
            Assert.assertTrue("Returned Result not expected: UserName", u.userName.equals("user1"));
        }, t -> fail("Networking operation failed"));
    }

    @Test
    public void logout() throws Exception {

        // Assign:
        UserController userController = new UserController(retrofit);
        mockWebServer.enqueue(new MockResponse().setResponseCode(204));

        // Act:
        Object logout = userController.logout("123");

        // Assert:
        Assert.assertNotNull("Returned Reactive object is null",logout);
        Assert.assertTrue("Returned reactive object not of the correct type",logout instanceof Completable);
        Completable logoutCompletable = (Completable) logout;

        logoutCompletable.subscribe(() -> {}, t -> fail("Networking operation failed"));

    }

}

/*


 */