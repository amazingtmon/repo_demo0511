package com.example.beer2021;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //버튼을 클릭하면 호출된다. 파라미터에는 반드시 View가 있어야 한다.
    public void findBeer(View view){
        //텍스트뷰의 레퍼런스를 얻어온다.
        TextView brands = findViewById(R.id.brands);
        //스피너의 레퍼런스를 얻어온다.
        Spinner color = findViewById(R.id.color);
        //스피너에서 선택한 항목을 얻는다.
        String beerType = String.valueOf(color.getSelectedItem());
        //선택한 항목 표시하기
        List<String> brandsList = expert.getBrands(beerType);
        StringBuilder sb = new StringBuilder();
        for(String brand:brandsList){
            sb.append(brand).append("\n");
        }
        brands.setText(sb.toString());
    }
}