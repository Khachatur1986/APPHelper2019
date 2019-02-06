package com.example.apphelper2019.ex.retrofit_2_ex.start.api.service;

import com.example.apphelper2019.ex.retrofit_2_ex.start.api.model.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubClient {
    //https://api.github.com/users/khachatur1986/repos
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(@Path("user") String user);
}
