package com.example.adminactivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Dbhelper extends SQLiteOpenHelper {

    public static final String DBNAME="MAHENDRA";
    public static final int DBVERSION=1;

    public static final String STUTABLE="STUDENTTABLE";
    public static final String STUID="ID";
    public static final String STUNAME="NAME";
    public static final String STUEMAIL="EMAIL";
    public static final String STUPASS="PASSWORD";

    public static final String STUGROUP="STUDENTGROUP";
    public static final String ATANAME="ATT_NAME";
    public static final String ATAGROUP="ATT_GROUP";
    public static final String ATAPRE="ATT_PRESENT";
    public static final String ATAABS="ATT_ABSENT";

    public static final String CREATE_STU_TABLE=" create table " + STUTABLE + " ( " + STUID + " INTEGER PRIMARY KEY AUTOINCREMENT," + STUNAME + " TEXT NOT NULL, " + STUEMAIL + " TEXT NOT NULL, " + STUPASS + " TEXT NOT NULL ) ";
    public static final String CREATE_STU_GROUP=" create table " + STUGROUP + " ( " + ATANAME + " TEXT NOT NULL, " + ATAGROUP + " TEXT NOT NULL, " + ATAPRE + " TEXT NOT NULL, " + ATAABS + " TEXT NOT NULL ) ";
    public Dbhelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREATE_STU_TABLE);
        sqLiteDatabase.execSQL(CREATE_STU_GROUP);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + STUTABLE);
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + STUGROUP);
        onCreate(sqLiteDatabase);
    }
}
