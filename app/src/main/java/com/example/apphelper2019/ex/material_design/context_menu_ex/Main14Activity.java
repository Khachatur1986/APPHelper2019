package com.example.apphelper2019.ex.material_design.context_menu_ex;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.apphelper2019.R;

public class Main14Activity extends AppCompatActivity {
    private EditText et_long_click;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main14);

        et_long_click = findViewById(R.id.et_long_click);
        registerForContextMenu(et_long_click);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v.getId() == R.id.et_long_click) {
            menu.add(100, 1, 0, "Cut");
            menu.add(100, 2, 1, "Copy");
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "Cut", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                Toast.makeText(this, "Copy", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
