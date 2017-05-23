package com.example.peter.newsadmin.fragment;


import android.content.Intent;
import android.net.Uri;
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
import com.example.peter.newsadmin.activity.UserMessageActivity;
import com.example.peter.newsadmin.base.BaseFragment;
import com.example.peter.newsadmin.common.StatusCode;
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
    @BindView(R.id.user_sign)
    TextView user_sign;
    @BindView(R.id.user_brith)
    TextView user_brith;
    @BindView(R.id.user_sex)
    TextView user_sex;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user, container, false);
        initCommonLogic(view);
        initView();
        return view;
    }

    @OnClick({R.id.btn_user_login,R.id.btn_user_register,R.id.setting,R.id.user_brith_layout,R.id.user_sex_layout,R.id.invita_other
    ,R.id.like_me,R.id.check_in,R.id.user_pic})
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
            case R.id.user_brith_layout:
            case R.id.user_sex_layout:
                if(StringUtil.isNotEmpty(User.getInstance().getId())){
                    Intent intent=new Intent(getActivity(), UserMessageActivity.class);
                    startActivity(intent);
                    break;
                }
            case R.id.user_pic :{
                Intent intent=new Intent(getActivity(), UserMessageActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.like_me:{
                showInfo(StatusCode.SHOW_INFO_DIALOG,"点赞成功");
                break;
            }
            case R.id.invita_other:{
                Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:"));
                sendIntent.putExtra("sms_body", "邀请我的好友");
                this.startActivity(sendIntent);
                break;
            }
            case R.id.check_in :{
                showInfo(StatusCode.SHOW_INFO_DIALOG,"签到成功");
                break;
            }
            case R.id.regular_reminders :{
                showInfo(StatusCode.SHOW_INFO_DIALOG,"请先登录");
                break;
            }
            case R.id.setting:{
                if(StringUtil.isEmpty(User.getInstance().getId())){
                    showInfo("您尚未登录");
                    return;
                }else {
                    User.getInstance().cleanData();
                    initView();
                }
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
            user_sign.setText(User.getInstance().getSign());
            if(User.getInstance().getSex().equals("2")){
                user_sex.setText("姑娘");
            }else user_sex.setText("少侠");
            user_brith.setText(User.getInstance().getBirthday());

        }
        else {
            no_login_frame.setVisibility(View.VISIBLE);
            login_frame.setVisibility(View.GONE);
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
