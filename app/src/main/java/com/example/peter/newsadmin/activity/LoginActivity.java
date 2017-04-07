package com.example.peter.newsadmin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseActivity;

import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initLogic();
    }

    @OnClick(R.id.btn_back_user_login)
    public void onclick(View view){
        switch (view.getId()){
            case R.id.btn_back_user_login:{
                finish();
                break;
            }
        }
    }
}
