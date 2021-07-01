package com.example.apptemplate2021;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.apptemplate2021.dto.MemberDTO;
import com.example.apptemplate2021.util.VolleyCallback;
import com.example.apptemplate2021.util.VolleyQueueProvider;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    //로그인 성공시 톰캣 서버로부터 받아올 이름 담기
    private String sname = null;
    EditText et_id = null;
    EditText et_pw = null;
    private Map<String,String> pmap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "버튼 호출 성공");
                login();
            }
        });

    }

    public void onStart(){
        super.onStart();
        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);
        String id = et_id.getText().toString();
        String pw = et_pw.getText().toString();
        pmap.put("mem_id", id);
        pmap.put("mem_pw", pw);
    }

    //사용자로부터 받아온 값 파라미터로 넘겨 받음
    public void loginProcess(Map<String,String>) pmap {
        VolleyQueueProvider.initRequestQueue(this);
        VolleyQueueProvider.openQueue();
        VolleyQueueProvider.callbackVolley(new VolleyCallback() {
            @Override
            public void onResponse(String response) {
                List<Map<String,Object>> resultList =new Gson().fromJson(response, List.class);
                if(resultList.size() == 0){
                    Toast.makeText(getApplicationContext()
                                    ,"아이디가 존재하지 않습니다."
                                    , Toast.LENGTH_LONG).show();
                }else if(resultList.get(0).get("mem_name").equals("-1")){
                    Toast.makeText(getApplicationContext()
                                    ,"비밀번호가 일치하지 않습니다."
                                    ,Toast.LENGTH_LONG).show();
                }else {
                    for(Map.Entry dtoTOMAP : resultList.get(0).entrySet()){
                        if(dtoTOMAP.getKey().equals("mem_name")){
                            MemberDTO.getInstance().setMem_name(dtoTOMAP.getValue().toString());;)
                        }
                    }
                }
            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, "login/jsonLogin", pmap);
    }

    private void login() {
        Log.i(this.getClass().getName(), "login 호출 성공");
        EditText et_id = findViewById(R.id.et_id);
        EditText et_pw = findViewById(R.id.et_pw);
        String apiURL = "http://192.168.0.206:8003/login/postLogin";

        try {
            StringRequest request = new StringRequest(Request.Method.POST, apiURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    sname = response;
                    println(sname);
                }
            }
        }catch (){

        }
    }

    public void println(String data){
        Log.i(this.getClass().getName(), data+"\n");
    }
}
