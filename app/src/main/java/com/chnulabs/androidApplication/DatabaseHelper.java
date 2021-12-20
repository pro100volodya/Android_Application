package com.chnulabs.androidApplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "android_application";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlTExt = "CREATE TABLE Dish (\n" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, \n" +
                "kindOfDish TEXT(100) NOT NULL, \n" +
                "nameOfRestaurant TEXT(100), \n" +
                "cashExistsFlg BOOLEAN, \n" +
                "terminalExistsFlg BOOLEAN \n" +
                ");";
        sqLiteDatabase.execSQL(sqlTExt);

        populateDB(sqLiteDatabase);
    }

    private void populateDB(SQLiteDatabase db) {
        for (Info info : Info.get_Info()) {
            insertRow(db, info);
        }
    }

    private void insertRow(SQLiteDatabase db, Info info) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("kindOfDish", info.getKindOfDish());
        contentValues.put("nameOfRestaurant", info.getNameOfRestaurant());
        contentValues.put("cashExistsFlg", info.isCashExistsFlg());
        contentValues.put("terminalExistsFlg", info.isTerminalExistsFlg());
        db.insert("Dish", null, contentValues);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
