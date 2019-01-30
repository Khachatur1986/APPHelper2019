package com.example.apphelper2019;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apphelper2019.ex.actionbar_ex.Main1Activity;
import com.example.apphelper2019.ex.volley_ex.Main2Activity;
import com.example.apphelper2019.utils.ExType;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private List<MainModel> models;
    private ListView lv_container;
    private ArrayAdapter<MainModel> adapter;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv_container = findViewById(R.id.lv_container);
        initModels();
        adapter = new MainAdapter(this.getApplicationContext(), R.layout.row_item_main_adapter, models);
        lv_container.setAdapter(adapter);
        lv_container.setOnItemClickListener(this);
    }

    private void initModels() {
        models = new ArrayList<>();
        MainModel model;
        ExType[] examples = ExType.values();
        for (ExType e : examples) {
            model = new MainModel(R.drawable.ic_assignment_ind_black_24dp, e.getTypeName());
            models.add(model);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent ;
        switch (position){
            case 0:
                intent = new Intent(getApplicationContext(), Main1Activity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                break;
        }
    }

    private void showPopUp() {
        // create a new ListView, set the adapter and item click listener
        ListView listViewItems = new ListView(this);
        listViewItems.setAdapter(adapter);
        listViewItems.setAdapter(adapter);

        // put the ListView in the pop up
        alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setView(listViewItems)
                .setTitle("alertDialog")
                .show();
    }
}
