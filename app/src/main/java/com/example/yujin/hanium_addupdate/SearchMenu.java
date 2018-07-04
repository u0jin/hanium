package com.example.yujin.hanium_addupdate;

//import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class SearchMenu extends Fragment
{
    public SearchMenu()
    {

    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.searchmenu, container, false);
        return layout;
    }
/**
    public void saveBtn(View v){
        Intent myIntent=new Intent(getApplicationContext(),.class);
        startActivity(myIntent);
    }

 저장버튼 -DB연동해야함
*/

}


