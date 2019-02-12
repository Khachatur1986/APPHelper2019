package com.example.apphelper2019.utils;

public enum ExType {
    ACTIONBAR_EX("actionbar_ex"),
    VOLLEY_EX_ONE("volley_ex_one"),
    CERTIFICATE_EX("certificate_ex"),
    LOGIN_REG_PHP_MYSQL("login reg php mysql"),
    RETROFIT_2_start("retrofit 2 start"),
    RETROFIT_2_send_obj("send_obj_in_request_body"),
    RETROFIT_2_upload_file("RETROFIT_2_upload_file"),
    RETROFIT_2_upload_file2("RETROFIT_2_upload_file2"),
    DB_TODO_EX("DB_SQLITE_OPEN_HELPER_TODO_EX");

    private String typeName;

    ExType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
