package com.example.apphelper2019.ex.storage.external;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apphelper2019.R;
import com.example.apphelper2019.utils.ViewUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/*
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
     */
//https://youtu.be/OJaj4D9tbbY
public class Main19Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_external_save_public_data, btn_external_display, btn_external_display_private_files;
    private EditText et_external_data_to_save;
    private TextView tv_external_data_to_display;

    private static final int PERMISSION_REQUEST_CODE = 15;
    private boolean permissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main19);
        initViews();

        //checking the permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            Log.d("tag", "onCreate->if");

            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);

            Log.d("tag", "onCreate->after if");
        } else {
            Log.d("tag", "onCreate->else");
            permissionGranted = true;
            initListener();
        }
    }

    private void initListener() {
        if (isExternalStorageWritable()) {
            showMsg("Mounted");
            btn_external_save_public_data.setOnClickListener(this);
            btn_external_display.setOnClickListener(this);
            btn_external_display_private_files.setOnClickListener(this);
            showExternalFilesDir();
        } else {
            showMsg("Bad storage");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                //if request is cancelled, the result arrays are empty
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                        grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    initListener();
                    //permission was granted
                    Log.d("tag", "onRequestPermissionsResult->permission was granted");
                } else {
                    //permission denied
                    Log.d("tag", "onRequestPermissionsResult->permission denied");

                }
                break;
        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void initViews() {
        btn_external_save_public_data = findViewById(R.id.btn_external_save_public_data);
        btn_external_display = findViewById(R.id.btn_external_display);
        btn_external_display_private_files = findViewById(R.id.btn_external_display_private_files);
        et_external_data_to_save = findViewById(R.id.et_external_data_to_save);
        tv_external_data_to_display = findViewById(R.id.tv_external_data_to_display);
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public File getPublicAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("TAG", "Directory not created");
        }
        return file;
    }

    @Override
    public void onClick(View v) {

        if (permissionGranted) {
            switch (v.getId()) {
                case R.id.btn_external_save_public_data:
                    savePublicData();
                    break;
                case R.id.btn_external_display:
                    readPublicData();
                    break;
                case R.id.btn_external_display_private_files:
                    break;
            }
        }

    }

    private void showMsg(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void savePublicData() {
        ViewUtil.closeKeyboard(this);
        String data = et_external_data_to_save.getText().toString();
        if (!TextUtils.isEmpty(data)) {
            File fileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "customDirName");
            if (!fileDir.mkdirs()) {
                Log.e("TAG", "Directory not created. " + fileDir.getAbsolutePath());
                showMsg("Directory not created. " + fileDir.getAbsolutePath());
            } else {
                File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "customDirName", "MyFile.txt");
                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    fos.write(data.getBytes());
                    fos.close();
                    showMsg("Data saved successfully");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readPublicData() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + File.separator + "customDirName", "MyFile.txt");
        if (file.exists() && file.canRead()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                StringBuilder builder = new StringBuilder();
                while (true) {
                    int ch = fis.read();
                    if (ch == -1) {
                        break;
                    } else {
                        builder.append((char) ch);
                    }
                }
                fis.close();
                tv_external_data_to_display.setText(builder.toString());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showMsg("File not exist");
        }
    }

    private void showExternalFilesDir() {
        StringBuilder builder = new StringBuilder();
        builder.append("External private filesDir : " + getExternalFilesDir("kuku").getAbsolutePath() + "\n");
        builder.append("External public dir : " + Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "\n");
        tv_external_data_to_display.setText(builder.toString());
    }
}
