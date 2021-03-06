package com.example.apphelper2019;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.apphelper2019.ex.actionbar_ex.Main1Activity;
import com.example.apphelper2019.ex.broadcast_receiver.Main17Activity;
import com.example.apphelper2019.ex.certificate_ex.Main3Activity;
import com.example.apphelper2019.ex.content_provider_ex1.content_resolver.Main12Activity;
import com.example.apphelper2019.ex.db_ex.todo_ex.Main9Activity;
import com.example.apphelper2019.ex.dialogs.Main13Activity;
import com.example.apphelper2019.ex.login_reg_php_mysql.Main4Activity;
import com.example.apphelper2019.ex.looper_handler.Main28Activity;
import com.example.apphelper2019.ex.material_design.context_menu_ex.Main14Activity;
import com.example.apphelper2019.ex.material_design.menu_ex.Main11Activity;
import com.example.apphelper2019.ex.notification.custom_notification.Main24Activity;
import com.example.apphelper2019.ex.notification.notification_chanel.Main25Activity;
import com.example.apphelper2019.ex.observer_observable_ex.Main10Activity;
import com.example.apphelper2019.ex.retrofit_2_ex.send_obj_in_request_body.ui.Main6Activity;
import com.example.apphelper2019.ex.retrofit_2_ex.start.ui.Main5Activity;
import com.example.apphelper2019.ex.retrofit_2_ex.upload_file.ui.Main7Activity;
import com.example.apphelper2019.ex.retrofit_2_ex.upload_file.ui.Main8Activity;
import com.example.apphelper2019.ex.runtime_permission.Main20Activity;
import com.example.apphelper2019.ex.sensors.Main22Activity;
import com.example.apphelper2019.ex.service_ex.intent_service.Main16Activity;
import com.example.apphelper2019.ex.service_ex.service.Main15Activity;
import com.example.apphelper2019.ex.storage.external.Main19Activity;
import com.example.apphelper2019.ex.storage.internal.Main18Activity;
import com.example.apphelper2019.ex.view_design.recycle_and_card_view.Main27Activity;
import com.example.apphelper2019.ex.view_design.recycle_and_card_view_ex.Main23Activity;
import com.example.apphelper2019.ex.view_design.recycleview.Main26Activity;
import com.example.apphelper2019.ex.view_design.state_list_drawable.Main21Activity;
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
        Intent intent;
        switch (position) {
            case 0:
                intent = new Intent(getApplicationContext(), Main1Activity.class);
                startActivity(intent);
                break;
            case 1:
                intent = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(intent);
                break;
            case 2:
                intent = new Intent(getApplicationContext(), Main3Activity.class);
                startActivity(intent);
                break;
            case 3:
                intent = new Intent(getApplicationContext(), Main4Activity.class);
                startActivity(intent);
                break;
            case 4:
                intent = new Intent(getApplicationContext(), Main5Activity.class);
                startActivity(intent);
                break;
            case 5:
                intent = new Intent(getApplicationContext(), Main6Activity.class);
                startActivity(intent);
                break;
            case 6:
                intent = new Intent(getApplicationContext(), Main7Activity.class);
                startActivity(intent);
                break;
            case 7:
                intent = new Intent(getApplicationContext(), Main8Activity.class);
                startActivity(intent);
                break;
            case 8:
                intent = new Intent(getApplicationContext(), Main9Activity.class);
                startActivity(intent);
                break;
            case 9:
                intent = new Intent(getApplicationContext(), Main10Activity.class);
                startActivity(intent);
                break;
            case 10:
                intent = new Intent(getApplicationContext(), Main11Activity.class);
                startActivity(intent);
                break;
            case 11:
                intent = new Intent(getApplicationContext(), Main12Activity.class);
                startActivity(intent);
                break;
            case 12:
                intent = new Intent(getApplicationContext(), Main13Activity.class);
                startActivity(intent);
                break;
            case 13:
                intent = new Intent(getApplicationContext(), Main14Activity.class);
                startActivity(intent);
                break;
            case 14:
                intent = new Intent(getApplicationContext(), Main15Activity.class);
                startActivity(intent);
                break;
            case 15:
                intent = new Intent(getApplicationContext(), Main16Activity.class);
                startActivity(intent);
                break;
            case 16:
                intent = new Intent(getApplicationContext(), Main17Activity.class);
                startActivity(intent);
                break;
            case 17:
                intent = new Intent(getApplicationContext(), Main18Activity.class);
                startActivity(intent);
                break;
            case 18:
                intent = new Intent(getApplicationContext(), Main19Activity.class);
                startActivity(intent);
                break;
            case 19:
                intent = new Intent(getApplicationContext(), Main20Activity.class);
                startActivity(intent);
                break;
            case 20:
                intent = new Intent(getApplicationContext(), Main21Activity.class);
                startActivity(intent);
                break;
            case 21:
                intent = new Intent(getApplicationContext(), Main22Activity.class);
                startActivity(intent);
                break;
            case 22:
                intent = new Intent(getApplicationContext(), Main23Activity.class);
                startActivity(intent);
                break;
            case 23:
                intent = new Intent(getApplicationContext(), Main24Activity.class);
                startActivity(intent);
                break;
            case 24:
                intent = new Intent(getApplicationContext(), Main25Activity.class);
                startActivity(intent);
                break;
            case 25:
                intent = new Intent(getApplicationContext(), Main26Activity.class);
                startActivity(intent);
                break;
            case 26:
                intent = new Intent(getApplicationContext(), Main27Activity.class);
                startActivity(intent);
                break;
            case 27:
                intent = new Intent(getApplicationContext(), Main28Activity.class);
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
