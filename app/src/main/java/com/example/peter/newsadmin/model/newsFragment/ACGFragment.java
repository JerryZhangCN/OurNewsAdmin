package com.example.peter.newsadmin.model.newsFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseFragment;

/**
 * jerry.zhang 04-07
 * ACG板块
 */
public class ACGFragment extends BaseFragment {


    public ACGFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acg, container, false);
    }

    @Override
    public void renderFragment() {

    }
}
