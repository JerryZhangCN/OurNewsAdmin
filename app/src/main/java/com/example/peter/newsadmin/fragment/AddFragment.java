package com.example.peter.newsadmin.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseFragment;
import com.example.peter.newsadmin.model.User;
import com.example.peter.newsadmin.present.presentImpl.AddNewsFragmentPresenter;
import com.example.peter.newsadmin.present.presentView.AddNewsFragmentView;
import com.example.peter.newsadmin.utils.GlideImageLoader;
import com.example.peter.newsadmin.utils.StringUtil;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class AddFragment extends BaseFragment implements AddNewsFragmentView {
    @BindView(R.id.add_photo)
    TextView float_photo;
    @BindView(R.id.add_text)
    TextView float_editext;
    @BindView(R.id.linearlayout)
    LinearLayout linearLayout;
    @BindView(R.id.type_spinner)
    Spinner newsType;
    @BindString(R.string.text)
    String text;
    @BindString(R.string.pic)
    String pic;
    @BindView(R.id.add_title)
    EditText title;


    private ImagePicker imagePicker;
    private boolean isText;
    private Map<Integer, View> map;
    private Map<ImageView, String> pathMap;
    private int imageViewNimber = 0;
    private int flag = 0;
    private int type;
    private String[] curs = {"ACG", "游戏", "社会", "娱乐", "科技"};
    private AddNewsFragmentPresenter presenter = new AddNewsFragmentPresenter(this);


    private List<Integer> numberList;
    private Map<Integer, String> textMap;

    public AddFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        initCommonLogic(view);
        return view;
    }

    @Override
    protected void initCommonLogic(View view) {
        super.initCommonLogic(view);
        map = new LinkedHashMap<>();
        pathMap = new LinkedHashMap<>();
        textMap = new LinkedHashMap<>();
        numberList = new ArrayList<>();
        imgpickerSetting();
        initSpinner();
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
    public void onResume() {
        super.onResume();
        //添加layout大小发生改变监听器

    }

    @Override
    public void showError(int type, String errorMsg) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            glideResult(images, requestCode);
        }
    }

    private void imgpickerSetting() {
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(true);//允许裁剪（单选才有效）
        imagePicker.setSaveRectangle(false); //是否按矩形区域保存
        imagePicker.setSelectLimit(1);    //选中数量限制
        imagePicker.setFocusWidth(1200);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(1200);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1200);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1200);//保存文件的高度。单位像素
        imagePicker.setStyle(CropImageView.Style.RECTANGLE);
        imagePicker.setMultiMode(false);
    }

    //处理imgpicker返回数据
    private void glideResult(ArrayList<ImageItem> images, int requestcode) {
        ImageView imageView;
        imageView = new ImageView(getActivity());
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280);
        imageView.setLayoutParams(params);
        pathMap.put((ImageView) map.get(requestcode), images.get(0).path);
        imageView.setBackgroundColor(Color.parseColor("#88888888"));
        imagePicker.getImageLoader().displayImage(getActivity(), images.get(0).path, (ImageView) map.get(requestcode), 280, 280);
    }

    @OnClick({R.id.add_text, R.id.add_photo, R.id.put_news})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_photo: {
                addImageView(getActivity());
                break;
            }
            case R.id.add_text: {
                addEditText(getActivity());
                break;
            }
            case R.id.put_news: {
                if (StringUtil.isEmpty(User.getInstance().getId())) {
                    showInfo("请先登陆或注册");
                    return;
                }
                domain();
                break;
            }
        }
    }

    private void domain() {
        if (flag == 0) {
            showInfo("请输入内容");
            return;
        }
        if (StringUtil.isEmpty(title.getText().toString())) {
            showInfo("请输入标题");
            return;
        }
        for (int i = 0; i < map.size(); i++) {
            View view = map.get(i);
            if (view instanceof TextView) {
                TextView textView = (TextView) view;
                textMap.put(i, textView.getText().toString());
            } else {
                ImageView imageView = (ImageView) map.get(i);
                if (StringUtil.isEmpty(pathMap.get(imageView))) {
                    showInfo("您有尚未选择图片的图片框");
                    return;
                }
                textMap.put(i, pathMap.get(imageView));
                numberList.add(i);
            }
        }
        presenter.updataPic(textMap, numberList, getType(), title.getText().toString());

    }

    @Override
    public void cleanData() {
        map.clear();
        pathMap.clear();
        imageViewNimber = 0;
        flag = 0;
        linearLayout.removeAllViews();
        numberList.clear();
        textMap.clear();
    }

    /**
     * 点击editext新增输入框
     *
     * @throws JSONException
     */
    private void addEditText(Context context) {
        if (isText)
            return;
        EditText editText = new EditText(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        editText.setLayoutParams(layoutParams);
        editText.setHint("请输入正文");
        editText.setBackgroundResource(R.drawable.public_add_editext_shape);
        Log.e("新增editText", "");

        linearLayout.addView(editText);
        editText.requestFocus();
        map.put(flag, editText);
        flag++;
        isText = true;
    }

    private void addImageView(final Context context) {
        if (pathMap.size() < imageViewNimber) {
            showInfo("您有图片框未选择图片");
            return;
        }
        ImageView imageView = new ImageView(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 600);
        layoutParams.setMargins(0, 24, 0, 24);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.ic_addpic_gray_24dp);
        imageView.setId(R.id.float_photo + flag);
        map.put(flag, imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), ImageGridActivity.class), v.getId() - R.id.float_photo);
                Log.e("点击的控件id", v.getId() + "传递的qequest为：" + (v.getId() - R.id.float_photo) + "");
            }
        });
        imageView.setClickable(true);
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        linearLayout.addView(imageView);
        imageView.requestFocus();
        flag++;
        isText = false;
        imageViewNimber++;
    }


    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, curs);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        newsType.setAdapter(adapter);
        newsType.setSelection(0);
        newsType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    curyid = position;
                //showPrice(position);
                TextView tv = (TextView) view;
                tv.setTextColor(getResources().getColor(R.color.white));    //设置颜色
                tv.setTextSize(12.0f);    //设置大小
                tv.setGravity(android.view.Gravity.CENTER_HORIZONTAL);//设置居中
//                showInfo(StatusCode.SHOW_INFO_TOAST, position+"");
                setType(position + 1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
