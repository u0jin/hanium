package com.example.yesterday.yesterday.UI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yesterday.yesterday.R;

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

    //MaterialDrawer
    private PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("홈").withIcon(R.drawable.ic_home_white_24dp).withIconTintingEnabled(true);
    private PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName("홈_첫번째").withIcon(R.drawable.ic_wb_sunny_black_24dp).withIconTintingEnabled(true);
    private PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName("홈_두번째").withIcon(R.drawable.ic_help_outline_black_24dp).withIconTintingEnabled(true);
    private PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName("섹션_첫번째").withIcon(R.drawable.ic_settings_black_24dp).withIconTintingEnabled(true);
    private PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName("우엉우엉이짱").withIcon(R.drawable.ic_playlist_add_black_24dp).withIconTintingEnabled(true);

    private SecondaryDrawerItem sectionHeader = new SecondaryDrawerItem().withName("section_header");

    private  Drawer result;

    //BottomBar
    private HomeFragment homeFragment;
    private AddFragment addFragment;
    private GoalFragment goalFragment;
    private StatisticsFragment statisticsFragment;
    //
    private CalendarFragment calendarFragment;
    //
    private String name;
    private ImageView imageView;
    //
    Intent intent;

    //현재 fragment가 어딘지 보기 위함
    int tabid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //MaterialDrawer 쓰기위해 toolbar의 id를 가져와 객체 생성
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);

        //BottomBar
        homeFragment = new HomeFragment();
        addFragment = new AddFragment();
        goalFragment = new GoalFragment();
        statisticsFragment = new StatisticsFragment();

        //Calendar
        calendarFragment = new CalendarFragment();

        //Intent로 로그인 이름 가져옴 -> name은 아직 안쓰임
        intent = getIntent();
        name = intent.getStringExtra("name");

        //Calendar
        imageView=(ImageView)findViewById(R.id.toolbar_calendar_button);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Calendar버튼 클릭", Toast.LENGTH_LONG).show();
                replaceFragment(calendarFragment);
            }
        });

        // MaterialDrawer
        // Create the AccountHeader -> 사용자 계정(이미지,이름,이메일)헤더 생성
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.header)        //배경이미지
                .withCompactStyle(true)                         //소형스타일
                .withProfileImagesClickable(false)              //프로필이미지선택X
                .withCloseDrawerOnProfileListClick(true)
                .withSavedInstance(savedInstanceState)
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
                .withTranslucentStatusBar(false)
                //.withFullscreen(true)
                .withDrawerLayout(R.layout.material_drawer_fits_not)
                .withSavedInstance(savedInstanceState)
                .build();

        //bottomBar를 tab했을 때 id를 구분해 해당 내부코드를 실행하여 Fragment의 전환이 이루어짐

        BottomBar bottomBar=(BottomBar)findViewById(R.id.bottomBar);
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
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.contentContainer, fragment);
        fragmentTransaction.commit();
    }
}
