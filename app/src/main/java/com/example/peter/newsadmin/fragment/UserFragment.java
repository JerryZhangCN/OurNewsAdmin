package com.example.peter.newsadmin.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.activity.LoginActivity;
import com.example.peter.newsadmin.activity.RegisterActivity;
import com.example.peter.newsadmin.base.BaseFragment;
import com.example.peter.newsadmin.model.User;
import com.example.peter.newsadmin.utils.GlideCircleTransform;
import com.example.peter.newsadmin.utils.StringUtil;

import butterknife.BindView;
import butterknife.OnClick;


public class UserFragment extends BaseFragment {
    @BindView(R.id.btn_user_login)
    Button login;
    @BindView(R.id.btn_user_register)
    LinearLayout register;
    @BindView(R.id.no_login_frame)LinearLayout no_login_frame;
    @BindView(R.id.pic_frame)LinearLayout login_frame;
    @BindView(R.id.nick_name)TextView nick_name;
    @BindView(R.id.user_pic)ImageView user_pic;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user, container, false);
        initCommonLogic(view);
        initView();
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
    private void initView(){
        if(StringUtil.isNotEmpty(User.getInstance().getId())){
            no_login_frame.setVisibility(View.GONE);
            login_frame.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(StringUtil.getPhotoUrl(User.getInstance().getPhoto())).asBitmap().transform(new GlideCircleTransform(getActivity())).diskCacheStrategy(DiskCacheStrategy.ALL).into(user_pic);
            nick_name.setText(User.getInstance().getNick_name());
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
