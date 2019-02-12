package com.example.apphelper2019.ex.db_ex.todo_ex.db_config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

public class ToDoDB_Helper extends SQLiteOpenHelper {

    public static final String DB_NAME = "todo.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_TODO = "table_todo";
    public static final String COLUMN_TODO_ID = "task_id";
    public static final String COLUMN_TODO = "todo";

    //create table table_todo(task_id integer primary key, todo text not null);
    private static String CREATE_TABLE_TODO = "CREATE TABLE " + TABLE_TODO + "(" + COLUMN_TODO_ID + " INTEGER PRIMARY KEY, " + COLUMN_TODO + " TEXT NOT NULL)";


    public ToDoDB_Helper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            db.setForeignKeyConstraintsEnabled(true);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TODO);
    }

    //https://thebhwgroup.com/blog/how-android-sqlite-onupgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }
}
