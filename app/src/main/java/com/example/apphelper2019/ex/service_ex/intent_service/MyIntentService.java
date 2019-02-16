package com.example.apphelper2019.ex.service_ex.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i("TAG", "Will be run in Worker thread, and stops automatically when task is done" );
    }
}
