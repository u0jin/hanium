package com.example.yesterday.yesterday.UI.HomeFrags;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yesterday.yesterday.R;
import com.example.yesterday.yesterday.UI.GoalAddActivity;
import com.example.yesterday.yesterday.UI.HomeActivity;
import com.example.yesterday.yesterday.UI.HomeViewPager.Chart1Fragment;
import com.example.yesterday.yesterday.UI.HomeViewPager.Chart2Fragment;
import com.example.yesterday.yesterday.UI.HomeViewPager.Chart3Fragment;
import com.example.yesterday.yesterday.UI.TodayMenuActivity;
import com.matthewtamlin.sliding_intro_screen_library.background.BackgroundManager;
import com.matthewtamlin.sliding_intro_screen_library.background.ColorBlender;

import static android.content.ContentValues.TAG;

//home 화면 Fragment
public class HomeFragment extends Fragment {

    private static final int[] BACKGROUND_COLORS = {0xff304FFE, 0xffcc0066, 0xff9900ff};

    private BackgroundManager backgroundManager = null;

    private ViewGroup rootView;

    private ViewPager viewPager = null;
    private ImageView[] imageViews;

    private Button menuButton;

    private Fragment[] arrFragment;

    private Handler handler;
    private setAutoChangeViewPager setAutoChangeViewPager;
    private Thread thread;

    //현재 HomeFragment가 화면에 보이면 isrun :true 안보이면 false
    private boolean isRun;
    //터치 or 드래그 중이면 true 아니면 false
    private boolean isTouched;

    public HomeFragment() {
        // Required empty public constructor

        //chartFragment
        arrFragment = new Fragment[3];
        arrFragment[0] = new Chart1Fragment();
        arrFragment[1] = new Chart2Fragment();
        arrFragment[2] = new Chart3Fragment();

        //indicator
        imageViews = new ImageView[arrFragment.length];

        //핸들러 생성 -> UI 변경하려면 무조건 핸들러
        handler = new Handler();
        //핸들러의 runnable (스레드) 작동 (setAutoChangeViewPager)
        setAutoChangeViewPager = new setAutoChangeViewPager();

        isRun=true;
        isTouched=false;
        //배경화면 colormanager
        configureBackground();
    }
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Log.d("TAG","onCreate : Home Fragment");
    }
    //생성자와 onCreateView만 있어도 ok
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //인플레이트(inflate) 한다는 것은 동작 가능한 view의 객체로 생성한다는 의미
        //rootView가 플래그먼트 화면으로 보이게 된다.

        Log.d("TAG", "onCreateView: Home Fragment");

        rootView = (ViewGroup)inflater.inflate(R.layout.fragment_home,container,false);

        //ViewPager
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
        //페이지가 바뀔 때 이벤트 처리
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (backgroundManager != null) {
                    backgroundManager.updateBackground(viewPager, position, positionOffset);
                }
            }
            @Override
            public void onPageSelected(int position) {

                for(int i=0;i<arrFragment.length;i++){
                    if(i==position) {
                        imageViews[i].setBackgroundResource(R.drawable.ic_radio_button_checked_black_24dp);
                    }
                    else{
                        imageViews[i].setBackgroundResource(R.drawable.ic_radio_button_unchecked_black_24dp);
                    }
                }
            }
            //드래그 중이면 자동 페이지 전환 안되도록 떼면 다시 작동
            @Override
            public void onPageScrollStateChanged(int state) {
                //state==0 종료
                if(state==0){
                    isTouched = false;
                }
                //state==1 드래그 중
                else if(state==1){
                    isTouched = true;
                }
            }
        });
        //* ViewPager Indicator *
        LinearLayout linearLayout=rootView.findViewById(R.id.linear_root);
        //layout_gravity -> center * layout_gravity의 경우 View 자체의 위치를 지정한 위치로 정렬 시키는 것 *
        //생성한 가져온 Gravity -> center * gravity는 View 안의 내용물을 지정한 위치로 정렬 시키는 것 *
        linearLayout.setGravity(Gravity.CENTER);
        //각각의 이미지의 layout 설정할 linearParams 생성
        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //이미지들 사이의 간격 좌,우 10씩
        linearParams.setMargins(10,0,10,0);

        //viewPager indicator 초기화 설정
        for(int i=0;i<arrFragment.length;i++) {
            //해당되는 Activity에서 ImageView 생성
            imageViews[i]=new ImageView(getActivity());
            //생성한 LinearLayout의 Gravity 설정 -> center
            //imageView의 레이아웃 설정 , 배경화면 설정
            imageViews[i].setLayoutParams(linearParams);
            if(i==0){
                //초기 indicator 지정 처음 보이는 ViewPager는 checked 되어 있도록
                imageViews[i].setBackgroundResource(R.drawable.ic_radio_button_checked_black_24dp);
            }
            else {
                imageViews[i].setBackgroundResource(R.drawable.ic_radio_button_unchecked_black_24dp);
            }
            linearLayout.addView(imageViews[i]);
        }


        //ViewPager의 mScroller를 이용, 권한 허용하여 원하는 myPager에 duration 설정
        /*
        try {
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            mScroller.set(viewPager, new setScrollerDuration(viewPager.getContext(),sInterpolator,1000));
        }catch(Exception e){ e.printStackTrace(); }
        */

        menuButton = (Button)rootView.findViewById(R.id.menubutton);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TodayMenuActivity.class);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return rootView;
    }
    //자동 스크롤 해주는 스레드 - 현재 위치 기준
    // UI 변경되는 스레드임!!
    public class setAutoChangeViewPager implements Runnable{

        int posit;        //페이지 번호

        public void run() {
            posit = viewPager.getCurrentItem();
            if (posit == 2) {
                posit = 0;
                viewPager.setCurrentItem(posit, true);
            } else {
                posit++;
                viewPager.setCurrentItem(posit, true);
            }
            Log.d("TAG", "**** Thread : setAutoChangeViewPager ****");
        }
    }
    @Override
    public void onResume(){
        super.onResume();
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.d("TAG", "Thread 시작");
                    while(isRun) {

                        Thread.sleep(5000);
                        //현재 상태가 드래그가 중이면 화면 전환 X
                        if(!isTouched) {
                            //UI 스레드
                            handler.post(setAutoChangeViewPager);
                        }
                    }
                }catch(Exception e) {
                    e.printStackTrace();
                }finally {
                    Log.d("TAG", "Thread 종료");
                    isRun=true;
                }
            }
        });
        thread.start();
    }

    @Override
    public void onStop() {
        super.onStop();

        Log.d("TAG", "onStop: HomeFragment 실행 중지");
    }
    @Override
    public void onPause(){
        super.onPause();
        isRun=false;
        Log.d("TAG", "onPause: HomeFragment Thread 일시 중지");
    }

    //전역 변수로 backgroundManager 초기화 메소드
    private void configureBackground() {
        this.backgroundManager= new ColorBlender(BACKGROUND_COLORS);
    }
    /*
    public class setScrollerDuration extends Scroller {
        private int mDuration;
        public setScrollerDuration(Context context, Interpolator interpolator, int duration) {
            super(context, interpolator);
            mDuration = duration;
        }
        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {
            // Ignore received duration, use fixed one instead
            super.startScroll(startX, startY, dx, dy, mDuration);
        }
    }
    */
}