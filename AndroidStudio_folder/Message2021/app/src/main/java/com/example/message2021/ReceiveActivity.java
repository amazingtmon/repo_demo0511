package com.example.message2021;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ReceiveActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        //인텐트에서 추가 정보 추출하기
        //___________는 액티비티를 시작시킨 인텐트를 반환하는데 이 인텐트를 이용해서
        //인텐트에 포함된 추가 정보를 추출할 수 있다.
        //데이터를 추출하는 방법은 어떤 데이터를 추가했느냐에 따라 달라진다.
        //문자열이면 - intent.getStringExtra("message")
        //다음은 name이라는 이름의 int를 추출하는 코드, default_value는 기본값으로 사용할 int값을 말함.
        //정수이면 - intent.getIntExtra("name", default_value);
        Intent intent = new Intent(this, ReceiveActivity.class);
        String msg = intent.getStringExtra(EXTRA_MESSAGE);
        //Toast.makeText(this,"읽어온 메시지:"+msg,Toast.LENGTH_LONG).show();
        TextView tv_msg = findViewById(R.id.text);
        tv_msg.setText(msg);
    }
}
