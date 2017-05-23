package com.example.peter.newsadmin.activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseActivity;
import com.example.peter.newsadmin.common.StatusCode;
import com.example.peter.newsadmin.model.User;
import com.example.peter.newsadmin.present.presentImpl.UserMessagePresenter;
import com.example.peter.newsadmin.present.presentView.UserMessageView;
import com.example.peter.newsadmin.utils.DateUtil;
import com.example.peter.newsadmin.utils.GlideCircleTransform;
import com.example.peter.newsadmin.utils.GlideImageLoader;
import com.example.peter.newsadmin.utils.StringUtil;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.view.CropImageView;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;

public class UserMessageActivity extends BaseActivity implements UserMessageView {
    @BindView(R.id.chose_user_pic)
    ImageView user_pic;
    @BindView(R.id.edit_sex)
    EditText user_sex;
    @BindView(R.id.edit_sign)
    EditText user_sign;
    @BindView(R.id.user_nick_name)
    EditText user_nickName;
    @BindView(R.id.text_brith)
    TextView brithday;

    private ImagePicker imagePicker;
    private int DEX = 231;
    private UserMessagePresenter presenter = new UserMessagePresenter(this);
    private String brith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_message);
        initLogic();
        imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());
        initView();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            ArrayList<ImageItem> images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
            glideResult(images, requestCode);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @OnClick({R.id.change_user_pic,R.id.brith_layout, R.id.update_sure,R.id.btn_back_user_login})
    public void gosub(View view) {
        switch (view.getId()) {
            case R.id.change_user_pic: {
                startActivityForResult(new Intent(this, ImageGridActivity.class), DEX);
                break;
            }
            case R.id.brith_layout:{
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, new datePickerCallBackStartEnd(),Integer.parseInt( DateUtil.date2Str(new Date(),DateUtil.FORMAT_ONLY_YAER)), Integer.parseInt( DateUtil.date2Str(new Date(),DateUtil.FORMAT_ONLY_MONTH))-1, Integer.parseInt( DateUtil.date2Str(new Date(),DateUtil.FORMAT_ONLY_DAY)));
                datePickerDialog.show();
                break;
            }
            case R.id.update_sure: {
                presenter.update(user_sex.getText().toString().trim().equals("男")?"0":"1",user_nickName.getText().toString().trim(),user_sign.getText().toString().trim(),brithday.getText().toString().trim());
                break;
            }
            case R.id.btn_back_user_login :{
                finish();
                break;
            }
        }
    }

    private void initView() {
        Glide.with(this).load(StringUtil.getPhotoUrl(User.getInstance().getPhoto())).asBitmap().transform(new GlideCircleTransform(this)).diskCacheStrategy(DiskCacheStrategy.ALL).into(user_pic);
        user_sign.setText(User.getInstance().getSign());
        if(User.getInstance().getSex().equals("0"))
            user_sex.setText("男");
        else
            user_sex.setText("女");
        user_nickName.setText(User.getInstance().getNick_name());
        brithday.setText(User.getInstance().getBirthday());

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
        imageView = new ImageView(this);
        AbsListView.LayoutParams params = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 280);
        imageView.setLayoutParams(params);
        imageView.setBackgroundColor(Color.parseColor("#88888888"));
        presenter.userPic(images.get(0).path);
       // Glide.with(this).load(StringUtil.getPhotoUrl(User.getInstance().getPhoto())).asBitmap().transform(new GlideCircleTransform(this)).diskCacheStrategy(DiskCacheStrategy.ALL).into(user_pic);
        imagePicker.getImageLoader().displayImage(UserMessageActivity.this, images.get(0).path, user_pic, 280, 280);
    }

    @Override
    public void result() {
        showInfo(StatusCode.SHOW_INFO_TOAST,"修改成功");
          finish();
    }
    private class datePickerCallBackStartEnd implements DatePickerDialog.OnDateSetListener {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            brith = year+month+dayOfMonth+"";
            brithday.setText(brith);
            return;


        }
    }
}
