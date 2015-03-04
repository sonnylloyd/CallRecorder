package com.aphidgrp.callrecorder.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sonny.lloyd on 04/03/2015.
 */
public class SQLHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "AphidGrpCallRecorder";

    public SQLHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CALL_LOG_TABLE = "CREATE TABLE call_log ( " +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "number TEXT, "+
                "author TEXT )";
        db.execSQL(CREATE_CALL_LOG_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS call_log");
        this.onCreate(db);
    }
}
