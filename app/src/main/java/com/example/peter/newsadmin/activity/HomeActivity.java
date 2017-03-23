package com.example.peter.newsadmin.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseActivity;
import com.example.peter.newsadmin.base.BaseFragment;
import com.example.peter.newsadmin.base.BaseFragmentActivity;
import com.example.peter.newsadmin.fragment.AddFragment;
import com.example.peter.newsadmin.fragment.EditFragment;
import com.example.peter.newsadmin.fragment.NewsFragment;
import com.example.peter.newsadmin.fragment.UserFragment;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends BaseFragmentActivity  {
    @BindView(R.id.home_personal_btn)LinearLayout newsBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initViews();
        initLogic();
        initListeners();

    }
    private LinearLayout layout_home_bottom;
    private HashMap<String, Fragment> fragments;



    protected void initViews() {
        super.initViews();
        layout_home_bottom = (LinearLayout) findViewById(R.id.home_bottom);
    }

    protected void initLogic() {
        super.initLogic();
        fragments=new HashMap<String, Fragment>();
        newsBtn.performClick();
    }

    @OnClick({R.id.home_appointment_btn, R.id.home_join_btn,R.id.home_personal_btn,R.id.home_setting_btn})
    public void clickEvents(View v) {
        BaseFragment fragment=null;
        switch (v.getId()) {
            case R.id.home_personal_btn:
                setClickChange(0);
                fragment=getFragment(NewsFragment.class);
                break;
            case R.id.home_join_btn:
                setClickChange(1);
                fragment=getFragment(AddFragment.class);
                break;
            case R.id.home_appointment_btn:
                setClickChange(2);
                fragment=getFragment(EditFragment.class);
                break;
            case R.id.home_setting_btn:
                setClickChange(3);
                fragment=getFragment(UserFragment.class);
                break;
            default:
                break;
        }
        if (fragment!=null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            hideFragments(manager, transaction);
            restSelect();
//          v.setBackgroundResource(R.color.home_bottom_selected);
            if (manager.findFragmentByTag(fragment.getStaticTag()) == null) {
                transaction.add(R.id.fragment_container, fragment, fragment.getStaticTag());
            }else
            {
                fragment.renderFragment();
            }
            transaction.show(fragment);
            transaction.commitAllowingStateLoss();
        }
    }
    private <T> BaseFragment getFragment( Class<T> clazz){
        String key=clazz.getName();
        Fragment frm = fragments.get(key);
        if (frm==null) {
            try {
                frm = (BaseFragment)clazz.newInstance();
                fragments.put(key, frm);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("key:"+key);
        return (BaseFragment)frm;
    }

    private void restSelect() {
        for (int i = 0; i < layout_home_bottom.getChildCount(); i++) {
            layout_home_bottom.getChildAt(i).setBackgroundDrawable(null);
        }
    }

    public void onBackPressed() {
        //在home页面点击返回跳转到主页
        Intent backHome = new Intent(Intent.ACTION_MAIN);
        backHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        backHome.addCategory(Intent.CATEGORY_HOME);
        startActivity(backHome);

    }

    public void setClickChange(int id){
        for (int i = 0; i < layout_home_bottom.getChildCount(); i++) {
            if(i!=id){
                layout_home_bottom.getChildAt(i).setSelected(false);}
            else {
                layout_home_bottom.getChildAt(id).setSelected(true);
            }
        }
    }




}
