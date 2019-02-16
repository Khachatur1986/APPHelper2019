package com.example.apphelper2019.ex.service_ex.service;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.apphelper2019.R;

public class Main15Activity extends AppCompatActivity {
    private Button btn_start_service, btn_stop_service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main15);
        btn_start_service = findViewById(R.id.btn_start_service);
        btn_stop_service = findViewById(R.id.btn_stop_service);
        btn_start_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playMusic();
            }
        });
        btn_stop_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
            }
        });
    }

    private void playMusic() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private void stopMusic() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}
