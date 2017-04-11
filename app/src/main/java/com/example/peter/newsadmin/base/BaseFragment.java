
package com.example.peter.newsadmin.base;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.peter.newsadmin.common.StatusCode;
import com.example.peter.newsadmin.common.vew.CustomToast;

import butterknife.BindString;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Byron on 2016-12-18.
 *
 * 基类提供一些共有属性操作
 */
public abstract class BaseFragment extends Fragment implements BaseView {
    protected static final String TAG = BaseFragment.class.getName();
    protected Unbinder unbinder;

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(unbinder!=null)
         unbinder.unbind();
    }

    /**
     * 参数设置
     */
    protected void initCommonLogic(View view) {
        unbinder = ButterKnife.bind(this, view);
        this.initTopbar();
    }


    public String getStaticTag() {
        return this.getClass().getName();
    }

  /*  protected void showFragment(int res,String tag,Fragment frm){
        if (frm != null) {
            FragmentManager manager = getChildFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            hideFragments(manager, transaction);
            if (manager.findFragmentByTag(tag) == null) {
                transaction.add(res, frm, tag);
            }
            transaction.show(frm);
            transaction.commitAllowingStateLoss();
        }
    }
    protected void hideFragments(FragmentManager manager, FragmentTransaction transaction) {
        List<Fragment> fragments = manager.getFragments();
        if (fragments != null) {
            for (Fragment f : fragments) {
                if (f != null) {
                    transaction.hide(f);
                }
            }
        }
    }*/

    public abstract  void renderFragment();

    @Override
    public void showInfo(int type, String msg) {
        switch (type) {
            case StatusCode.SHOW_INFO_TOAST:
                CustomToast.show(msg, Toast.LENGTH_SHORT);
                break;
//            case StatusCode.SHOW_INFO_DIALOG:
//                DialogUtil.showInfoDialog(getActivity(),msg);
//                break;
            default:
                CustomToast.show(msg, Toast.LENGTH_SHORT);
                break;

        }
    }

    @Override
    public void dismissError() {

    }

    @Override
    public void initTopbar() {

    }

/*
    public BizMeetingFinishedListener meetingFinishListener() {
        BizMeetingFinishedListener listener=new BizMeetingFinishedListener() {
            @Override
            public void onFinished() {
                BizConfSDKUtils.getInstance(getActivity()).saveMeetingIdToSP();
                Toast.makeText(getActivity(),"会议结束",Toast.LENGTH_SHORT).show();
            }
        };
        return listener;
    }*/
    /**
     * 监听editText获得焦点事件
     * @param hint
     * @return
     */
    public View.OnFocusChangeListener OnFocusChangListener(final String hint){
        View.OnFocusChangeListener listener=new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    ((TextView) v).setHint(hint);
                } else {
                    ((TextView) v).setHint("");
                }
            }
        };
        return listener;
    }

    public void onPause() {
        super.onPause();
        dismissLoadingDialog();// pause时关闭加载框
    }
/*    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (hidden) {// 不在最前端界面显示
Log.e(BizConfClientConfig.DEBUG_TAG,"fragment隐藏 ："+this.getClass().getName());
            MobclickAgent.onPageEnd(this.getClass().getName());
        } else {// 重新显示到最前端中
Log.e(BizConfClientConfig.DEBUG_TAG,"fragment显示 ："+this.getClass().getName());
            MobclickAgent.onPageStart(this.getClass().getName());
        }
    }*/

    @Override
    public void showLoadingDialog() {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showError(int type, String errorMsg) {

    }

    @Override
    public void showInfo(String msg) {
      CustomToast.show(msg, Toast.LENGTH_SHORT);
    }
}
