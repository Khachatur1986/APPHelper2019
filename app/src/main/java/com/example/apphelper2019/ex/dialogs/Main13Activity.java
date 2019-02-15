package com.example.apphelper2019.ex.dialogs;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.apphelper2019.R;

public class Main13Activity extends AppCompatActivity implements View.OnClickListener {
    private Button btn1, btn2, btn3, btn4, btn5, btn6, btn7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main13);
        initViews();
    }

    private void initViews() {
        btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
        btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(this);
        btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(this);
        btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(this);
        btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(this);
        btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                showAlertDialog();
                break;
            case R.id.btn2:
                showFragmentDialog();
                break;
            case R.id.btn3:
                showDatePickerDialog();
                break;
            case R.id.btn4:
                showTimePickerDialog();
                break;
            case R.id.btn5:
                showProgressDialog();
                break;
            case R.id.btn6:
                shoCustomDialog();
                break;
            case R.id.btn7:
                showFragmentDialog(MyDialogFragment.ALERT_TAG);
                break;
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.ic_error_black_24dp)
                .setTitle(R.string.alert_title)
                .setMessage(R.string.alert_message);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showFragmentDialog() {
        MyDialogFragment dialogFragment = new MyDialogFragment();

        dialogFragment.show(getSupportFragmentManager(), "alert");
    }
    private void showFragmentDialog(String tag) {
        MyDialogFragment dialogFragment = new MyDialogFragment();

        dialogFragment.show(getSupportFragmentManager(), tag);
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, null, 2019, 2, 15);
        datePickerDialog.show();
    }

    private void showTimePickerDialog() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, null, 15, 36, true);
        timePickerDialog.show();
    }

    private void showProgressDialog() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Progress message");
        progressDialog.setTitle("Progress title");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(Main13Activity.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        progressDialog.show();
    }

    private void shoCustomDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        View view = LayoutInflater.from(this).inflate(R.layout.dialog_custom, null, false);
//        builder.setView(view);
        builder.setView(R.layout.dialog_custom);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
