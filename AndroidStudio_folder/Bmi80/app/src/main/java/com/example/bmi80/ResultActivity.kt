package com.example.bmi80

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

/*
* new 예약어가 없다.
* 함수뒤에 제네릭 사용
* 객체생성 후 즉시 초기화 할때 apply() let()....RecyclerView, XXXAdapter, ViewHolder
* getIntent().getIntExtra
*
* */

class ResultActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_result)

        val bmiTitle: TextView = findViewById(R.id.bmiTitle)
        val resultTitle: TextView = findViewById(R.id.resultTitle)

        val height = intent.getIntExtra("height", 0)
        val weight = intent.getIntExtra("weight", 0)
        Log.d("ResultActivity", "height: $height, weight: $weight")

        val bmi = weight / (height/ 100.0).pow(2.0)
        val resultText = when {
            bmi >= 35.0 -> "고도 비만"
            bmi >= 30.0 -> "중정도 비만"
            bmi >= 25.0 -> "경도 비만"
            bmi >= 23.0 -> "과체중"
            bmi >= 18.5 -> "정상체중"
            else -> "저체중"
        }

        bmiTitle.text = bmi.toString()
        resultTitle.text = resultText
    }
}