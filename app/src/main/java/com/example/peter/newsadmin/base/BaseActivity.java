package com.example.peter.newsadmin.base;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.common.vew.CustomToast;
import com.example.peter.newsadmin.common.vew.MyProgressDialog;
import com.lzy.imagepicker.view.SystemBarTintManager;

import butterknife.ButterKnife;

/**
 * Created by peter on 22/3/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    private MyProgressDialog mProgressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTopBar();
        hideStatusBar();
//        initLogic();
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    protected void initTopBar(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.blue);//通知栏所需颜色
        }
    }

    protected void hideStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.blue(R.color.blue));
            window.setNavigationBarColor(Color.blue(R.color.blue));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public void hideNavigationBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
    }
    /**
     * 进度对话框是否显示
     * @return
     */
    public final boolean isProgressShowing() {
        if(mProgressDialog != null) {
            return mProgressDialog.isShowing();
        } else {
            return false;
        }
    }

    protected void initLogic(){
        ButterKnife.bind(this);
    }

    @Override
    public void showInfo(String msg) {
        CustomToast.show(msg, Toast.LENGTH_SHORT);

    }

    @Override
    public void showLoadingDialog() {

        if (mProgressDialog == null) {
            mProgressDialog = new MyProgressDialog(this);
//			mProgressDialog.setMsg("请稍后");
        }
        mProgressDialog.setCanceledOnTouchOutside(false);
//		mProgressDialog.setOnCancelListener(mCancel);

        if(!isFinishing())
            mProgressDialog.show();
    }

    @Override
    public void dismissLoadingDialog() {
        if (this.isProgressShowing())
            mProgressDialog.dismiss();
    }

    @Override
    public void showError(int type, String errorMsg) {

    }

    @Override
    public void showInfo(int type, String msg) {

    }

    @Override
    public void dismissError() {

    }

    @Override
    public void initTopbar() {

    }
}
