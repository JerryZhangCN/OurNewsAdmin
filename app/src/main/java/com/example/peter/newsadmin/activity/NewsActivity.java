package com.example.peter.newsadmin.activity;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;

public class NewsActivity extends BaseActivity {
    @BindView(R.id.news_content)LinearLayout content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initLogic();
    }

    @Override
    protected void initLogic() {
        super.initLogic();

    }
}
