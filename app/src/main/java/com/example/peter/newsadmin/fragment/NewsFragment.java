package com.example.peter.newsadmin.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseFragment;
import com.example.peter.newsadmin.common.adpter.FragmentAdeptar;

import butterknife.BindView;


public class NewsFragment extends BaseFragment {
    @BindView(R.id.news_viewpager)ViewPager viewPager;
    @BindView(R.id.news_tablayout)TabLayout tabLayout;
    private FragmentAdeptar pagerAdapter;

    public NewsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_news, container, false);
        initCommonLogic(view);
        return view;
    }


    @Override
    protected void initCommonLogic(View view) {
        super.initCommonLogic(view);
        pagerAdapter = new FragmentAdeptar(getActivity().getSupportFragmentManager(), getActivity());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public void renderFragment() {

    }

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showError(int type, String errorMsg) {

    }
}
