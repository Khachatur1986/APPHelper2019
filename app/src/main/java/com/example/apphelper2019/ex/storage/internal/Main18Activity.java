package com.example.apphelper2019.ex.storage.internal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphelper2019.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 */
public class Main18Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_save, btn_display, btn_display_private_files;
    private EditText et_data_to_save;
    private TextView tv_data_to_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main18);
        initViews();
        showCashFileDir();
    }

    private void initViews() {
        btn_save = findViewById(R.id.btn_save);
        btn_display = findViewById(R.id.btn_display);
        btn_display_private_files = findViewById(R.id.btn_display_private_files);
        et_data_to_save = findViewById(R.id.et_data_to_save);
        tv_data_to_display = findViewById(R.id.tv_data_to_display);

        btn_save.setOnClickListener(this);
        btn_display.setOnClickListener(this);
        btn_display_private_files.setOnClickListener(this);
    }

    private void saveData() {
        String text = et_data_to_save.getText().toString();
        if (!TextUtils.isEmpty(text)) {
            try {
                FileOutputStream fileOutputStream = openFileOutput("file.txt", MODE_APPEND);
                fileOutputStream.write(text.getBytes());
                fileOutputStream.close();
                Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    private void displayData() {
        try {
            FileInputStream fileInputStream = openFileInput("file.txt");
            StringBuilder builder = new StringBuilder();

            while (true) {
                int ch = fileInputStream.read();
                if (ch == -1) {
                    break;
                } else {
                    builder.append((char) ch);
                }
            }
            fileInputStream.close();
            tv_data_to_display.setText(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayDataUsingFilesDir() {
        try {
            File file = new File(getFilesDir(), "file.txt");
            FileInputStream fileInputStream = new FileInputStream(file);
            StringBuilder builder = new StringBuilder();

            while (true) {
                int ch = fileInputStream.read();
                if (ch == -1) {
                    break;
                } else {
                    builder.append((char) ch);
                }
            }
            fileInputStream.close();
            tv_data_to_display.setText(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCashFileDir() {
        StringBuilder builder = new StringBuilder();
        builder.append("CacheDir :" + getCacheDir().getAbsolutePath() + "\n");
        builder.append("FilesDir : " + getFilesDir().getAbsolutePath());
        tv_data_to_display.setText(builder.toString());
    }

    private void displayPrivateFiles() {
        tv_data_to_display.setText("");
        String[] files = fileList();
        for (String fileName : files) {
            tv_data_to_display.append(fileName + "\n");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_save:
                saveData();
                break;
            case R.id.btn_display:
                displayData();
                break;
            case R.id.btn_display_private_files:
                displayPrivateFiles();
                break;
        }
    }
}
