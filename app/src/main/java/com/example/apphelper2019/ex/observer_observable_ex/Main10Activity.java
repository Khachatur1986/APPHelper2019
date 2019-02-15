package com.example.apphelper2019.ex.observer_observable_ex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.apphelper2019.R;

import java.util.Observable;
import java.util.Observer;

/*
Observer = ditord
Observable = ditarkvox
 */
public class Main10Activity extends AppCompatActivity implements Observer, View.OnClickListener {
    private Button btn_first;
    private Button btn_second;
    private Button btn_three;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        initViews();

        model = new Model();
        model.addObserver(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         *set default value from model.list
         */
        model.setInitialData();
    }

    private void initViews() {
        btn_first = findViewById(R.id.btn_first);
        btn_second = findViewById(R.id.btn_second);
        btn_three = findViewById(R.id.btn_three);
        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);
        btn_three.setOnClickListener(this);
    }

    /*
    this update function from Observer called when notifyObservers() is called from model (setValueAtIndex)
     */
    @Override
    public void update(Observable o, Object arg) {
        btn_first.setText(String.format("%s%d", getString(R.string.count), model.getValueAtIndex(0)));
        btn_second.setText(String.format("%s%d", getString(R.string.count), model.getValueAtIndex(1)));
        btn_three.setText(String.format("%s%d", getString(R.string.count), model.getValueAtIndex(2)));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_first:
                model.setValueAtIndex(0);
                break;
            case R.id.btn_second:
                model.setValueAtIndex(1);
                break;
            case R.id.btn_three:
                model.setValueAtIndex(2);
                break;
        }
    }
}
