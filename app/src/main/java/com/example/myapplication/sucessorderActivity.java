//package com.example.myapplication;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class sucessorderActivity extends AppCompatActivity {
//    private Button btn; //기타 주문하기 버튼
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sucessorder);
//
//
//        btn = (Button)findViewById(R.id.successOrderButton);
//        btn.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), tcpActivity.class);
//                startActivity(intent);
//
//            }
//        });
//    }
//}
