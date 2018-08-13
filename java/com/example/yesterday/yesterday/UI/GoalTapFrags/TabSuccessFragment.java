package com.example.yesterday.yesterday.UI.GoalTapFrags;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yesterday.yesterday.R;
public class TabSuccessFragment extends Fragment {

    private ViewGroup rootView;

    public TabSuccessFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView=(ViewGroup)inflater.inflate(R.layout.fragment_tab_success,container,false);

        // Inflate the layout for this fragment
        return rootView;
    }
}
