package com.example.yesterday.yesterday.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yesterday.yesterday.R;
import com.example.yesterday.yesterday.UI.ViewPager.Chart1Fragment;
import com.example.yesterday.yesterday.UI.ViewPager.Chart2Fragment;
import com.example.yesterday.yesterday.UI.ViewPager.Chart3Fragment;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

//home 화면 Fragment
public class HomeFragment extends Fragment {

    private Intent intent;

    private ViewGroup rootView;

    private ViewPager viewPager;
    private Button btn_go;
    private TextView view;

    private Fragment[] arrFragment;

    private Handler handler;
    private autoChangeViewPager runnable;
    private Thread thread;

    private int posit=0;        //페이지 번호

    public HomeFragment() {
        // Required empty public constructor
        handler = new Handler();
        runnable = new autoChangeViewPager();

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true) {
                        Thread.sleep(5000);
                        handler.post(runnable);
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        //pause , continue 언제?
    }

    //생성자와 onCreateView만 있어도 ok
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //인플레이트(inflate) 한다는 것은 동작 가능한 view의 객체로 생성한다는 의미
        //rootView가 플래그먼트 화면으로 보이게 된다.
        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home,container,false);

        arrFragment = new Fragment[3];
        arrFragment[0] = new Chart1Fragment();
        arrFragment[1] = new Chart2Fragment();
        arrFragment[2] = new Chart3Fragment();

        viewPager=(ViewPager)rootView.findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return arrFragment[position];
            }
            @Override
            public int getCount() {
                return arrFragment.length;
            }
        });

        btn_go = (Button)rootView.findViewById(R.id.button);
        btn_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText((HomeActivity)getActivity(), "먹은 메뉴 버튼 클릭", Toast.LENGTH_LONG).show();
                intent = new Intent((HomeActivity)getActivity(), TodayMenu.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
    public class autoChangeViewPager implements Runnable{
            public void run() {
                if (posit == 3) {
                    posit = 0;
                }
                viewPager.setCurrentItem(posit,true);
                posit++;
                Log.d(TAG, "****Thread 실행 중****");
            }
    }
    /*
    @Override
    public void onStop() {
        super.onStop();
        try {
            // 일시 정지.. 왜 안돼
        }catch(Exception e){
            e.printStackTrace();
        }
        Log.d(TAG, "onStop: Thread 실행 중지");
    }
    */
}
