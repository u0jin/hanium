package com.example.yesterday.yesterday.UI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.yesterday.yesterday.R;

public class TodayMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todaymenu);
    }
    public void backBtn(View v){
        Intent myIntent=new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(myIntent);
    }

    public void updateBtn(View v){
        Intent myIntent=new Intent(getApplicationContext(),UpdateMenuActivity.class);
        startActivity(myIntent);
    }


}
