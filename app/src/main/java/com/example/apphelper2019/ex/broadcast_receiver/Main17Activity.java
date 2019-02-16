package com.example.apphelper2019.ex.broadcast_receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.apphelper2019.R;

import java.util.Iterator;
import java.util.Set;

public class Main17Activity extends AppCompatActivity {
    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Set<String> datas = intent.getExtras().keySet();
            Log.i("TAG", intent.getAction()+ "  : " + join(datas, "__"));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main17);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(broadcastReceiver, intentFilter);
    }

    private String join(Set<String> set, String sep) {
        String result = null;
        if (set != null) {
            StringBuilder sb = new StringBuilder();
            Iterator<String> it = set.iterator();
            if (it.hasNext()) {
                sb.append(it.next());
            }
            while (it.hasNext()) {
                sb.append(sep).append(it.next());
            }
            result = sb.toString();
        }
        return result;
    }

    private int isAirplaneModeOn(){
        int anInt = 0;
        try {
            anInt = Settings.System.getInt(getContentResolver(), Settings.System.AIRPLANE_MODE_ON);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        return anInt;
    }
}
