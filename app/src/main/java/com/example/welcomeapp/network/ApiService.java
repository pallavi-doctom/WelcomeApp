package com.example.welcomeapp.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;

import com.example.welcomeapp.model.UserData;

public interface ApiService {

    @GET("users")
    Call<List<UserData>> getUsers();

    @POST("user")
    Call<UserData> createUser(@Body UserData userData);
}
