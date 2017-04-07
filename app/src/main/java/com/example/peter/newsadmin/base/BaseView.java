package com.example.peter.newsadmin.base;

/**
 * Created by Byron on 2016-12-28.
 */

public interface BaseView {

    /**
     *  显示loading框
     */
    void showLoadingDialog();

    /**
     *  隐藏loading框
     */
    void dismissLoadingDialog();

    /**
     *  显示error
     */
    void showError(int type, String errorMsg);

    /**
     *  显示一般信息
     */
    void showInfo(String msg);

    void showInfo(int type, String msg);

    /**
     *  隐藏error
     */
    void dismissError();

    /**
     * topBar初始化
     */
    void initTopbar();

    /**
     * 启动会议成功结束回调
     */
//    BizMeetingFinishedListener meetingFinishListener();

}
