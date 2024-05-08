package com.example.samsungfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "TheoremDB";

    // Таблица для хранения теорем
    private static final String TABLE_THEOREMS = "theorems";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String TABLE_USERS = "users";
    private static final String KEY_USER_ID = "user_id";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_THEOREMS_TABLE = "CREATE TABLE " + TABLE_THEOREMS +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TITLE + " TEXT," +
                KEY_CONTENT + " TEXT," +
                KEY_USER_ID + " INTEGER" + // добавляем новое поле для хранения id пользователя
                ")";
        db.execSQL(CREATE_THEOREMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THEOREMS);
        onCreate(db);
    }

    public long addTheorem(String title, String content, int userId) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_CONTENT, content);
        values.put(KEY_USER_ID, userId); // сохраняем id пользователя вместе с теоремой

        long result = db.insert(TABLE_THEOREMS, null, values);
        db.close();
        return result;
    }



    public List<Theorem> getAllTheorems(int userId) {
        List<Theorem> theoremList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_THEOREMS + " WHERE " + KEY_USER_ID + " = " + userId;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Theorem theorem = new Theorem();
                theorem.setId(Integer.parseInt(cursor.getString(0)));
                theorem.setTitle(cursor.getString(1));
                theorem.setContent(cursor.getString(2));
                theoremList.add(theorem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return theoremList;
    }
}
