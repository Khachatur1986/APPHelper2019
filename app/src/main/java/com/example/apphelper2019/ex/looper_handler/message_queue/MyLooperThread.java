package com.example.apphelper2019.ex.looper_handler.message_queue;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * https://www.youtube.com/watch?v=998tPb10DFM
 */
public class MyLooperThread extends Thread {

    private Handler handler;
    @Override
    public void run() {
        /*
        This method identifies the calling thread and then create a looper and message queue for that thread
         */
        Looper.prepare();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
/*
precess incoming messages here
this will run in non ui.background thread
 */
            }
        };

        /*
        It starts the looper and put the thread out of the infinite loop
         */
        Looper.loop();
    }

}
