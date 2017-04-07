package com.example.peter.newsadmin.common.vew;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.peter.newsadmin.R;

/**
 * 自定义dialog，数据加载时使用
 */

public class MyProgressDialog extends Dialog {
	private ImageView mImageView;
	private TextView mTextView;
	private View view;
	private Context mContext;

	public MyProgressDialog(final Context context) {
		super(context, R.style.android_progress_dialog);
		this.mContext = context;


		// 加载布局文件
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.public_progress_dialog, null);
		mTextView = (TextView) view.findViewById(R.id.progress_dialog_txt);
		// dialog添加视图
		setContentView(view);
		getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		mImageView = (ImageView) view.findViewById(R.id.progress_dialog_img);
		Animation anim = AnimationUtils.loadAnimation(mContext, R.anim.progressbar);
		mImageView.startAnimation(anim);
	}

	public void setMsg(String msg) {
		mTextView.setText(msg);
	}

	public void setMsg(int msgId) {
		mTextView.setText(msgId);
	}

}
