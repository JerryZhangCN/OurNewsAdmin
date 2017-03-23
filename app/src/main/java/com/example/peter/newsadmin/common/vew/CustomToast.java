package com.example.peter.newsadmin.common.vew;

import android.widget.Toast;

import com.example.peter.newsadmin.app.AppContext;


/**
 * created by byron on 2016/12/18
 *
 * Toast显示内容
 */
public class CustomToast {
	private static Toast mToast, mToast2;

	//关闭显示
	public static void cancel() {
		if(mToast != null)
		mToast.cancel();
	}

	//显示Toast
	public static void show(CharSequence text, int duration) {
		if (mToast == null) {
			mToast = Toast.makeText(AppContext.mMainContext, text, duration);
		} else {
			mToast.setDuration(duration);
			mToast.setText(text);
		}

		mToast.show();
	}

}