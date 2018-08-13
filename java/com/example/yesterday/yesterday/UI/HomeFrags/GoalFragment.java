package com.example.yesterday.yesterday.UI.HomeFrags;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yesterday.yesterday.R;

import com.example.yesterday.yesterday.UI.GoalAddActivity;
import com.example.yesterday.yesterday.UI.GoalTapFrags.TabGoalFragment;
import com.example.yesterday.yesterday.UI.GoalTapFrags.TabSuccessFragment;
import com.example.yesterday.yesterday.UI.GoalTapFrags.TabTotalFragment;
import com.example.yesterday.yesterday.UI.GoalTapFrags.TabUserGoalFragment;
import com.example.yesterday.yesterday.UI.HomeActivity;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

//목표 화면 Fragment
public class GoalFragment extends Fragment {

    //주황 배경 무슨 오류..!?
    private ViewGroup rootView;

    //TabLayout , ViewPager
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private PagerAdapter pagerAdapter;

    private TabTotalFragment tabTotalFragment;
    private TabGoalFragment tabGoalFragment;
    private TabUserGoalFragment tabUserGoalFragment;
    private TabSuccessFragment tabSuccessFragment;

    //FloatingActionButton
    private FloatingActionButton floatingActionButton;

    public int REQUEST_ACT=1234;

    public GoalFragment() {
        tabTotalFragment = new TabTotalFragment();
        tabGoalFragment = new TabGoalFragment();
        tabUserGoalFragment = new TabUserGoalFragment();
        tabSuccessFragment = new TabSuccessFragment();
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    //생성자와 onCreateView만 있어도 ok
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=(ViewGroup)inflater.inflate(R.layout.fragment_goal,container,false);

        tabLayout = (TabLayout)rootView.findViewById(R.id.tab_layout);
        viewPager = (ViewPager)rootView.findViewById(R.id.tab_viewpager);

        //tabLayouy 초기화
        tabLayout.addTab(tabLayout.newTab().setText("전체"));
        tabLayout.addTab(tabLayout.newTab().setText("목표"));
        tabLayout.addTab(tabLayout.newTab().setText("다짐"));
        tabLayout.addTab(tabLayout.newTab().setText("완료"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //ViewPager 초기화
        //class로 설정한 viewpager 어댑터 정의 후 적용
        pagerAdapter = new PagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.d("TAG","Press TabLayout");
                viewPager.setCurrentItem(tab.getPosition(),false);
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) { }
            @Override
            public void onTabReselected(TabLayout.Tab tab) { }
        });

        //floatingActionButton 초기화 밑 이벤트 처리
        floatingActionButton = (FloatingActionButton)rootView.findViewById(R.id.floating_action_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent((HomeActivity)getActivity(),GoalAddActivity.class);
                //fragment의 startActivityForResult !!
                startActivityForResult(intent,REQUEST_ACT);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }

    //FragmentPagerAdapter -> 정적인 뷰페이저에 어울림 미리 페이지를 load 해두기 때문
    //FragmentStatePagerAdapter -> 동적인 뷰페이저에 어울림 페이지 focus가 사라지면 destroy하기 때문
    //즉 FragmentStatePagerAdapter는 getItem 할 때 객체를 새로 생성하고 return해주어야함 생성자에 생성해서 하면 오류남
    public class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm){
            super(fm);
    }
        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return tabTotalFragment;
                case 1:
                    return  tabGoalFragment;
                case 2:
                    return tabUserGoalFragment;
                case 3:
                    return tabSuccessFragment;
                    default:
                        return null;
            }
        }
        @Override
        public int getCount() {
            return 4;
        }
    }

    //
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d("TAG","GoalFragment onActivityResult");
        // Check which request we're responding to
        if (requestCode == REQUEST_ACT) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                // The user picked a contact.
                // The Intent's data Uri identifies which contact was selected.

                    //GoalAddActivity로 부터 데이터 받음!!
                    String name = data.getStringExtra("NAME");
                    Log.d("VALUE",name);

                    //TabTotalFragment로 데이터 전달
                    Bundle bundle = new Bundle();
                    bundle.putString("NAME",name);
                    tabTotalFragment.setArguments(bundle);

                // Do something with the contact here (bigger example below)
            }
        }
    }
}
