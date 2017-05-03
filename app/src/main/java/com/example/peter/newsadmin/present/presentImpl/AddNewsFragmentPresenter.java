package com.example.peter.newsadmin.present.presentImpl;

import android.support.annotation.Nullable;
import android.util.Log;

import com.example.peter.newsadmin.base.BasePresenter;
import com.example.peter.newsadmin.common.ContansString;
import com.example.peter.newsadmin.common.http.APIType;
import com.example.peter.newsadmin.common.http.APIURL;
import com.example.peter.newsadmin.common.http.CommonResponse;
import com.example.peter.newsadmin.common.http.GsonResponseParser;
import com.example.peter.newsadmin.common.http.HttpConnectUtil;
import com.example.peter.newsadmin.model.User;
import com.example.peter.newsadmin.present.presentView.AddNewsFragmentView;
import com.example.peter.newsadmin.utils.StringUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public class AddNewsFragmentPresenter extends BasePresenter {
    private AddNewsFragmentView view;
    private String cover;

    public AddNewsFragmentPresenter(AddNewsFragmentView _view) {
        this.view = _view;
    }

    public void updataPic(final Map<Integer, String> map, final List<Integer> list, int type, String title) {

        if (list.size() > 0) {
            List<File> fileList = new ArrayList<>();
            for (int item : list) {
                File file = new File(map.get(item));
                fileList.add(file);
            }

            //OkHttpUtils.post().addFile("upload",fileList).url(APIURL.PULL_PIC).id(APIType.REQUEST_PULL_IMAGE).build().execute(new HttpCallback());
            com.lzy.okhttputils.OkHttpUtils.post(APIURL.PULL_PIC).addFileParams("upload", fileList).execute(new com.lzy.okhttputils.callback.StringCallback() {
                @Override
                public void onResponse(boolean isFromCache, String s, Request request, @Nullable Response response) {
                    GsonResponseParser<String[]> parser = new GsonResponseParser<String[]>() {
                    };
                    CommonResponse response1 = null;
                    response1 = parser.parse(s);

                    Log.e(ContansString.LOG_MSG, "上传图片数据" + s);
                    if (isSuccess(response1.getResult())) {
                        String[] image = (String[]) response1.getData();
                        setCover(image[1]);
                        for (int i = 0; i < list.size(); i++) {
                            Gson gson = new Gson();
                            Pic pic = new Pic();
                            pic.setName(image[i]);
                            String item = "<json>" + gson.toJson(pic).toString() + "</json>";
                            map.put(list.get(i), item);
                        }
                    } else {
                        view.showInfo("上传图片失败");
                    }
                }
            });
        }
        putNews(map, title, type);
    }


    private void putNews(Map<Integer, String> map, String title, int type) {
        Map<String, String> params = new HashMap<>();
        params.put("mid", User.getInstance().getId());
        params.put("token", User.getInstance().getToken());
        params.put("title", title);
        if (StringUtil.isEmpty(cover))
            params.put("cover", "NoImage");
        else
            params.put("cover", cover);
        params.put("abstract", title);
        params.put("content", doMap(map));
        params.put("type", String.valueOf(type));
        params.put("push", "1");
        HttpConnectUtil.requestParams(params, APIType.REQUEST_PUT_NEWS, new HttpCallback());

    }

    private String doMap(Map<Integer, String> map) {
        String s = null;
        for (int i = 0; i < map.size(); i++) {
            if (s == null)
                s = map.get(i);
            else
                s = s + map.get(i);
        }
        return s;
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

                case APIType.REQUEST_PULL_IMAGE: {
                    {
                        GsonResponseParser<String[]> parser = new GsonResponseParser<String[]>() {
                        };
                        CommonResponse response = parser.parse(responseT);
                        Log.e(ContansString.LOG_MSG, "上传图片数据" + responseT);
                        if (isSuccess(response.getResult())) {

                        } else {

                        }
                    }
                    break;
                }
                case APIType.REQUEST_PUT_NEWS: {
                    {
                        GsonResponseParser<String[]> parser = new GsonResponseParser<String[]>() {
                        };
                        CommonResponse response = parser.parse(responseT);
                        Log.e(ContansString.LOG_MSG, "上传新闻数据" + responseT);
                        if (isSuccess(response.getResult())) {
                            view.showInfo("上传新闻成功");
                            view.cleanData();
                        } else {
                            view.showInfo("上传新闻失败");
                            view.cleanData();
                        }
                    }
                    break;
                }

                default:
                    break;
            }
        }
    }

    class Pic {
        private String[] data;
        private String name;
        private String width = "500";
        private String height = "550";

        public String[] getData() {
            return data;
        }

        public void setData(String[] data) {
            this.data = data;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
