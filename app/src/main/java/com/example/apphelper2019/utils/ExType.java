package com.example.apphelper2019.utils;

public enum ExType {
    ACTIONBAR_EX("actionbar_ex"),
    VOLLEY_EX_ONE("volley_ex_one"),
    CERTIFICATE_EX("certificate_ex"),
    LOGIN_REG_PHP_MYSQL("login reg php mysql");

    private String typeName;

    ExType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
