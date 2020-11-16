package com.example.myapplication;

import android.os.Bundle;
import android.os.Debug;
import android.view.View;
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
   private int soonPrice; //순대국 가격(DB로 가져올거임)
    private boolean menuSelect1=true; //첫번째 음식을 주문 했는지?
    public int sumPrice1; // 첫번째 음식줄 가격의 총합
    private ImageView plusImg;
    private TextView numberFood1;
    private TextView selectedFood1;
    private TextView price;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food);
        plusImg = (ImageView) findViewById(R.id.plusFood1);
        numberFood1 = (TextView) findViewById(R.id.numberFood1);
        selectedFood1 = (TextView) findViewById(R.id.selectedFood1);
        price = (TextView)findViewById(R.id.price);

    }


    //음식 갯수 더하기 구현
    public void plusClick (View v)
    {
        if(menuSelect1) {
            Toast.makeText(this, "원하는 음식을 선택해 주세요.", Toast.LENGTH_SHORT).show();
            menuSelect1=true;
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
                menuSelect1=true;
            }
        }

        else {  numberFood1.setText(String.valueOf(--counter1));
                priceSetting1(); }
        }
    //담기 구현
    public void basket1Click(View v)
    {
        numberFood1.setText(String.valueOf(++counter1));
        priceSetting1();
        task = new Task();
        task.onPostExecute(task.receiveMsg);
        selectedFood1.setText(task.name);
        soonPrice=Integer.parseInt(task.price);
        menuSelect1=false;
    }
    //(+,-)에따른 가격 셋팅
    public void priceSetting1(){
        sumPrice1=soonPrice*counter1;

        price.setText(String.valueOf(sumPrice1)+"원");
    }
}
