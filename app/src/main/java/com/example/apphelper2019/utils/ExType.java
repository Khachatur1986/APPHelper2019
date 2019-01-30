package com.example.apphelper2019.utils;

public enum ExType {
    ACTIONBAR_EX("ACTIONBAR_EX"),
    VOLLEY_EX_ONE("VOLLEY_EX_ONE");

    private String typeName;

    ExType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
