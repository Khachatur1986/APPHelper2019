package com.example.apphelper2019.ex.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.apphelper2019.R;

import java.util.List;

/*
https://youtu.be/3GKXMdzfqwg
 */
public class Main22Activity extends AppCompatActivity {
    private ListView lv_sensor_container;

    private MySensorAdapter mySensorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22);
        lv_sensor_container = findViewById(R.id.lv_sensor_container);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL);
        mySensorAdapter = new MySensorAdapter(getApplicationContext(), R.layout.row_item_sensor_container, sensorList);
        lv_sensor_container.setAdapter(mySensorAdapter);
    }
}
