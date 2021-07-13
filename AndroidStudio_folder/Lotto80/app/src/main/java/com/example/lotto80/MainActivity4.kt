package com.example.lotto80

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker

class MainActivity4 : AppCompatActivity() {
    //선언뒤에 lazy가 오면 activity_main4.xml을 스캔할 때 까지 기다린다.
    private val btn_run: Button by lazy {
        findViewById(R.id.btn_run)
    }

    //NumberPicker의 범위를 정해준다.
    private val numberPicker: NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker)

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45
        //자동채번 버튼 구현
        initRunButton()

    }////////////// end of Create

    private fun initRunButton() {
        btn_run.setOnClickListener {
            val list = getRandomNUmber()
            Log.d("MainActivity", list.toString())

        }
    }

    override fun onStart(){
        super.onStart()
        //실행이 되었다가 (백스택에 머물어 있다가) 다시 소환될 때 onCreate으로 가는게 아니라 여기로 온다.
        initRunButton()
    }

    //채번 알고리즘??
    private fun getRandomNUmber():List<Int>{
        //List<Integer> numberList2 = new ArrayList<>();
        val numberList = mutableListOf<Int>().apply {
            for(i in 1..45){
                this.add(i)
            }
        }///////// end of apply
        numberList.shuffle()
        val newList = numberList.subList(0, 6)
        return newList.sorted()
    }////////// end of getNumber
}