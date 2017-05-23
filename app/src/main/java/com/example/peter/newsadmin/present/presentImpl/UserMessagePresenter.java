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
import com.example.peter.newsadmin.model.NewsModel;
import com.example.peter.newsadmin.model.User;
import com.example.peter.newsadmin.present.presentView.RegisterView;
import com.example.peter.newsadmin.present.presentView.UserMessageView;
import com.example.peter.newsadmin.utils.DateUtil;
import com.example.peter.newsadmin.utils.MD5Tool;
import com.example.peter.newsadmin.utils.StringUtil;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
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

public class UserMessagePresenter extends BasePresenter {
    private UserMessageView view;
    private String pic;

    public UserMessagePresenter(UserMessageView _view) {
        this.view = _view;
    }

    public void userPic(String path){
        File file = new File(path);
        OkHttpUtils.post().addFile("upload", file.getName(), file).url(APIURL.PULL_PIC).id(APIType.REQUEST_PULL_IMAGE).build().execute(new HttpCallback());
    }

    public void update(String sex,String nickName,String sign,String brith) {
        if(pic==null)
            pic=User.getInstance().getPhoto();
        Map<String,String> map=new HashMap<>();
        map.put("id",User.getInstance().getId());
        map.put("token",User.getInstance().getToken());
        map.put("nick_name",nickName);
        map.put("sex",sex);
        map.put("sign",sign);
        map.put("birthday",brith);
        map.put("photo",pic);
        Log.e("修改管理员信息发送参数",map.toString());
        HttpConnectUtil.requestParams(map,APIType.REQUEST_UPDATE_INFO,new HttpCallback());
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
                case APIType.REQUEST_PULL_IMAGE :{
                    GsonResponseParser<String[]> parser = new GsonResponseParser<String[]>() {
                    };
                    CommonResponse response = parser.parse(responseT);
                    String[] s= (String[]) response.getData();
                    setPic(s[0]);
                    Log.e(ContansString.LOG_MSG, "上传图片数据" + responseT);

                break;
                }
                case APIType.REQUEST_UPDATE_INFO: {
                    {
                        GsonResponseParser<User> parser = new GsonResponseParser<User>() {
                        };
                        CommonResponse response = parser.parse(responseT);
                        if(response.getResult().equals("success"))
                        Log.e(ContansString.LOG_MSG, "修改管理员信息数据" + responseT);

                    }
                    break;
                }
            }
        }
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    class GetCode {
        private String phone;
        private String time;
        private String key;
        private String code;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

}
