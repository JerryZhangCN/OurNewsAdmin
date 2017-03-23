package com.example.peter.newsadmin.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Byron on 2016-12-18.
 *
 * 基类提供一些共有属性操作
 */
public abstract class BaseFragmentActivity extends FragmentActivity {
	protected static final String TAG				= BaseFragmentActivity.class.getName();
	private PowerManager.WakeLock   wakeLock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		ActivityUtil.addActivity(this);
	}

	/**
	 * 初始化组件
	 */
	protected void initViews() {

	}

	/**
	 * 参数设置
	 */
	protected void initLogic() {
		ButterKnife.bind(this);
	}

    /**
     * 监听设置
     */
	protected void initListeners() {

	}



	@Override
	protected void onResume() {
		super.onResume();
//		MobclickAgent.onPageStart(this.getClass().getName());
		//umeng
	}

	@Override
	protected void onPause() {
		super.onPause();
//		MobclickAgent.onPageEnd(this.getClass().getName());
		//umeng
	}
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
	}

	/**
	 * 显示进度对话框
	 */

	protected void hideFragments(FragmentManager manager, FragmentTransaction transaction) {
		List<Fragment> fragments = manager.getFragments();
		if (fragments != null) {
			for (Fragment f : fragments) {
				if (f != null && !f.isHidden()) {
					transaction.hide(f);
				}
			}
		}
	}



}
