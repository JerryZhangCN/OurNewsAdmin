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
import com.example.peter.newsadmin.model.NewsModelVO;
import com.example.peter.newsadmin.model.TypeNewsMode;
import com.example.peter.newsadmin.model.User;
import com.example.peter.newsadmin.present.presentView.EditFragmentView;
import com.example.peter.newsadmin.present.presentView.PageFragmentView;
import com.example.peter.newsadmin.utils.StringUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public class EditFragmentPresenter extends BasePresenter {
    private EditFragmentView view;
    private List<NewsModel> newsModels = new ArrayList<>();

    public EditFragmentPresenter(EditFragmentView _view) {
        this.view = _view;
    }

    public void getNews() {
                Map<String, String> map = new HashMap<>();
                map.put("id", User.getInstance().getId());
                map.put("token", User.getInstance().getToken());
                map.put("page", "1");
                map.put("size", "10");
                map.put("sort", "1");
                HttpConnectUtil.requestParams(map, APIType.REQUEST_GET_OWN_NEWS, new HttpCallback());



    }

    private void handMsg( List<NewsModel> newsModel) {
        newsModels.clear();
        newsModels.addAll(newsModel);
        view.updateMsg(this.newsModels);
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

                case APIType.REQUEST_GET_OWN_NEWS: {

                    GsonResponseParser<NewsModelVO> parser = new GsonResponseParser<NewsModelVO>() {
                    };
                    CommonResponse response = parser.parse(responseT);
                    Log.e(ContansString.LOG_MSG, "获取分类数据" + responseT);
                    if (isSuccess(response.getResult())) {
                        handMsg(((NewsModelVO)response.getData()).getNews());
                    } else {

                    }
                }
                default:
                    break;
            }
        }
    }

}
