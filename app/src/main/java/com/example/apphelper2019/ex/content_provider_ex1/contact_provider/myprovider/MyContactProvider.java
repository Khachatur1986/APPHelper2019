package com.example.apphelper2019.ex.content_provider_ex1.contact_provider.myprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

//https://www.tutorialspoint.com/android/android_content_providers.htm
//https://www.journaldev.com/13325/android-location-api-tracking-gps
//C:\Users\Khach\AndroidStudioProjects\ContentProviderExample\app\src\main\java\com\example\khach\contentproviderexample
//https://startandroid.ru/ru/uroki/vse-uroki-spiskom/166-urok-101-sozdaem-svoj-contentprovider.html

/*
add this lines on manifest application
        <provider
            android:name=".ex.content_provider_ex1.contact_provider.MyContactProvider"
            android:authorities="hello.world" />
 */
public class MyContactProvider extends ContentProvider {
    public static String TAG = "tag";
    /**
     * Uri
     * authority
     */
    static final String AUTHORITY = "hello.world";
    /**
     * path
     */
    static final String CONTACT_PATH = "contacts";

    /**
     * full Uri
     */
    static final Uri CONTACT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTACT_PATH);

    //data type
    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + CONTACT_PATH;

    //one line
    static final String CONTACT_CONTENT_TYPE_ITEM = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + CONTACT_PATH;

    //UriMatcher
    //common Uri
    static final int URI_CONTACTS = 1;

    //Uri with id
    static final int URI_CONTACTS_ID = 2;


    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH, URI_CONTACTS);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH + "/#", URI_CONTACTS_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;


    @Override
    public boolean onCreate() {
        Log.d(TAG, "MyContactProvider onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    //read
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {//projection = colomn
        Log.d(TAG, "query " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS: //common Uri
                Log.d(TAG, "URI_CONTACTS");
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = DBHelper.CONTACT_NAME + " ASC";
                }
                break;

            case URI_CONTACTS_ID: //with id
                Log.d(TAG, "URI_CONTACTS_ID");
                String id = uri.getLastPathSegment();
                Log.d(TAG, "getLastPathSegment = " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = selection + " AND " + DBHelper.CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong Uri " + uri.toString());
        }

        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(DBHelper.CONTACT_TABLE, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), CONTACT_CONTENT_URI);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        Log.d(TAG, "getType");
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS: //common Uri
                return CONTACT_CONTENT_TYPE;

            case URI_CONTACTS_ID: //with id
                return CONTACT_CONTENT_TYPE_ITEM;
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        Log.d(TAG, "insert");
        if (uriMatcher.match(uri) != URI_CONTACTS) {
            throw new IllegalArgumentException("Wrong Uri " + uri.toString());
        }
        db = dbHelper.getWritableDatabase();
        long rowId = db.insert(DBHelper.CONTACT_TABLE, null, values);
        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowId);
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "delete");
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS: //common Uri
                Log.d(TAG, "URI_CONTACTS");
                break;

            case URI_CONTACTS_ID: //with id
                Log.d(TAG, "URI_CONTACTS_ID");
                String id = uri.getLastPathSegment();
                Log.d(TAG, "getLastPathSegment = " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = selection + " AND " + DBHelper.CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong Uri " + uri.toString());
        }

        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(DBHelper.CONTACT_TABLE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        Log.d(TAG, "update");
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS: //common Uri
                Log.d(TAG, "URI_CONTACTS");
                break;

            case URI_CONTACTS_ID: //with id
                Log.d(TAG, "URI_CONTACTS_ID");
                String id = uri.getLastPathSegment();
                Log.d(TAG, "getLastPathSegment = " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = selection + " AND " + DBHelper.CONTACT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong Uri " + uri.toString());
        }

        db = dbHelper.getWritableDatabase();
        int cnt = db.update(DBHelper.CONTACT_TABLE, values, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return cnt;
    }
}
