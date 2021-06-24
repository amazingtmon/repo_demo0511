package com.example.workout80;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
/*
디바이스가 폰인지 태블릿인지에 따라 동작 방법을 다르게 하고 싶다면 어떻게 할까요?
여러 액티비티에서 재활용할 수 있는 모듈화 코드 컴포넌트인 프래그먼트가 필요함.
기본 프래그먼트와 리스트 프래그먼트를 생성하는 방법
액티비티에 프래그먼트를 추가하는 방법
프래그먼트와 액티비티가 서로 통신하는 방법을 설명

프래그먼트를 이용하면 두 개의 액티비티에서 코드를 중복하지 않을 수 있다.
그럼 프래그먼트란 뭘까요?

프래그먼트는 재사용할 수 있는 컴포넌트나 하위 액티비티와 같다.
운동목록과 자세한 운동 정보를 각각의 프래그먼트로 생성한 다음 레이아웃에서 프래그먼트를 재사용할 수 있다.

액티비티는 레이아웃을 갖습니다.
프래그먼트 코드에 레이아웃의 모든 컨트롤을 포함하면 앱의 어디에서나 프래그먼트를 쉽게 재사용할 수 있다.
 */

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.Listener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onShowDetails(View view){
        Intent intent = new Intent(this,DetailActivity.class);
        startActivity(intent);
    }

}