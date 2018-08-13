package com.example.yesterday.yesterday.UI.GoalTapFrags;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yesterday.yesterday.R;
import com.example.yesterday.yesterday.RecyclerView.ItemTouchHelperCallback;
import com.example.yesterday.yesterday.RecyclerView.RecyclerItem;
import com.example.yesterday.yesterday.RecyclerView.RecyclerViewAdapter;


import java.util.ArrayList;


public class TabTotalFragment extends Fragment {

    private ViewGroup rootView;

    //RecyclerView
    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private RecyclerViewAdapter adapter;

    private ArrayList<RecyclerItem> items;
    private String[] names={"Charile","Andrew","Liz","Thomas","Sky","Andy","Lee","Park","Kim","Jeong"};

    //결과 -> key="NAME"
    private String name;

    public TabTotalFragment() {
        // Required empty public constructor
        //ArrayList 생성해서 RectclerItem으로 데이터 넣어둠
        items = new ArrayList<RecyclerItem>();
        for(int i=0;i<names.length;i++) {
            items.add(new RecyclerItem(names[i]));
        }

    }
    // GoalAddActivity에서 목표 설정을 완료한 후 finish() 했을 때
    // tabTotalFragment onResume 실행됌 onCreateView 실행 안됌
    // 해당 액티비티에서 입력받은 문자를 받기 위함
    @Override
    public void onResume(){
        super.onResume();
        Log.d("TAG","onResume : TapTotalFragment");

        //GoalFragment로부터 name 데이터 받음!!
        Bundle bundle = getArguments();
        if (bundle != null) {
            name = bundle.getString("NAME");
            if(name != null) {
                Log.d("FINAL VALUE", name);
                adapter.onItemAdd(name);
                bundle.clear();
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("TAG","onCreate : TapTotalFragment");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("TAG","onCreateView : TapTotalFragment");

        rootView=(ViewGroup)inflater.inflate(R.layout.fragment_tab_total,container,false);

        //RecyclerView 초기화
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerview);
        //layoutManager 생성
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        //RecylerView에 layout 적용
        recyclerView.setLayoutManager(layoutManager);
        //Decoration 추가 -> 구분선 Vertical: 수직으로 구분한다!
        DividerItemDecoration decoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        decoration.setDrawable(getResources().getDrawable(R.drawable.recycler_line));
        recyclerView.addItemDecoration(decoration);
        //animator 설정
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //Adapter 생성 , RecyclerView에 적용
        adapter = new RecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);

        //드래그 or 스와이프 이벤트를 사용 하기 위한 ItemTouchHelper
        ItemTouchHelperCallback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        // Inflate the layout for this fragment
        return rootView;
    }
}