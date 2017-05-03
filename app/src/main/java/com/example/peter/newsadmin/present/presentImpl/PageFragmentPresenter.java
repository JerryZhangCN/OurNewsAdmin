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
import com.example.peter.newsadmin.model.TypeNewsMode;
import com.example.peter.newsadmin.present.presentView.PageFragmentView;
import com.example.peter.newsadmin.utils.StringUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public class PageFragmentPresenter extends BasePresenter {
    private PageFragmentView view;
    private List<NewsModel> newsModels = new ArrayList<>();

    public PageFragmentPresenter(PageFragmentView _view) {
        this.view = _view;
    }

    public void getNews(int id) {
        switch (id) {
            case 1: {
                HttpConnectUtil.request("", APIType.REQUEST_HOME_NEWS, new HttpCallback());
                break;
            }
            default: {
                HttpConnectUtil.request("", APIType.REQUEST_HOME_NEWS, new HttpCallback());
                break;
//                Gson gson = new Gson();
//                NewList requestModel = new NewList();
//                requestModel.setType(id);
//                requestModel.setPage(1);
//                requestModel.setSize(10);
//                requestModel.setSort(1);
//                HttpConnectUtil.request(gson.toJson(requestModel).toString(), APIType.REQUEST_TYPE_NEWS, new HttpCallback());
//                break;
            }
        }
    }

    private void handMsg(List<TypeNewsMode> list) {
        newsModels.clear();
        for (TypeNewsMode item : list) {
            for (int i = 0; i < item.getList().length; i++) {
                this.newsModels.add(item.getList()[i]);
            }
        }
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

                case APIType.REQUEST_HOME_NEWS: {
                    {
                        GsonResponseParser<HomeNewsModel> parser = new GsonResponseParser<HomeNewsModel>() {
                        };
                        CommonResponse response = parser.parse(responseT);
                        Log.e(ContansString.LOG_MSG, "获取首页数据" + responseT);
                        if (isSuccess(response.getResult())) {
                            TypeNewsMode[] list = ((HomeNewsModel) response.getData()).getNews();
                            List<TypeNewsMode> list1 = new ArrayList<>();
                            for (int i = 0; i < list.length; i++)
                                list1.add(list[i]);
                            handMsg(list1);
                        } else {

                        }
                    }
                    break;
                }
                case APIType.REQUEST_TYPE_NEWS: {

                    GsonResponseParser<HomeNewsModel> parser = new GsonResponseParser<HomeNewsModel>() {
                    };
                    CommonResponse response = parser.parse(responseT);
                    Log.e(ContansString.LOG_MSG, "获取分类数据" + responseT);
                    if (isSuccess(response.getResult())) {
                        TypeNewsMode[] list = ((HomeNewsModel) response.getData()).getNews();
                        List<TypeNewsMode> list1 = new ArrayList<>();
                        for (int i = 0; i < list.length; i++)
                            list1.add(list[i]);
                        handMsg(list1);
                    } else {

                    }
                }
                default:
                    break;
            }
        }
    }

}
