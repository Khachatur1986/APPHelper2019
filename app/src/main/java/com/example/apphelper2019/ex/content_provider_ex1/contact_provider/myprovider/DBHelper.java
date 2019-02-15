package com.example.apphelper2019.ex.content_provider_ex1.contact_provider.myprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;

    public static final String CONTACT_TABLE = "contacts";

    public static final String CONTACT_ID = "id";
    public static final String CONTACT_NAME = "name";
    public static final String CONTACT_EMAIL = "email";

    private static final String DB_CREATE = "create table " + CONTACT_TABLE + "("
            + CONTACT_ID + " int primary key autoincrement, "
            + CONTACT_NAME + " text, "
            + CONTACT_EMAIL + " text " + ");";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE);
        ContentValues contentValues = new ContentValues();
        for (int i = 0; i <= 3; i++) {
            contentValues.put(CONTACT_NAME, "name" + i);
            contentValues.put(CONTACT_EMAIL, "email" + i);
            db.insert(CONTACT_TABLE, null, contentValues);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CONTACT_TABLE);
        onCreate(db);
    }
}
