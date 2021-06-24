package com.example.workout80;

import android.os.Bundle;

import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class WorkoutListFragment extends ListFragment {
    interface Listener{
        void itemClicked(long id);
    }

    private Listener listener = null;

    //운동제목 클릭에 응답하기
    public void onListItemClick(ListView listView, View itemview, int position, long id){
        if(listener != null){
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String names[] = new String[Workout.workouts.length];
        for(int i=0; i< names.length; i++){
            names[i] = Workout.workouts[i].getName();
        }
        //배열 어댑터를 생성하여 리스트뷰에 연결
        //Fragment는 context의 하위 클래스가 아니다.
        //this로 현재 컨텍스트를 배열 어댑터에 전달 할 수 없다.
        //LayoutInflater의 getContext()로 컨텍스트를 얻을 수 있다.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(inflater.getContext()
                        , android.R.layout.simple_list_item_1
                        , names);
        //배열 어댑터를 리스트뷰에 연결
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}