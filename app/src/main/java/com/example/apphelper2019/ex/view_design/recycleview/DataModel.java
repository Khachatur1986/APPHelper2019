package com.example.apphelper2019.ex.view_design.recycleview;

public class DataModel {
    private String imageName;
    private String imageUrl;

    public DataModel(String imageName, String imageUrl) {
        this.imageName = imageName;
        this.imageUrl = imageUrl;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
