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
    DB_TODO_EX("SQLITE_OPEN_HELPER_TODO"),
    OBSERVER_OBSERVABLE("OBSERVER_OBSERVABLE"),
    MENU_EX("MENU_EX"),
    CONTENT_RESOLVER("CONTENT_RESOLVER"),
    DIALOGS("DIALOGS"),
    CONTEXT_MENU_EX("CONTEXT_MENU_EX"),
    MY_SERVICE("MY_SERVICE"),
    MY_INTENT_SERVICE("MY_INTENT_SERVICE"),
    BROADCAST_RECEIVER("BROADCAST_RECEIVER"),
    INTERNAL_STORAGE("INTERNAL_STORAGE"),
    EXTERNAL_STORAGE("EXTERNAL_STORAGE"),
    RUNTIME_PERMISSION("RUNTIME_PERMISSION"),
    VIEW_DESIGN_STATE_LIST_DRAWABLE("STATE_LIST_DRAWABLE"),
    AVAILABLE_SENSORS("AVAILABLE_SENSORS"),
    RECYCLE_AND_CARD_VIEW("RECYCLE_AND_CARD_VIEW"),
    NOTIFICATION_CUSTOM("NOTIFICATION_CUSTOM"),
    NOTIFICATION_CHANNEL("NOTIFICATION_CHANNEL"),
    RECYCLE_VIEW("RECYCLE_VIEW"),
    HANDLER_LOOPER("HANDLER_LOOPER");

    private String typeName;

    ExType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
