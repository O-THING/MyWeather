package com.example.androidlearn4_2.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    public static SQLiteDatabase db;
    /* 初始化数据库信息*/
    public static void initDB(Context context){
        MyDBHelper dbHelper = new MyDBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    /* 新增一条省级记录*/
    public static long addProvince(String name, int id){
        ContentValues values = new ContentValues();
        values.put("provinceName",name);
        values.put("id",id);
        return db.insert("Province",null, values);
    }
    /* 新增一条市级记录*/
    public static long addCity(String name, int id, int provinceId){
        ContentValues values = new ContentValues();
        values.put("provinceName",name);
        values.put("id",id);
        values.put("provinceId",provinceId);
        return db.insert("City",null, values);
    }
    /* 新增一条县级记录*/
    public static long addCounty(String name, int weatherId, int cityId){
        ContentValues values = new ContentValues();
        values.put("provinceName",name);
        values.put("weatherId",weatherId);
        values.put("cityId",cityId);
        return db.insert("County",null, values);
    }
}
