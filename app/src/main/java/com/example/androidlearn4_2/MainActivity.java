package com.example.androidlearn4_2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidlearn4_2.db.MyDBHelper;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {
    //private MyDBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //dbHelper = new MyDBHelper(this,"mydb",null,1);
        //dbHelper.getWritableDatabase();
        //LitePal.initialize(this);
    }
}
