package com.example.myapplication;

import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;


public class tcpActivity extends AppCompatActivity {


    private Handler mHandler;
    Socket socket;
    private String ip = "172.30.1.55"; // IP 주소
    private int port = 9999; // PORT번호

    public int selectMsg;
    
    //DB정보 담을 변수
    private String orderFood;
    private int countFood;
    private int totalPrice;
    private String str_db;

    private EditText et;
    private TextView msgTV;
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;

    @Override
    protected void onStop() {
        super.onStop();
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcp);
        mHandler = new Handler();


        et = (EditText) findViewById(R.id.EditText01);
        Button btn = (Button) findViewById(R.id.Button01);
      //  final TextView tv = (TextView) findViewById(R.id.TextView01);
        msgTV = (TextView)findViewById(R.id.chatTV);
        b1=(Button) findViewById(R.id.b1);
        b2=(Button) findViewById(R.id.b2);
        b3=(Button) findViewById(R.id.b3);
        b4=(Button) findViewById(R.id.b4);

        DbOpenHelper2 mDbOpenHelper2 = new DbOpenHelper2(this);
        mDbOpenHelper2.open();
        mDbOpenHelper2.create();
        Cursor iCursor = mDbOpenHelper2.selectColumns();
        //음식 쭉 돌리면서 검색
        while (iCursor.moveToNext()) {
            String tempOrder = iCursor.getString(0);
            int tempCount = iCursor.getInt(1);
            int tempPrice = iCursor.getInt(2);

            orderFood=tempOrder;
            countFood=tempCount;
            totalPrice=tempPrice;
            str_db = "테이블 주문 번호 : "+"1"+"주문한 음식 : "+orderFood+"음식 개수 : "+countFood + "총 액 : "+totalPrice;
        }
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (et.getText().toString() != null || !et.getText().toString().equals("")) {
                    selectMsg=5;
                    ConnectThread th =new ConnectThread();
                    th.start();
                }
            }
        });
        b1.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                selectMsg=1;
                ConnectThread th =new ConnectThread();
                th.start();
            }
        });
        b2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                selectMsg=2;
                ConnectThread th =new ConnectThread();
                th.start();
            }
        });
        b3.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                selectMsg=3;
                ConnectThread th =new ConnectThread();
                th.start();
            }
        });
        b4.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                selectMsg=4;
                ConnectThread th =new ConnectThread();
                th.start();
            }
        });
    }
    class ConnectThread extends Thread{

        public void run(){
            try{
                //소켓 생성
                InetAddress serverAddr = InetAddress.getByName(ip);
                socket =  new Socket(serverAddr,port);
                //입력 메시지
                String sndMsg="";
                switch(selectMsg)
                {
                    case 1:
                        sndMsg = str_db;
                        break;
                    case 2:
                        sndMsg = b2.getText().toString();
                        break;
                    case 3:
                        sndMsg = b3.getText().toString();
                        break;
                    case 4:
                        sndMsg = b4.getText().toString();
                        break;
                    case 5:
                        sndMsg = et.getText().toString();
                        break;

                }
               // String sndMsg = et.getText().toString();
                Log.d("=============", sndMsg);
                //데이터 전송
                PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
                out.println(sndMsg);
                //데이터 수신
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String read = input.readLine();
                //화면 출력
                mHandler.post(new msgUpdate(read));
                Log.d("=============", read);
                socket.close();

            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    // 받은 메시지 출력
    class msgUpdate implements Runnable {
        private String msg;
        public msgUpdate(String str) {
            this.msg = str;
        }
        public void run() {
            msgTV.setText(msgTV.getText().toString() + msg + "\n");
        }
    };
}