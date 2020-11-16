package com.example.myapplication;

import android.os.AsyncTask;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Task extends AsyncTask<String, Void, String> {
    public static String ip ="172.30.1.55"; //자신의 IP번호
    public String sendMsg, receiveMsg;
    private String key;
    String serverip = "http://172.30.1.55:9090/DBConnection/DBtoAndroid.jsp"; // 연결할 jsp주소
    public static String name;
    public static String price;
    void task(String sendmsg){
        this.sendMsg = sendmsg;
    }
    //doInBackground의 매개값이 문자열 배열인데, 보낼 값이 여러개일 경우를 위해 배열로 한다.
    @Override
    protected String doInBackground(String... strings) {
        String get_key= strings[0];

        try {
            String str;
            URL url = new URL(serverip); //보낼 JSP 주소를 작성
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); //URL연결
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");//헤더값 설정
            conn.setRequestMethod("POST"); //데이터를 POST방식으로 전송
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
            //유니코드 문자열을 바이트 스트림으로 변환해준다.(하드디스크로 보낼때 사용)
            sendMsg = "name="+strings[0];
            // 보낼 정보 GET방식으로 작성 sendMsg

            osw.write(sendMsg); //OutpuStreamWriter에 담아서 전송
            osw.flush(); //버퍼에있는데이터를 모두 출력시키고 버퍼를 비우는 역할.


            if(conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                //바이트스트림에서 유니코드문자열로 변환해줌. 하드디스크->문자스트림(서버에서 읽어온다.)
                BufferedReader reader = new BufferedReader(tmp);//버퍼리더생성(줄단위로 읽어오기위함)
                StringBuffer buffer = new StringBuffer();//값을 변경할수도, 추가할 수도 있다.
                //String과 비교대상. String은 불변이지만, StringBuffer는 가변적.
                //문자열의 추가,삭제,수정등이 빈번하게 이루어질때 사용
                while ((str = reader.readLine()) != null) {//파일에서 데이터를 읽어옴
                    buffer.append(str);
                }
                receiveMsg = buffer.toString();//버퍼에담고있는 2진수를 문자열로 변환
                reader.close();
                conn.disconnect();

            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return receiveMsg;
    }
  //JSON으로 데이터 받기
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        try {
            JSONArray jarray = new JSONObject(result).getJSONArray(key);
            if(jarray!=null) {
                JSONObject jsonObject = jarray.getJSONObject(0);
                name = jsonObject.getString("name");
                price = jsonObject.getString("price");

            }
        } catch (Exception e) {

        }
    }
}
