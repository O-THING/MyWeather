package com.example.androidlearn4_2.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    // 省表
    public static final String CREATE_PROVINCE=
            "CREATE TABLE Province (" +
            "id integer PRIMARY KEY,"+
            "provinceName text,"+
            "provinceCode integer)";
    // 市表
    public static final String CREATE_CITY=
            "CREATE TABLE City ("+
            "id integer PRIMARY KEY,"+
            "cityName text,"+
            "cityNode integer,"+
            "provinceId integer)";  // 外键（？）
    // 县表
    public static final String CREATE_COUNTY=
            "CREATE TABLE County("+
            "id integer PRIMARY KEY,"+
            "countyName text,"+
            "weatherId integer,"+
            "cityId integer)";


    public MyDBHelper(Context context){
        super(context,"WeatherForecast.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_PROVINCE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){

    }
}