package com.example.peter.newsadmin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseActivity;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        hideStatusBar();
//        initTopBar();
        setContentView(R.layout.activity_login);
        initLogic();
    }

    @Override
    protected void initLogic() {
        super.initLogic();
    }
}
