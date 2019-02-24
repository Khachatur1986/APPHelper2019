package com.example.apphelper2019.ex.notification.custom_notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RemoteViews;

import com.example.apphelper2019.R;

/*
https://youtu.be/ToUR9i4Smfw
 */
public class Main24Activity extends AppCompatActivity {

    private Notification.Builder builder;
    private NotificationManager notificationManager;
    private int notification_unique_id;

    //RemoteViews allows to build a custom layouts and then combine that layout with notification
    private RemoteViews remoteViews;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main24);

        context = this;
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        remoteViews = new RemoteViews(getPackageName(), R.layout.notification_custom);

        remoteViews.setImageViewResource(R.id.notif_icon, android.R.drawable.btn_star_big_on);
        remoteViews.setTextViewText(R.id.notif_title, "Text");
        remoteViews.setProgressBar(R.id.progressBar, 100, 50, true);


        notification_unique_id = (int) System.currentTimeMillis();
        Intent button_intent = new Intent("button_clicked");
        button_intent.putExtra("unique_id", notification_unique_id);

        PendingIntent p_button_intent = PendingIntent.getBroadcast(context, 123, button_intent, 0);
        remoteViews.setOnClickPendingIntent(R.id.button, p_button_intent);

        findViewById(R.id.btn_custom_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("test");
/*                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    Intent intent = new Intent(context, Main24Activity.class);
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

                    builder = new Notification.Builder(context);
                    builder.setSmallIcon(android.R.drawable.star_big_on);//required
                    builder.setContentText("Title");//required
                    builder.setContentText("Text");//required
                    builder.setAutoCancel(true);
                    builder.setCustomBigContentView(remoteViews);
                    builder.setContentIntent(pendingIntent);

                    notificationManager.notify(notification_unique_id, builder.build());
                }*/

                createNotification("test", context);
            }
        });
    }

    public void createNotification(String aMessage, Context context) {
        final int NOTIFY_ID = 0; // ID of notification
        String id = context.getString(R.string.default_notification_channel_id); // default_channel_id
        String title = context.getString(R.string.default_notification_channel_title); // Default Channel
        Intent intent;
        PendingIntent pendingIntent;
        NotificationCompat.Builder builder;
        if (notificationManager == null) {
            notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notificationManager.getNotificationChannel(id);
            if (mChannel == null) {
                mChannel = new NotificationChannel(id, title, importance);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notificationManager.createNotificationChannel(mChannel);
            }
            builder = new NotificationCompat.Builder(context, id);
            intent = new Intent(context, Main24Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentTitle(aMessage)                            // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                    .setContentText(context.getString(R.string.app_name)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        }
        else {
            builder = new NotificationCompat.Builder(context, id);
            intent = new Intent(context, Main24Activity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
            builder.setContentTitle(aMessage)                            // required
                    .setSmallIcon(android.R.drawable.ic_popup_reminder)   // required
                    .setContentText(context.getString(R.string.app_name)) // required
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setAutoCancel(true)
                    .setContentIntent(pendingIntent)
                    .setTicker(aMessage)
                    .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                    .setPriority(Notification.PRIORITY_HIGH);
        }
        Notification notification = builder.build();
        notificationManager.notify(NOTIFY_ID, notification);
    }
}
