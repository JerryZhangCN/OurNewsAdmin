package com.example.peter.newsadmin.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.activity.LoginActivity;
import com.example.peter.newsadmin.activity.RegisterActivity;
import com.example.peter.newsadmin.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;


public class UserFragment extends BaseFragment {
    @BindView(R.id.btn_user_login)
    Button login;
    @BindView(R.id.btn_user_register)
    LinearLayout register;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user, container, false);
        initCommonLogic(view);
        return view;
    }

    @OnClick({R.id.btn_user_login,R.id.btn_user_register})
    public void onclick(View v){
        switch (v.getId()){
            case R.id.btn_user_login:{
                Intent intent=new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_user_register:{
                Intent intent=new Intent(getActivity(), RegisterActivity.class);
                startActivity(intent);
                break;
            }
        }
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
