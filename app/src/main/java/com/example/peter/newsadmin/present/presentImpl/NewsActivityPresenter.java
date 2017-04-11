package com.example.peter.newsadmin.present.presentImpl;

import android.util.Log;

import com.example.peter.newsadmin.base.BasePresenter;
import com.example.peter.newsadmin.common.ContansString;
import com.example.peter.newsadmin.common.http.APIType;
import com.example.peter.newsadmin.common.http.CommonResponse;
import com.example.peter.newsadmin.common.http.GsonResponseParser;
import com.example.peter.newsadmin.common.http.HttpConnectUtil;
import com.example.peter.newsadmin.model.HomeNewsModel;
import com.example.peter.newsadmin.model.NewsModel;
import com.example.peter.newsadmin.model.RequestModel;
import com.example.peter.newsadmin.model.TypeNewsMode;
import com.example.peter.newsadmin.present.presentView.NewsActivityView;
import com.example.peter.newsadmin.present.presentView.PageFragmentView;
import com.example.peter.newsadmin.utils.StringUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public class NewsActivityPresenter extends BasePresenter {
    private NewsActivityView view;

    public NewsActivityPresenter(NewsActivityView _view) {
        this.view = _view;
    }

    public void getNews(int id) {
        Gson gson = new Gson();
        RequestModel requestModel = new RequestModel();
        requestModel.setNid(id);
        HttpConnectUtil.request(gson.toJson(requestModel).toString(), APIType.REQUEST_GET_NEWS_CONTENT, new HttpCallback());
    }

    private void handMsg(NewsModel model) {

        view.updateMsg(model);
    }

    private class HttpCallback extends StringCallback {
        @Override
        public void onBefore(Request request, int id) {
            view.showLoadingDialog();
        }

        @Override
        public void onAfter(int id) {
            view.dismissLoadingDialog();

        }

        @Override
        public void onError(Call call, Exception e, int id) {
            view.showInfo("获取数据失败");
            view.dismissLoadingDialog();
        }

        @Override
        public void onResponse(String responseT, int id) {
            Log.e(TAG, "onResponse：complete: " + responseT);

            if (StringUtil.isEmpty(responseT)) {
                view.showInfo("数据为空");
                return;
            }

            switch (id) {

                case APIType.REQUEST_HOME_NEWS: {
                    {
                        GsonResponseParser<NewsModel> parser = new GsonResponseParser<NewsModel>() {
                        };
                        CommonResponse response = parser.parse(responseT);
                        Log.e(ContansString.LOG_MSG, "获取新闻内容数据" + responseT);
                        if (isSuccess(response.getResult())) {
                            handMsg((NewsModel)response.getData());
                        } else {

                        }
                    }
                    break;
                }
                default:
                    break;
            }
        }
    }

}
