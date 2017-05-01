package com.example.peter.newsadmin.present.presentImpl;

import android.util.Log;

import com.example.peter.newsadmin.base.BasePresenter;
import com.example.peter.newsadmin.common.ContansString;
import com.example.peter.newsadmin.common.http.APIType;
import com.example.peter.newsadmin.common.http.APIURL;
import com.example.peter.newsadmin.common.http.CommonResponse;
import com.example.peter.newsadmin.common.http.GsonResponseParser;
import com.example.peter.newsadmin.model.NewsModel;
import com.example.peter.newsadmin.present.presentView.AddNewsFragmentView;
import com.example.peter.newsadmin.utils.StringUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public class AddNewsFragmentPresenter extends BasePresenter {
    private AddNewsFragmentView view;
    private List<NewsModel> newsModels = new ArrayList<>();

    public AddNewsFragmentPresenter(AddNewsFragmentView _view) {
        this.view = _view;
    }

    public void updataPic(String path, int id) {
        File file=new File(path);
        OkHttpUtils.post().addFile("image/*", file.getName(), file).url(APIURL.PULL_PIC).id(APIType.REQUEST_PULL_IMAGE).build().execute(new HttpCallback());


    }
//
//    private void handMsg(List<TypeNewsMode> list) {
//        newsModels.clear();
//        for (TypeNewsMode item : list) {
//            for (int i = 0; i < item.getList().length; i++) {
//                this.newsModels.add(item.getList()[i]);
//            }
//        }
//        view.updateMsg(this.newsModels);
//    }

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

                case APIType.REQUEST_PULL_IMAGE: {
                    {
                        GsonResponseParser<NewsModel> parser = new GsonResponseParser<NewsModel>() {
                        };
                        CommonResponse response = parser.parse(responseT);
                        Log.e(ContansString.LOG_MSG, "上传图片数据" + responseT);
                        if (isSuccess(response.getResult())) {

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
