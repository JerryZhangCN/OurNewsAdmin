package com.example.peter.newsadmin.common.vew;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.utils.StringUtil;
import com.example.peter.newsadmin.utils.UiUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * jerry 2017-04-25
 */

public class AddNewsView extends RelativeLayout {

    private final String DIFFERENCE_STRING = "AozakiShiki";

    private Context context;

    private String content;

    private List<String> contents;
    //悬浮按钮点击事件
    private OnActionListener onActionListener;
    //添加图片的button
    private ImageView floatPhoto;
    //添加文字的button
    private ImageView floatEditext;
    //scroll下的第一个层级
    private LinearLayout linearLayout;
    //文字和图片框放在scroll中
    private ScrollView scrollView;
    private int i = 0;
    private List<Integer> id;

    public interface OnActionListener {
        void onPhotoLoadEnd(View view, String photoName);
    }

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
        this.context = context;
        initView(context);
        contents = new ArrayList<>();
    }

    public void setOnActionListener(OnActionListener onActionListener) {
        this.onActionListener = onActionListener;
    }

    /**
     * 初始化view
     *
     * @param context
     */
    private void initView(Context context) {
        id = new ArrayList<>();
        scrollView = new ScrollView(context);
        linearLayout = new LinearLayout(context);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMarginStart(24);
        layoutParams.setMarginEnd(24);
        scrollView.setLayoutParams(layoutParams);
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setId(R.id.linearlayout);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(linearLayout);
        this.addView(scrollView);
        initFloatP(context);
        initFloatE(context);
    }



    /**
     * 初始化添加图片button
     */
    private void initFloatP(Context context) {
        floatPhoto = new ImageView(context);
        LayoutParams layoutParamsFloat = new LayoutParams(240, 240);
        layoutParamsFloat.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParamsFloat.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        floatPhoto.setLayoutParams(layoutParamsFloat);
        floatPhoto.setImageResource(R.drawable.tab_floatphoto);
        floatPhoto.setClickable(true);
        floatPhoto.setId(R.id.float_photo);
        this.addView(floatPhoto);
    }

    /**
     * 初始化添加文字button
     */
    private void initFloatE(Context context) {
        floatEditext = new ImageView(context);
        LayoutParams layoutParamsFloat = new LayoutParams(240, 240);
        layoutParamsFloat.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParamsFloat.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        floatEditext.setLayoutParams(layoutParamsFloat);
        floatEditext.setImageResource(R.drawable.tab_floatphoto);
        floatEditext.setClickable(true);
        floatEditext.setId(R.id.float_editext);
        this.addView(floatEditext);
    }

    /**
     * 点击editext新增输入框
     *
     * @throws JSONException
     */
    private void addEditText(Context context) {
        if (id.size() > 0 && id.get(id.size() - 1) == R.id.float_editext + i - 1)
            return;
        EditText editText = new EditText(context);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        editText.setLayoutParams(layoutParams);
        editText.setHint("请输入正文");
        Log.e("新增editText", "");
        editText.setId(R.id.float_editext + i);
        id.add(R.id.float_editext + i);
        linearLayout.addView(editText);
        i++;
    }
    private void addImageView(final Context context){
        ImageView imageView=new ImageView(context);
        LayoutParams layoutParams = new LayoutParams(-1,600);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.ic_addpic_gray_24dp);
        linearLayout.addView(imageView);
        imageView.setClickable(true);
        id.add(R.id.float_photo + i);
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
