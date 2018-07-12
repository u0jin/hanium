package com.example.yesterday.yesterday.UI;

//import android.app.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.yesterday.yesterday.R;

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
        RelativeLayout layout = (RelativeLayout) inflater.inflate(R.layout.fragment_searchmenu, container, false);
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


