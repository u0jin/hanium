package com.example.yujin.hanium_addupdate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void addBtn(View v){
        Intent myIntent=new Intent(getApplicationContext(),TodayMenu.class);
        startActivity(myIntent);

    }


}
