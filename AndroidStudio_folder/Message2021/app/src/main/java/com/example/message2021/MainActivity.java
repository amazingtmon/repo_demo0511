package com.example.message2021;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //전송버튼을 클릭하면 호출된다.
    public void onSendMessage(View view){
        EditText et_msg = findViewById(R.id.et_msg);
        String msg = et_msg.getText().toString();
        Intent intent = new Intent(this, ReceiveActivity.class);

        intent.putExtra(ReceiveActivity.EXTRA_MESSAGE,msg);
        startActivity(intent);
    }

}