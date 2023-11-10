package com.example.varosok;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "varosok.db";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "varosok";

    private static final String COL_ID = "id";
    private static final String COL_NAME = "nev";
    private static final String COL_COUNTRY = "orszag";
    private static final String COL_POPULATION = "lakossag";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT UNIQUE NOT NULL, " +
                COL_COUNTRY + " TEXT NOT NULL, " +
                COL_POPULATION + " INTEGER NOT NULL" +
                ");";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(
                "DROP TABLE IF EXISTS " + TABLE_NAME
        );
        onCreate(sqLiteDatabase);
    }

    public boolean addToTable(String name, String country, int population) {
        if (!canBeAdded(name)) {
            return false;
        }

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_COUNTRY, country);
        values.put(COL_POPULATION, population);

        long result = database.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public boolean canBeAdded(String name) {
        if (checkForName(name)) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean checkForName(String name) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, new String[] {COL_ID, COL_NAME, COL_COUNTRY, COL_POPULATION},
                COL_NAME + " = ?", new String[] {name},
                null, null, null);
        return cursor.getCount() > 0;
    }

    public String searchResultList(String country) {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.query(TABLE_NAME, new String[] {COL_ID, COL_NAME, COL_COUNTRY, COL_POPULATION},
                COL_COUNTRY + " = ?", new String[] {country},
                null, null, null);
        String searchRes = "";
        if (cursor.moveToFirst()) {
            do {
                searchRes += (cursor.getString(1)) + ", ";
            } while (cursor.moveToNext());
        }
        cursor.close();
        if (!searchRes.trim().equals("")){
            return searchRes;
        } else {
            return "Nem található rekord a következő adattal: " + country;
        }
    }
}
