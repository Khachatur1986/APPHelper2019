package com.example.apphelper2019.ex.content_provider_ex1.content_resolver;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.apphelper2019.R;

import java.util.ArrayList;

/*
    <uses-permission android:name="android.permission.READ_CONTACTS" />

 */
//https://www.youtube.com/watch?v=SgzveSeDKwY&t=10s
//https://youtu.be/SgzveSeDKwY
public class Main12Activity extends AppCompatActivity {

    private final int MY_PERMISSION_REQUEST = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        //checking the permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            Log.d("tag", "onCreate->if");

            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, MY_PERMISSION_REQUEST);

            Log.d("tag", "onCreate->after if");
        } else {
            Log.d("tag", "onCreate->else");

            fetchContacts();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSION_REQUEST:
                //if request is cancelled, the result arrays are empty
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted
                    Log.d("tag", "onRequestPermissionsResult->permission was granted");
                    fetchContacts();
                } else {
                    //permission denied
                    Log.d("tag", "onRequestPermissionsResult->permission denied");

                }
                break;
        }
    }

    private void fetchContacts() {
        ArrayList<String> contacts = new ArrayList<>();
        ContentResolver contentResolver = getContentResolver();

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = null;

        Cursor cursor = contentResolver.query(uri, projection, selection, selectionArgs, sortOrder);

        while (cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contacts.add(name + "  :  " + number);
        }

        ((ListView) findViewById(R.id.lv_list_contacts))
                .setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, contacts));
    }
}
