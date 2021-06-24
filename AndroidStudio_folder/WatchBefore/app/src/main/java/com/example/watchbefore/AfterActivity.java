package com.example.watchbefore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class AfterActivity extends AppCompatActivity {
    final TextView tv_time = findViewById(R.id.tv_time);
    //스톱워치에 표시할 초
    private int seconds = 0;
    //스톱워치가 실행 중인가?
    //버튼에 대한 이벤트로 상태값이 바뀌고 사용은 다른 메소드에서 처리.
    private boolean running;//false -> start 버튼 -> true;
    /* 지금까지의 흐른 시간과 현재 스톱워치가 실행 중인지 상태 저장 */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //항상 super 를 선언해야한다.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after);
        if(savedInstanceState !=null){
            seconds=savedInstanceState.getInt("seconds");
            running=savedInstanceState.getBoolean("running");

        }
        Button btn_start = findViewById(R.id.btn_start);
        Button btn_stop = findViewById(R.id.btn_stop);
        Button btn_reset = findViewById(R.id.btn_reset);
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = true;
            }
        });
        runTimer();
    }

    //이전 상태를 저장하는 코드가 필요하다.
    public void onSaveInstanceState (Bundle saveInstanceState){
        super.onSaveInstanceState(saveInstanceState);
        saveInstanceState.putInt("seconds", seconds);
        saveInstanceState.putBoolean("running", running);
    }

    public void start(View v){
        running = true;
    }
    public void stop(View v){
        running = false;
    }
    public void reset(View v){
        running = false;
        seconds = 0;
    }

    /*
    여기서는 레이아웃의 텍스트뷰 레퍼런스를 얻고 seconds 변수의 값을 시, 분, 초
    형식으로 바꾼다음 텍스트뷰에 결과 표시
    만일 running 값이 true 이면 seconds 변수의 값을 1씩 증가시킬 것.
     */
    private void runTimer(){
        //final TextView tv_time = findViewById(R.id.tv_time);
        //Handler 사용하면 특정코드를 미래의 특정시점에 사용할 수 있음.
        final Handler handler = new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format(Locale.getDefault()
                        , "%d:%02d:%02d",hours,minutes,secs);
                tv_time.setText(time);
                if(running){
                    seconds++;
                }
                handler.postDelayed(this,1000);
            }
        });
    }//runTimer
}