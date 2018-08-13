package com.example.yesterday.yesterday.UI.HomeFrags;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yesterday.yesterday.R;


//추가 화면 Fragment
public class AddFragment extends Fragment {

    private ViewGroup rootView;

    public AddFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    //생성자와 onCreateView만 있어도 ok
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView=(ViewGroup)inflater.inflate(R.layout.fragment_add,container,false);

        // Inflate the layout for this fragment
        return rootView;
    }
}
