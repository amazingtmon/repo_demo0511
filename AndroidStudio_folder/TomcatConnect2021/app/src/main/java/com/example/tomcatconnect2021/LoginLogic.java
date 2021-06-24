package com.example.tomcatconnect2021;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/*
* 새로 만든 스레드에서 UI객체에 직접 접근하는 것은 불가합니다.
* 핸들러 클래스를 사용하기도 하지만 핸들러를 사용하면 코드가 복잡해진다.
* 이러한 백그라운드 작업을 심플하게 만들기 위해 AsyncTask클래스를 사용한다.
* 스레드를 위한 동작 코드와 UI접근 코드를 한꺼번에 넣을 수 있다.
* */
public class LoginLogic extends AsyncTask<String, Void ,String> {
    //앱에서 입력한 아이디와 비번을 담아서 톰캣 서버에 전달한다.
    String sendMsg = null;
    //톰캣서버를 통해서 처리된 결과를 받아서 담는다.
    String receiveMsg = null;

    //반드시 재정의해야 할 메소드이다.
    //백그라운드에서 실행할 코드를 포함하는 메소드이다.
    @Override
    public String doInBackground(String... strings){
        //String apiURL = "http://192.168.0.206:8003/android/androidOracleConnect.jsp";
        String apiURL = "http://192.168.0.206:8003/member/login";
        try{
            URL url = new URL(apiURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("Connection-Type","application/x-www-form-urlencoded");
            con.setRequestMethod("POST");
            Log.i("LoginLogic","con: "+con.toString());

            //보내주기
            OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream());

            //톰캣 서버에 전송할 메시지 처리
            sendMsg = "mem_id="+strings[0]+"&mem_pw="+strings[1];
            osw.write(sendMsg);
            osw.flush();
            int responseCode = con.getResponseCode();//200 204 404 500
            Log.i("LoginLogic","responseCode: "+responseCode);
            BufferedReader br = null;
            if(responseCode == con.HTTP_OK){
                br = new BufferedReader(
                        new InputStreamReader(con.getInputStream(),"UTF-8"));
                String inputLine = null;
                StringBuilder sb_res = new StringBuilder();
                while((inputLine = br.readLine()) != null){
                    sb_res.append(inputLine);
                }

                receiveMsg = sb_res.toString();
                Log.i("LoginLogic","receiveMsg: "+receiveMsg);
            }
        } catch (Exception e){
            Log.i("LoginLogic","Exception: "+e.toString());

        }
        return receiveMsg;
    }
}
