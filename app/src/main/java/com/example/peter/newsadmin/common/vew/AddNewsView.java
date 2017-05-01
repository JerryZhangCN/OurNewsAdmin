package com.example.peter.newsadmin.common.vew;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.example.peter.newsadmin.R;

import java.util.ArrayList;
import java.util.List;

/**
 * jerry 2017-04-25
 */

public class AddNewsView extends RelativeLayout {

    private final String DIFFERENCE_STRING = "AozakiShiki";

    //scroll下的第一个层级
    private LinearLayout linearLayout;
    //文字和图片框放在scroll中
    private ScrollView scrollView;




    public AddNewsView(Context context) {
        super(context);
        init(context);
    }

    public AddNewsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AddNewsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        initView(context);

    }



    /**
     * 初始化view
     *
     * @param context
     */
    private void initView(Context context) {
        scrollView = new ScrollView(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        scrollView.setLayoutParams(layoutParams);
        this.addView(scrollView);
        initLinearLayout(context);

    }
    private void initLinearLayout(Context context){
        linearLayout=new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMarginStart(24);
        layoutParams.setMarginEnd(24);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setId(R.id.linearlayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);
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
