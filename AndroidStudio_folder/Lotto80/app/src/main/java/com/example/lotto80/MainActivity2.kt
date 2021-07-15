package com.example.lotto80

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import org.w3c.dom.Text

class MainActivity2 : AppCompatActivity() {
    //선언뒤에 lazy가 오면 activity_main4.xml을 스캔할 때 까지 기다린다.
    private val btn_run: Button by lazy {
        findViewById(R.id.btn_run)
    }

    private val btn_add: Button by lazy {
        findViewById(R.id.btn_add)
    }

    private val btn_reset: Button by lazy {
        findViewById(R.id.btn_reset)
    }
    //NumberPicker의 범위를 정해준다.
    private val numberPicker: NumberPicker by lazy{
        findViewById<NumberPicker>(R.id.numberPicker)

    }

    //채번된 숫자가 출력될 컴포넌트 배열에 담기
    private val numberTextViewList: List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.tv_choice1),
            findViewById(R.id.tv_choice2),
            findViewById(R.id.tv_choice3),
            findViewById(R.id.tv_choice4),
            findViewById(R.id.tv_choice5),
            findViewById(R.id.tv_choice6),
        )
    }

    //이미 자동생성 시작이라는 것을 눌러서 번호를 추가할 수 없는 상태일 수 있으니까 예외처리
    private var didRun = false

    //1부터 45까지 중에 중복이 되지 않도록 막기
    private val pickNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        numberPicker.minValue = 1
        numberPicker.maxValue = 45
        //자동채번 버튼 구현
        initRunButton()
        initAddButton()
        initReset()

    }////////////// end of Create

    private fun initAddButton(){
        btn_add.setOnClickListener{
            if(didRun){
                Toast.makeText(this, "초기화 후에 시도해주세요.", Toast.LENGTH_SHORT).show()
                //@setOnClickListener를 붙이는 이유는 안에 있는 setOnCllickListener
                //밖에 있는 initAddButton을 리턴할지 모르니까 알아보기 쉽게 파랑으로
                return@setOnClickListener
            }
            if(pickNumberSet.size>=6){
                Toast.makeText(this, "번호는 6개까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(pickNumberSet.contains(numberPicker.value)){
                Toast.makeText(this, "이미 선택한 번호입니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible=true
        }
    }

    //자동 생성시작 버튼을 눌렀을 때
    //기존에 추첨한 숫자가 있을 경우 그 나머지만 숫자를 계속 바꿔서 출력해야 한다.
    private fun initRunButton() {
        btn_run.setOnClickListener {
            var rbtn = it.id

            val list = getRandomNUmber()
            didRun = true
            list.forEachIndexed { index, number ->
                val textView = numberTextViewList[index]
                textView.text = number.toString()
                textView.
            }
            Log.d("MainActivity", list.toString())

        }
    }

    private fun initReset(){
        btn_reset.setOnClickListener{
            pickNumberSet.clear()
            numberTextViewList.forEach {
                it.isVisible = false
            }
            didRun = false
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
                //이미 추첨한 숫자가 있으면 그것만큼 제외시킨다.
                if(pickNumberSet.contains(i)) continue
                this.add(i)
            }
        }///////// end of apply
        numberList.shuffle()
        val newList = numberList.subList(0, 6)
        return newList.sorted()
    }////////// end of getNumber
}