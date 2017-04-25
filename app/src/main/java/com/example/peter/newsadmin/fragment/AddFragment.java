package com.example.peter.newsadmin.fragment;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.peter.newsadmin.MainActivity;
import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseFragment;
import com.example.peter.newsadmin.utils.GlideImageLoader;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

public class AddFragment extends BaseFragment implements View.OnLayoutChangeListener,View.OnClickListener{
    @BindView(R.id.float_photo)
    ImageView float_photo;
    @BindView(R.id.float_editext)
    ImageView float_editext;
    @BindView(R.id.linearlayout)LinearLayout linearLayout;
    @BindView(R.id.main_content)LinearLayout mainView;

    //Activity最外层的Layout视图
    private View activityRootView;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;
    private ImagePicker imagePicker;
    private List<Integer> ids;
    private List<Integer> id;
    private int defoualtId=0;
    private int i=1;
    private Map<Integer,ImageView> map;

    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        initCommonLogic(view);
        activityRootView = view.findViewById(R.id.main_content);
        //获取屏幕高度
        screenHeight = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        //阀值设置为屏幕高度的1/3
        keyHeight = screenHeight/3;
        return view;
    }

    @Override
    protected void initCommonLogic(View view) {
        super.initCommonLogic(view);
        ids=new ArrayList<>();
        id=new ArrayList<>();
        map=new LinkedHashMap<>();
        imgpickerSetting();
//        getPic(linearLayout);
        float_photo.setOnClickListener(this);
        float_editext.setOnClickListener(this);
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
        activityRootView.addOnLayoutChangeListener(this);
    }

    @Override
    public void showError(int type, String errorMsg) {

    }
////遍历控件是否是imageview
//    private void getPic(ViewGroup viewGroup) {
//        if (viewGroup == null) {
//            return;
//        }
//        int count = viewGroup.getChildCount();
//        for (int i = 0; i < count; i++) {
//            View view = viewGroup.getChildAt(i);
//            if (view instanceof ImageView) {
//               ImageView newDtv = (ImageView) view;
//                newDtv.setId(R.id.float_photo+defoualtId);
//                newDtv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                        startActivityForResult(new Intent(getActivity(), ImageGridActivity.class), v.getId());
//                    }
//                });
//            } else if (view instanceof ViewGroup) {
//                // 若是布局控件（LinearLayout或RelativeLayout）,继续查询子View
//                this.getPic((ViewGroup) view);
//            }
//        }
//    }

    //键盘弹出隐藏两个图标
    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
        //现在认为只要控件将Activity向上推的高度超过了1/3屏幕高，就认为软键盘弹起
        if(oldBottom != 0 && bottom != 0 &&(oldBottom - bottom > keyHeight)){

            float_editext.setVisibility(View.GONE);
            float_photo.setVisibility(View.GONE);
        }else if(oldBottom != 0 && bottom != 0 &&(bottom - oldBottom > keyHeight)){
            float_editext.setVisibility(View.VISIBLE);
            float_photo.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null ) {
            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            glideResult(images,requestCode);}
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
    private void glideResult(ArrayList<ImageItem> images,int requestcode) {
        ImageView imageView;
        imageView = new ImageView(getActivity());
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280);
        imageView.setLayoutParams(params);
        imageView.setBackgroundColor(Color.parseColor("#88888888"));
        imagePicker.getImageLoader().displayImage(getActivity(), images.get(0).path, map.get(requestcode), 280, 280);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.float_photo: {
                addImageView(getActivity());
                break;
            }
            case R.id.float_editext: {
                addEditText(getActivity());
                break;
            }
        }
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
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
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
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,600);
        imageView.setLayoutParams(layoutParams);
        imageView.setImageResource(R.drawable.ic_addpic_gray_24dp);
        imageView.setId(R.id.float_photo + i);
        map.put(i,imageView);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), ImageGridActivity.class), v.getId()-R.id.float_photo);
                Log.e("点击的控件id",v.getId()+"传递的qequest为："+(v.getId()-R.id.float_photo)+"");
            }
        });
        imageView.setClickable(true);
        ids.add(R.id.float_photo + i);
        linearLayout.addView(imageView);
        i++;
    }

}
