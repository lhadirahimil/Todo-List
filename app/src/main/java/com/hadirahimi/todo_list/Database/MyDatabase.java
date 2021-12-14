package com.hadirahimi.todo_list.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDatabase extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "db_todo.db";
    private static int DATABASE_VERSION = 2;
    public final String TABLE_NAME = "tbTodo";
    public String ID = "id";
    public String TITLE = "title";
    public String CHECKED = "checked";
    public String CATEGORY = "category_id";

    public MyDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT," + TITLE + " TEXT NOT NULL," + CHECKED + " INTEGER(1), " + CATEGORY + " INTEGER(2))";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query_update = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(query_update);
        onCreate(sqLiteDatabase);
    }

    public void insert(String title, int category_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TITLE, title);
        cv.put(CATEGORY, category_id);
        cv.put(CHECKED, 0);
        db.insert(TABLE_NAME, null, cv);
    }

    public Cursor allData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY "+ID+" DESC", null);
    }

    public Cursor recentData() {
        SQLiteDatabase db = this.getReadableDatabase();

        return db.rawQuery("select * from " + TABLE_NAME + " order by " + ID + " desc limit 3;", null);
    }

    public void isChecked(int id, int isChecked) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME + " SET checked = " + isChecked + " WHERE id = " + id);
    }

    public boolean deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, ID + "=" + id, null) > 0;
    }


}
