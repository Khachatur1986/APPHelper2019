package com.example.apphelper2019.ex.notification.notification_chanel;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.apphelper2019.MainActivity;
import com.example.apphelper2019.R;

/*
https://youtu.be/ub4_f6ksxL0
https://www.youtube.com/watch?v=ub4_f6ksxL0
 */
public class Main25Activity extends AppCompatActivity implements View.OnClickListener {
    public static final String CHANNEL_1_ID = "Channel 1 id";
    public static final String CHANNEL_1_NAME = "Channel 1 name";
    public static final String CHANNEL_2_ID = "Channel 2 id";
    public static final String CHANNEL_2_NAME = "Channel 2 name";

    private EditText et_notification_channel_title, et_notification_channel_message;
    private Button btn_notification_channel_1, btn_notification_channel_2;

    private NotificationManager notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main25);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        initViews();
    }

    private void initViews() {
        et_notification_channel_title = findViewById(R.id.et_notification_channel_title);
        et_notification_channel_message = findViewById(R.id.et_notification_channel_message);
        btn_notification_channel_1 = findViewById(R.id.btn_notification_channel_1);
        btn_notification_channel_2 = findViewById(R.id.btn_notification_channel_2);

        btn_notification_channel_1.setOnClickListener(this);
        btn_notification_channel_2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_notification_channel_1:
                String title1 = et_notification_channel_title.getText().toString();
                String message1 = et_notification_channel_message.getText().toString();
                sendChanel1(title1, message1);
                break;
            case R.id.btn_notification_channel_2:
                String title2 = et_notification_channel_title.getText().toString();
                String message2 = et_notification_channel_message.getText().toString();
                sendChanel2(title2, message2);
                break;
        }
    }

    private void sendChanel2(String title, String message) {
        Intent intent = new Intent(Main25Activity.this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 123, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_1_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(android.R.drawable.btn_star)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);


        notificationManager.notify(1, builder.build());
    }

    private void sendChanel1(String title, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_2_ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(android.R.drawable.btn_star);
        notificationManager.notify(2, builder.build());
    }
}
