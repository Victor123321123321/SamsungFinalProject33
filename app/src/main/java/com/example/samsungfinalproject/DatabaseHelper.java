package com.example.samsungfinalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TheoremDB";

    // Таблица для хранения теорем
    private static final String TABLE_THEOREMS = "theorems";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_CONTENT = "content";
    private static final String COLUMN_IMAGE = "image"; // Путь к изображению

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Создание таблицы теорем
        String CREATE_THEOREMS_TABLE = "CREATE TABLE " + TABLE_THEOREMS +
                "(" +
                KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_TITLE + " TEXT," +
                KEY_CONTENT + " TEXT," +
                COLUMN_IMAGE + " TEXT" + // Новый столбец для пути к изображению
                ")";
        db.execSQL(CREATE_THEOREMS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Удаление старой таблицы теорем, если она существует
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_THEOREMS);
        // Создание новой таблицы
        onCreate(db);
    }

    // Метод для добавления новой теоремы в базу данных
    // Вставьте этот код в ваш класс DatabaseHelper
    public long addTheorem(String title, String content) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_CONTENT, content);

        long result = db.insert(TABLE_THEOREMS, null, values);
        db.close();
        return result;
    }


    // Метод для получения списка всех теорем из базы данных
    public List<Theorem> getAllTheorems() {
        List<Theorem> theoremList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_THEOREMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Theorem theorem = new Theorem();
                theorem.setId(Integer.parseInt(cursor.getString(0)));
                theorem.setTitle(cursor.getString(1));
                theorem.setContent(cursor.getString(2));
//                theorem.setImagePath(cursor.getString(3)); // Получаем путь к изображению
                theoremList.add(theorem);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return theoremList;
    }
}
