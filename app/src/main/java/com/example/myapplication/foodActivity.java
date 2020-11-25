package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class foodActivity extends AppCompatActivity {
    public int counter1 = 0;//첫번째 음식 주문 갯수 카운트
    public int sumPrice; //가격의 총합
    private String Name1;
   private int Price1; //순대국 가격(DB로 가져올거임)
    private boolean menuSelect1=true; //첫번째 음식을 주문 했는지?
    private DbOpenHelper2 mDBOpenHelper2;// sales테이블 DB사용

    private ImageView plusImg;
    private TextView numberFood1;
    private TextView selectedFood1;
    private TextView price;
    private Button order;
    private Button basket1;
    public static Context context_main;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);

        plusImg = (ImageView) findViewById(R.id.plusFood1);
        numberFood1 = (TextView) findViewById(R.id.numberFood1);
        selectedFood1 = (TextView) findViewById(R.id.selectedFood1);
        price = (TextView)findViewById(R.id.price);
        order = (Button)findViewById(R.id.Buy);
        basket1 = (Button)findViewById(R.id.basket1) ;
        //담기 구현
        basket1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                 selectedFood1.setText(Name1);
                 numberFood1.setText(String.valueOf(++counter1));
                 priceSetting1();
                 menuSelect1=false;
            }
        });

        DbOpenHelper mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mDbOpenHelper.create();
        Cursor iCursor = mDbOpenHelper.selectColumns();
        //음식 쭉 돌리면서 검색
        while (iCursor.moveToNext()) {
            int tempID = iCursor.getInt(0);
            int tempNo = iCursor.getInt(1);
            String tempName = iCursor.getString(2);
            int tempPrice = iCursor.getInt(3);
//
            // 음식번호가 1번이면 1번 가격에 저장.
            // 음식번호란? 코드에서 편하게 분류하려고 1번순대국의 
            // 이름과 가격을 변수에 넣고 UI에 표시하기 위함.
            if (tempNo==1) {
                Price1 = tempPrice;
                Name1 = tempName;
            }
        }
//        priceGetting1();
        mDBOpenHelper2 = new DbOpenHelper2(this);
        //주문버튼 구현
        order.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(menuSelect1) {
                    Toast.makeText(getApplicationContext(), "원하는 음식을 선택해 주세요.", Toast.LENGTH_SHORT).show();
                    //menuSelect1=true;
                }
                else {
                    //주문음식, 음식갯수, 총액수 얻기
                    Name1 = selectedFood1.getText().toString();
                    counter1 = Integer.parseInt(numberFood1.getText().toString());

                    try{mDBOpenHelper2.open();
                    mDBOpenHelper2.insertColumn(Name1,counter1,sumPrice);
                    }catch(SQLException e){e.printStackTrace();}
                    Intent intent = new Intent(getApplicationContext(), sucessorderActivity.class);
                    startActivity(intent);
                }
            }
        });
    }


    //음식 갯수 더하기 구현
    public void plusClick (View v)
    {
        if(menuSelect1) {
            Toast.makeText(this, "원하는 음식을 선택해 주세요.", Toast.LENGTH_SHORT).show();
            menuSelect1=false;
        }
        else {
            numberFood1.setText(String.valueOf(++counter1));//valueof의 인자값은 문자열이나 원시데이터형이됨.
            priceSetting1();
        }
    }
    //음식 갯수 빼기 구현
    public void minusClick (View v)
    {
        if(counter1==0){
            if(menuSelect1) {
                Toast.makeText(this, "선택된 음식이 없습니다.", Toast.LENGTH_SHORT).show();
                menuSelect1=false;
            }
        }

        else {  numberFood1.setText(String.valueOf(--counter1));
                priceSetting1(); }
        }

    //(+,-)에따른 가격 셋팅
    public void priceSetting1(){
        sumPrice=Price1*counter1;

        price.setText(String.valueOf(sumPrice)+"원");
    }
    //주문음식, 음식갯수, 총액수
    //Name1
    //counter1
    //sumPrice1

}
