package com.example.peter.newsadmin.base;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

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


	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		if (ev.getAction() == MotionEvent.ACTION_DOWN) {

			// 获得当前得到焦点的View，一般情况下就是EditText（特殊情况就是轨迹求或者实体案件会移动焦点）
			View v = getCurrentFocus();

			if (isShouldHideInput(v, ev)) {
				hideSoftInput(v.getWindowToken());
			}
		}
		return super.dispatchTouchEvent(ev);
	}

	/**
	 * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时没必要隐藏
	 *
	 * @param v
	 * @param event
	 * @return
	 */
	private boolean isShouldHideInput(View v, MotionEvent event) {
		if (v != null && (v instanceof EditText)) {
			int[] l = {0, 0};
			v.getLocationInWindow(l);
			int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
					+ v.getWidth();
			if (event.getX() > left && event.getX() < right
					&& event.getY() > top && event.getY() < bottom) {
				// 点击EditText的事件，忽略它。
				return false;
			} else {
				return true;
			}
		}
		// 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditView上，和用户用轨迹球选择其他的焦点
		return false;
	}

	/**
	 * 多种隐藏软件盘方法的其中一种
	 *
	 * @param token
	 */
	private void hideSoftInput(IBinder token) {
		if (token != null) {
			InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			im.hideSoftInputFromWindow(token,
					InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}



}
