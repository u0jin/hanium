package com.example.yujin.hanium_addupdate;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class UpdateMenu extends AppCompatActivity {


    ViewPager vp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updatemenu);

        vp = (ViewPager) findViewById(R.id.ViewPager);
        Button freqeuntlyBtn = (Button) findViewById(R.id.frequentlyBtn);
        Button searchBtn = (Button) findViewById(R.id.searchBtn);

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        searchBtn.setOnClickListener(movePageListener);
        searchBtn.setTag(0);

        freqeuntlyBtn.setOnClickListener(movePageListener);
        freqeuntlyBtn.setTag(1);
    }
    View.OnClickListener movePageListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            int tag = (int) v.getTag();
            vp.setCurrentItem(tag);
        }
    };

    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(android.support.v4.app.FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public android.support.v4.app.Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new SearchMenu();
                case 1:
                    return new FrequentlyEatenMenu();
                default:
                    return null;
            }
        }

        @Override
        public int getCount()

        {
            return 2;
        }
    }




}
