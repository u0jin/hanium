package com.example.yesterday.yesterday.UI;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.yesterday.yesterday.R;

public class TodayMenu extends android.support.v4.app.Fragment {


    private Intent intent;

    private ViewGroup rootView;

    private ViewPager viewPager;
    private Button btn_go;
    private int posit=0;        //페이지 번호


    private android.support.v4.app.Fragment[] arrFragment;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_todaymenu);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.activity_todaymenu, container, false);
       // textview  =  (TextView)view.findViewById(R.id.xx);

/**
        rootView = (ViewGroup)inflater.inflate(R.layout.activity_todaymenu,container,false);
      /**
        arrFragment = new android.support.v4.app.Fragment[1];

        arrFragment[0] = new UpdateMenu();

/**
        viewPager=(ViewPager)rootView.findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public android.support.v4.app.Fragment getItem(int position) {
                return arrFragment[position];
            }
            @Override
            public int getCount() {
                return arrFragment.length;
            }
        }
 );
 **/


        btn_go = (Button)rootView.findViewById(R.id.backBtn);

        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText((HomeActivity)getActivity(), "먹은 메뉴 버튼 클릭", Toast.LENGTH_LONG).show();
                intent = new Intent((HomeActivity)getActivity(),HomeActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
    /**
    public void backBtn(View v){
        Intent myIntent=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(myIntent);
    }

    public void updateBtn(View v){
        Intent myIntent=new Intent(getApplicationContext(),UpdateMenu.class);
        startActivity(myIntent);
    }
**/

}
