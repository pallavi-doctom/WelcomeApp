package com.example.welcomeapp.repository;

import com.example.welcomeapp.model.UserData;
import com.example.welcomeapp.network.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {
    private final ApiService apiService;

    public UserRepository() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://your.api.base.url/") // Replace with your API endpoint
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
