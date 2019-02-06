package com.example.apphelper2019.ex.retrofit_2_ex.send_obj_in_request_body.api.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName(value = "id")
    private Integer id;

    @SerializedName(value = "username")
    private String username;

    @SerializedName(value = "password")
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
