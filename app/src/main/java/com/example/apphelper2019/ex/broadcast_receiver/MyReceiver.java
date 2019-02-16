package com.example.apphelper2019.ex.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/*

add this line in manifest application scope
        <receiver
            android:name=".ex.broadcast_receiver.MyReceiver"
            android:enabled="true"
            android:exported="true"></receiver>
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving

    }
}
