package com.example.apphelper2019.ex.view_design.state_list_drawable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.apphelper2019.R;

/*
https://www.journaldev.com/10929/android-interview-questions-and-answers
 */
public class Main21Activity extends AppCompatActivity {
    private EditText et_selection_cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main21);
        et_selection_cursor = findViewById(R.id.et_selection_cursor);

    }

    public void setSelectionCursor(View view) {
        if (et_selection_cursor != null) {
            et_selection_cursor.setSelection(Integer.parseInt(String.valueOf(et_selection_cursor.getText().toString().length())));
        }
    }
}
