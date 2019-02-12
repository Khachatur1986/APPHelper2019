package com.example.apphelper2019.ex.db_ex.todo_ex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.apphelper2019.ex.db_ex.todo_ex.db_config.ToDoDB_Helper;
import com.example.apphelper2019.ex.db_ex.todo_ex.model.ToDo;

import java.util.ArrayList;
import java.util.List;

import static com.example.apphelper2019.ex.db_ex.todo_ex.db_config.ToDoDB_Helper.COLUMN_TODO;
import static com.example.apphelper2019.ex.db_ex.todo_ex.db_config.ToDoDB_Helper.COLUMN_TODO_ID;
import static com.example.apphelper2019.ex.db_ex.todo_ex.db_config.ToDoDB_Helper.TABLE_TODO;

public class TodoDB {
    private static final String TAG = TodoDB.class.getSimpleName();

    private Context context;
    private SQLiteDatabase sqLliteDatabase;
    private static TodoDB todoDBInstance;

    private TodoDB(Context context) {
        this.context = context;
        sqLliteDatabase = new ToDoDB_Helper(this.context,
                ToDoDB_Helper.DB_NAME,
                null,
                ToDoDB_Helper.DB_VERSION).getWritableDatabase();
    }

    public static TodoDB getInstance(Context context) {
        if (todoDBInstance == null) {
            todoDBInstance = new TodoDB(context);
        }
        return todoDBInstance;
    }

    //insert,delete,modify,query methods

    public boolean insert(String toDoItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TODO, toDoItem);

        return sqLliteDatabase.insert(TABLE_TODO, null, contentValues) > 0;
    }

    public boolean delete(int taskId) {
        return sqLliteDatabase.delete(TABLE_TODO, COLUMN_TODO_ID + " = " + taskId, null) > 0;
    }

    public boolean modify(int taskId, String newToDoItem) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_TODO, newToDoItem);

        return sqLliteDatabase.update(TABLE_TODO, contentValues, COLUMN_TODO_ID + " = " + taskId, null) > 0;
    }

    public List<ToDo> getAllToDos() {
        List<ToDo> toDoList = new ArrayList<ToDo>();

        Cursor cursor = sqLliteDatabase.query(TABLE_TODO, new String[]{COLUMN_TODO_ID, COLUMN_TODO}, null, null, null, null, null, null);

        if (cursor != null & cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ToDo toDo = new ToDo(cursor.getLong(0), cursor.getString(1));
                toDoList.add(toDo);
            }
        }
        cursor.close();
        return toDoList;
    }
}
