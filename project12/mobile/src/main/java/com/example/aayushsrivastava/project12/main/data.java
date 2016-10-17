package com.example.aayushsrivastava.project12.main;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aayush Srivastava on 01-07-2016.
 */
public class data extends SQLiteOpenHelper {
    public data(Context context) {
        super(context, "track", null, 113);
    }

    @Override
    public void onCreate(SQLiteDatabase sql) {

        sql.execSQL("create table register(name string ,mobile varchar ,pin integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sql, int oldVersion, int newVersion) {
        sql.execSQL("drop table register");
        onCreate(sql);

    }
}

