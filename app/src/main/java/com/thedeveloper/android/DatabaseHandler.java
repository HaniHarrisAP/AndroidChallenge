package com.thedeveloper.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Users";
    private static final String TABLE_ADDRESS = "users";
    private static final String KEY_ID = "id";
    private static final String KEY_USER= "username";
    private static final String KEY_PASSWORD = "password";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_ADDRESS_TABLE = "CREATE TABLE " + TABLE_ADDRESS + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_USER + " TEXT,"
                + KEY_PASSWORD + " TEXT" + ")";

        db.execSQL(CREATE_ADDRESS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
        onCreate(db);
    }

    void addUsers(Users user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_USER, user.getUsername());
        values.put(KEY_PASSWORD, user.getPassword());
        db.insert(TABLE_ADDRESS, null, values);
        db.close();
    }

    Users getUsers1(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ADDRESS, new String[] { KEY_ID,
                        KEY_USER, KEY_PASSWORD}, KEY_USER + "=?"+KEY_PASSWORD + "=?",
                new String[] { String.valueOf(username), String.valueOf(password) }, null, null, null, null);
        Users user=null;
        if (cursor != null && cursor.moveToFirst())
        {


            user = new Users(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
        }


        return user;
    }

    Users getUsers2(String username) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ADDRESS, new String[] { KEY_ID,
                        KEY_USER, KEY_PASSWORD}, KEY_USER + "=?",
                new String[] { String.valueOf(username)}, null, null, null, null);

        Users user=null;
        if (cursor != null && cursor.moveToFirst())
        {


            user = new Users(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
        }


        return user;
    }
}
