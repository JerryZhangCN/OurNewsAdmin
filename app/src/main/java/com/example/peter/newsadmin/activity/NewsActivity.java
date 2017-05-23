package com.example.peter.newsadmin.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseActivity;
import com.example.peter.newsadmin.model.NewsModel;
import com.example.peter.newsadmin.present.presentImpl.NewsActivityPresenter;
import com.example.peter.newsadmin.present.presentView.NewsActivityView;
import com.example.peter.newsadmin.utils.StringUtil;
import com.example.peter.newsadmin.utils.UiUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsActivity extends BaseActivity implements NewsActivityView{
    @BindView(R.id.new_message_layout)
    LinearLayout linearLayout;

    private List<String> contents=new ArrayList<>();
    private NewsActivityPresenter presenter;
    private final String DIFFERENCE_STRING = "AozakiShiki";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        initLogic();
    }

    @Override
    protected void initLogic() {
        super.initLogic();
        Intent intent=getIntent();
        presenter=new NewsActivityPresenter(this);
        presenter.getNews(intent.getStringExtra("nid"));
    }
    @OnClick(R.id.news_back)
    public void gosubpage(View view){
        switch (view.getId()){
            case R.id.news_back:{
                finish();
                break;
            }
        }
    }
    @Override
    public void updateMsg(NewsModel newsModel) {
        String content=newsModel.getContent();
        String[] tempStrings = content.split("<br/>");
        for (String str : tempStrings) {
            List<String> list = getPhotoString(str);
            if (list != null) {
                contents.addAll(list);
            } else {
                contents.add(str);
            }
        }

        for (int i = 0; i < contents.size(); i++) {
            if (contents.get(i).startsWith(DIFFERENCE_STRING)) {
                final ImageView simpleDraweeView = new ImageView(this);
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(contents.get(i).substring(DIFFERENCE_STRING.length(), contents.get(i).length()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int imgWidth = 0;
                try {
                    imgWidth = jsonObject.getInt("width");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                int imgHeight = jsonObject.optInt("height");
                final String name = jsonObject.optString("name");
                if (!(imgWidth == 0 || imgHeight == 0 || TextUtils.isEmpty(name))) {
//                    int height = (UiUtil.getScreenWidth() - UiUtil.dip2px(24)) * imgHeight / imgWidth;
//                    int height=400;
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                        layoutParams.setMarginStart(UiUtil.dip2px(12));
                        layoutParams.setMarginEnd(UiUtil.dip2px(12));
                    }
                    if (i == 0) {
                        layoutParams.setMargins(UiUtil.dip2px(12), 0, UiUtil.dip2px(12), UiUtil.dip2px(16));
                    } else {
                        layoutParams.setMargins(UiUtil.dip2px(12), UiUtil.dip2px(16), UiUtil.dip2px(12), UiUtil.dip2px(16));
                    }
                    simpleDraweeView.setLayoutParams(layoutParams);

                    linearLayout.addView(simpleDraweeView);

                    Glide.with(this).load(StringUtil.getPhotoUrl(name))
                            .asBitmap().diskCacheStrategy(DiskCacheStrategy.ALL)
                            .into(simpleDraweeView);
                }
            } else {
                TextView textView = new TextView(this);
//                textView.setTextColor(ThemeUtil.getColor(context.getTheme(), R.attr.textColor2));
                textView.setTextSize(18);
                textView.setLineSpacing(0, 1.2f);
                textView.setTextIsSelectable(true);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    layoutParams.setMarginStart(UiUtil.dip2px(12));
                    layoutParams.setMarginEnd(UiUtil.dip2px(12));
                }
                if (i == 0) {
                    layoutParams.setMargins(UiUtil.dip2px(12), 0, UiUtil.dip2px(12), UiUtil.dip2px(16));
                } else {
                    layoutParams.setMargins(UiUtil.dip2px(12), UiUtil.dip2px(16), UiUtil.dip2px(12), UiUtil.dip2px(16));
                }
                textView.setLayoutParams(layoutParams);

                String text = "\t\t\t\t" + contents.get(i);

                textView.setText(text);
                linearLayout.addView(textView);
            }
        }
    }

    private List<String> getPhotoString(String str) {
        List<String> list = null;
        while (true) {
            if (str.contains("<json>") && str.contains("</json>")) {
                if (list == null)
                    list = new ArrayList<>();
                int length = str.indexOf("<json>");
                if (length != 0) {
                    list.add(str.substring(0, str.indexOf("<json>") - 1));
                }
                list.add(DIFFERENCE_STRING + str.substring(str.indexOf("<json>") + 6, str.indexOf("</json>")));
                str = str.substring(str.indexOf("</json>") + 7, str.length());
            } else {
                if (list != null) {
                    list.add(str);
                }
                break;
            }
        }
        return list;
    }
}
