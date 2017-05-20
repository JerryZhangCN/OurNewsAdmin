package com.example.peter.newsadmin.present.presentImpl;

import android.util.Log;

import com.example.peter.newsadmin.base.BasePresenter;
import com.example.peter.newsadmin.common.ContansString;
import com.example.peter.newsadmin.common.http.APIType;
import com.example.peter.newsadmin.common.http.CommonResponse;
import com.example.peter.newsadmin.common.http.GsonResponseParser;
import com.example.peter.newsadmin.common.http.HttpConnectUtil;
import com.example.peter.newsadmin.model.NewsModel;
import com.example.peter.newsadmin.model.User;
import com.example.peter.newsadmin.present.presentView.RegisterView;
import com.example.peter.newsadmin.utils.DateUtil;
import com.example.peter.newsadmin.utils.MD5Tool;
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

public class RegisterPresenter extends BasePresenter {
    private RegisterView view;
    private List<NewsModel> newsModels = new ArrayList<>();
    private String phone;
    private String code;

    public RegisterPresenter(RegisterView _view) {
        this.view = _view;
    }

    public void getCode(String phoneNumber) {
        String date = DateUtil.getTimeStamp();
        Map<String,String> map=new HashMap<>();
        map.put("phone",phoneNumber);
        map.put("time",date);
        map.put("key",MD5Tool.getMD5(ContansString.APP_KEY + phoneNumber + date));
        Log.e("获取验证码发送参数",map.toString());
        HttpConnectUtil.requestParams(map,APIType.REQUEST_GET_CODE,new HttpCallback());
    }

    private void handCode(GetCode code){
        this.setPhone(code.getPhone());
        this.setCode(code.getCode());
    }
    public void register(String phone,String code,String password){
        if(!phone.equals(this.getPhone())||!code.equals(this.getCode())){
            view.showInfo("验证码错误");
            return;
        }
        String date = DateUtil.getTimeStamp();
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("time",date);
        map.put("code",code);
        map.put("password",password);
        map.put("key",MD5Tool.getMD5(ContansString.APP_KEY + code + date));
        Log.e("注册发送参数",map.toString());
        HttpConnectUtil.requestParams(map,APIType.REQUEST_REGISTER,new HttpCallback());
    }
    public void login(String phone,String code){
        if(!phone.equals(this.getPhone())||!code.equals(this.getCode())){
            view.showInfo("验证码错误");
            return;
        }
        String date = DateUtil.getTimeStamp();
        Map<String,String> map=new HashMap<>();
        map.put("phone",phone);
        map.put("time",date);
        map.put("code",code);
        map.put("key",MD5Tool.getMD5(ContansString.APP_KEY + code + date));
        Log.e("登陆发送参数",map.toString());
        HttpConnectUtil.requestParams(map,APIType.REQUEST_LOGIN,new HttpCallback());
    }

    private void handRegister(User user){
        User.getInstance().setId(user.getId());
        User.getInstance().setPhone(user.getPhone());
        User.getInstance().setBirthday(user.getBirthday());
        User.getInstance().setNick_name(user.getNick_name());
        User.getInstance().setPhoto(user.getPhoto());
        User.getInstance().setSex(user.getSex());
        User.getInstance().setSign(user.getSign());
        User.getInstance().setToken(user.getToken());
        view.toHomeActivity();
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

                case APIType.REQUEST_GET_CODE: {
                    {
                        GsonResponseParser<GetCode> parser = new GsonResponseParser<GetCode>() {
                    };
                    CommonResponse response = parser.parse(responseT);
                    Log.e(ContansString.LOG_MSG, "获取验证码数据" + responseT);
                    if (isSuccess(response.getResult())) {
                       handCode((GetCode) response.getData());
                    }
                }
                break;
                }
                case APIType.REQUEST_LOGIN:
                case APIType.REQUEST_REGISTER:{
                    GsonResponseParser<User> parser = new GsonResponseParser<User>() {
                    };
                    CommonResponse response = parser.parse(responseT);
                    Log.e(ContansString.LOG_MSG, "注册返回数据" + responseT);
                    if (isSuccess(response.getResult())) {
                        handRegister((User) response.getData());
                    }
                break;
                }
                default:
                    break;
            }
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
