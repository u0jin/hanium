package com.example.yujin.hanium_addupdate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class TodayMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todaymenu);
    }
    public void backBtn(View v){
        Intent myIntent=new Intent(getApplicationContext(),UpdateMenu.class);
        startActivity(myIntent);
    }

}
