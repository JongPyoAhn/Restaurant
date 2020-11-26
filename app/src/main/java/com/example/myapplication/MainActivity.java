package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    public static int Tablenum;
    public static final int sub1 = 1001;
    public static SQLiteDatabase mDB;
    private DbOpenHelper mDBOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.number);
        String databasePath = Environment.getDataDirectory()+"/data/com.example.myapplication/databases/Database(SQLite).db";
        SQLiteDatabase database = this.openOrCreateDatabase(databasePath, Context.MODE_PRIVATE,null);
        database.execSQL("DROP TABLE sales");
        ImageButton number1 = (ImageButton) findViewById(R.id.number1);
        ImageButton number2 = ( ImageButton)findViewById(R.id.number2);
        ImageButton number3 = ( ImageButton)findViewById(R.id.number3);
        ImageButton number4 = ( ImageButton)findViewById(R.id.number4);
        ImageButton number5 = ( ImageButton)findViewById(R.id.number5);
        ImageButton number6 = ( ImageButton)findViewById(R.id.number6);

        mDBOpenHelper = new DbOpenHelper(this);
        try{
            mDBOpenHelper.open();
            mDBOpenHelper.insertColumn(1,"순대국",6500);
        }catch(SQLException e){e.printStackTrace();}



        number1.setOnClickListener(new View.OnClickListener(){
            @Override
           public void onClick(View v){
               Tablenum = 1;
               Intent intent = new Intent(getApplicationContext(), foodActivity.class);
               startActivity(intent);
           }
        });
        number2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Tablenum = 2;
                Intent intent = new Intent(getApplicationContext(), foodActivity.class);
                startActivity(intent);
            }
        });
        number3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Tablenum = 3;
                Intent intent = new Intent(getApplicationContext(), foodActivity.class);
                startActivity(intent);
            }
        });
        number4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Tablenum = 4;
                Intent intent = new Intent(getApplicationContext(), foodActivity.class);
                startActivity(intent);
            }
        });
        number5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Tablenum = 5;
                Intent intent = new Intent(getApplicationContext(), foodActivity.class);
                startActivity(intent);
            }
        });
        number6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Tablenum = 6;
                Intent intent = new Intent(getApplicationContext(), foodActivity.class);
                startActivity(intent);
            }
        });


    }

}
