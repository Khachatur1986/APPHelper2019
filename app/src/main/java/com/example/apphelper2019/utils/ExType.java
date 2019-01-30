package com.example.apphelper2019.utils;

public enum ExType {
    ACTIONBAR_EX("ACTIONBAR_EX");

    private String typeName;

    ExType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
