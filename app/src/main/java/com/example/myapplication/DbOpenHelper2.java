package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.view.View;

public class DbOpenHelper2 {
    private static final String DATABASE_NAME = "DatabaseReal(SQLite).db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    public static Context mCtx;




    class DatabaseHelper extends SQLiteOpenHelper {
        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL(DataBases.CreateDB._CREATE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL("DROP TABLE IF EXISTS "+DataBases.CreateDB._TABLENAME1);
            onCreate(db);
        }
    }

    public DbOpenHelper2(Context context){
        this.mCtx = context;
    }

    public DbOpenHelper2 open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void create(){
        mDBHelper.onCreate(mDB);
    }

    public void close(){
        mDB.close();
    }
    public static long insertColumn(String orderFood, int countFood, int totalFood, int tableNo){
        ContentValues values = new ContentValues();
        values.put(DataBases.CreateDB.ORDERFOOD, orderFood);
        values.put(DataBases.CreateDB.COUNTFOOD, countFood);
        values.put(DataBases.CreateDB.TOTALPRICE, totalFood);
        values.put(DataBases.CreateDB.TABLENO, tableNo);
        return mDB.insert(DataBases.CreateDB._TABLENAME1,null,values);
    }
    public static Cursor selectColumns(){
        return mDB.query(DataBases.CreateDB._TABLENAME1, null, null,null,null,null,null,null);
    }
    //DB컬럼전체삭제
    public void deleteAllColumns(){
        mDB.delete(DataBases.CreateDB._TABLENAME1,null,null);
    }

}

