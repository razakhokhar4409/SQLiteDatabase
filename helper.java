package com.example.database_sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class helper extends SQLiteOpenHelper {

    private static final String dbname="test.db";
    private static final int version=1;

    public helper(Context context) {
        super(context, dbname, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql= "create table STUDENT (SID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, BATCH TEXT)";
        db.execSQL(sql);
    }

    public boolean insertvalues(String name, String batch){
        SQLiteDatabase db;
        db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("BATCH",batch);
        long result=db.insert("STUDENT",null,contentValues);
        if(result==-1){
            return  false;
        }else{
            return true;
        }
    }

    public boolean updatevalues(String name,String batch){

        SQLiteDatabase db;
        db=this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("NAME",name);
        contentValues.put("BATCH",batch);
        db.update("STUDENT",contentValues,"NAME = ?",new String[]{name});
        return true;

    }

    public Integer deletevalues(String name){
        SQLiteDatabase db= this.getWritableDatabase();
        return db.delete("STUDENT","NAME=?",new String[]{name});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table if exists STUDENT");
            onCreate(db);
    }

    public Cursor showallvalues(){
        SQLiteDatabase db;
        db= this.getWritableDatabase();
        Cursor cursor= db.rawQuery("SELECT NAME, BATCH FROM STUDENT", null);
        return cursor;
    }




}
