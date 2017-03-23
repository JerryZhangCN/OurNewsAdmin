package com.example.peter.newsadmin.app;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;


/**
 * 记录应用全局信息
 *
 * 
 */
public final class AppContext {

	public static final Handler mMainHandler	= new Handler(Looper.getMainLooper());	// 公共Handler
		
	/*
	 * 初始化上下问
	 */
	public static Context mMainContext	= null;

	public static void init(Context MainContext) {
		mMainContext = MainContext.getApplicationContext();			
	}

	/**
	 * 销毁全局变量
	 */
	public static void destory() {
	}

}
