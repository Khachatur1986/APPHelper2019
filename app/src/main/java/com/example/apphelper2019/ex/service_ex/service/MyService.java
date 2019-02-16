package com.example.apphelper2019.ex.service_ex.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.example.apphelper2019.R;

public class MyService extends Service {
    private MediaPlayer player;

    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, R.raw.armenian_songs_klarnet);
        player.start();
//        return super.onStartCommand(intent, flags, startId);
        return START_NOT_STICKY;//for not recreate the service
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        super.onDestroy();
    }
}
