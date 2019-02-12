package com.example.apphelper2019.ex.retrofit_2_ex.send_obj_in_request_body.api.service;

import com.example.apphelper2019.ex.retrofit_2_ex.send_obj_in_request_body.api.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {

    @Headers({"Content-Type: application/x-www-form-urlencoded"})
    @FormUrlEncoded
    @POST("user/signup.php")
    Call<User> createAccount(@Field("username") String username, @Field("password") String password);
}