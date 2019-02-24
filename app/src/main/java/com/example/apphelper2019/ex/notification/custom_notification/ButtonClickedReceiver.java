package com.example.apphelper2019.ex.notification.custom_notification;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
register receiver on manifest file
        <receiver
            android:name=".ex.notification.custom_notification.ButtonClickedReceiver"
            android:enabled="true"
            android:exported="true">
            <intent-filter>
                <action android:name="button_clicked" />
            </intent-filter>
        </receiver>
 */
public class ButtonClickedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(intent.getExtras().getInt("unique_id"));
        Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show();
    }
}
