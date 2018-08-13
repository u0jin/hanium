package com.example.yesterday.yesterday.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import com.example.yesterday.yesterday.R;

import com.example.yesterday.yesterday.UI.HomeFrags.AddFragment;
import com.example.yesterday.yesterday.UI.HomeFrags.GoalFragment;
import com.example.yesterday.yesterday.UI.HomeFrags.HomeFragment;
import com.example.yesterday.yesterday.UI.HomeFrags.StatisticsFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

public class HomeActivity extends AppCompatActivity {

    //Activity
    Activity mActivity;
    //toolbar
    private Toolbar toolbar;
    //FragmentManager
    private FragmentManager fragmentManager;

    //MaterialDrawer
    private PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("홈").withIcon(R.drawable.ic_home_solid_white).withIconTintingEnabled(true);
    private PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("홈_첫번째").withIcon(R.drawable.ic_wb_sunny_black_24dp).withIconTintingEnabled(true);
    private PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("홈_두번째").withIcon(R.drawable.ic_help_outline_black_24dp).withIconTintingEnabled(true);
    private PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("섹션_첫번째").withIcon(R.drawable.ic_settings_black_24dp).withIconTintingEnabled(true);
    private PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("우엉우엉이짱").withIcon(R.drawable.ic_playlist_add_black_24dp).withIconTintingEnabled(true);

    private SecondaryDrawerItem sectionHeader = new SecondaryDrawerItem().withName("section_header");

    Drawer result;
    AccountHeader headerResult;
    //private Handler handler;

    //BottomBar
    BottomBar bottomBar;
    //Fragment
    private HomeFragment homeFragment;
    private AddFragment addFragment;
    private GoalFragment goalFragment;
    private StatisticsFragment statisticsFragment;
    //
    String name;
    ImageView calendarView;
    //
    Intent intent;


    public HomeActivity(){

        //Activity
        mActivity = HomeActivity.this;

        //FragmentManager
        fragmentManager = getSupportFragmentManager();
        //handler = new Handler();

        //Fragment
        homeFragment = new HomeFragment();
        addFragment = new AddFragment();
        goalFragment = new GoalFragment();
        statisticsFragment = new StatisticsFragment();
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d("TAG","HomeActivity onStart / 시작");
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        Log.d("TAG","HomeActivity onRestart / 다시 실행");
    }
    @Override
    protected void onResume(){
        super.onResume();
        Log.d("TAG","HomeActivity onResume / 다시 시작");
    }
    @Override
    protected void onPause(){
        super.onPause();
        Log.d("TAG","HomeActivity onPause / 일시 정지");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.d("TAG","HomeActivity onStop / 정지");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d("TAG","HomeActivity onDestroy / 종료");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Log.d("TAG","onCreate / 앱 생성(초기화)");

        //MaterialDrawer 쓰기위해 toolbar의 id를 가져와 객체 생성
        toolbar=(Toolbar)findViewById(R.id.toolbar);

        //Intent로 로그인 이름 가져옴 -> name은 아직 안쓰임
        intent = getIntent();
        name = intent.getStringExtra("name");

        //Calendar
        //Calendar View로 넘어가면 밑에 바텀바 focus 어케 해결!?
        calendarView=(ImageView)findViewById(R.id.toolbar_calendar_button);
        calendarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
               startActivity(intent);
            }
        });

        // MaterialDrawer
        // Create the AccountHeader -> 사용자 계정(이미지,이름,이메일)헤더 생성
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)        //배경이미지
                .withCompactStyle(true)                         //소형스타일
                .withProfileImagesClickable(false)              //프로필이미지선택X
                //.withSavedInstance(savedInstanceState)
                .addProfiles(
                        new ProfileDrawerItem().withName("woowonLee").withEmail("wwlee94@gmail.com").withIcon(getResources().getDrawable(R.drawable.profile))
                )
                .build();
        //create the drawer and remember the `Drawer` result object
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)                           //toolbar에 drawer추가
                .withAccountHeader(headerResult)                //drawer에 계정 정보 추가
                .addDrawerItems(                                //drawer에 들어갈 item추가
                        item1, item2, item3,
                        new DividerDrawerItem(),
                        sectionHeader,
                        item4,item5
                )
                //drawer를 클릭 했을 때 이벤트 처리
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        return true;
                    }
                })
                //.withFullscreen(true)
                //반투명화 시켜줌.. style에 statusBar랑 같이 쓰면
                .withTranslucentStatusBar(false)
                //색 안 넣으면 투명화 layout
                .withDrawerLayout(R.layout.material_drawer_fits_not)
                //.withSavedInstance(savedInstanceState)
                .build();

        //bottomBar를 tab했을 때 id를 구분해 해당 내부코드를 실행하여 Fragment의 전환이 이루어짐
        bottomBar=(BottomBar)findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(int tabId) {

                //transaction 객체를 가져옴
                //if 가져온 tabId가 tab_home일때 homeFragment화면으로 전환
                if(tabId==R.id.tab_home){
                    replaceFragment(homeFragment);
                }
                //if 가져온 tabId가 tab_add일때 해당 화면으로 전환
                else if(tabId==R.id.tab_add){
                    replaceFragment(addFragment);
                }
                //if 가져온 tabId가 tab_goal일때 해당 화면으로 전환
                else if(tabId==R.id.tab_goal){
                    replaceFragment(goalFragment);
                }
                //if 가져온 tabId가 tab_statistics일때 해당 화면으로 전환
                else if(tabId==R.id.tab_statistics){
                    replaceFragment(statisticsFragment);
                }
            }
        });
    }
    public void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.contentContainer, fragment);
        transaction.commit();
    }
}
