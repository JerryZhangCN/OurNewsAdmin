package com.example.peter.newsadmin.model;

/**
 * Created by cdxy_ on 2017/5/2.
 */

public class User {
    private String id;
    private String phone;
    private String nick_name;
    private String sex;
    private String sign;
    private String birthday;
    private String photo;
    private String token;

    private static User  mInstance;

    public static User getInstance() {
        if (mInstance == null) {
            mInstance = new User();
        }
        return mInstance;
    }
    public void cleanData(){
        this.id=null;
        this.phone=null;
        this.nick_name=null;
        this.sex=null;
        this.sign=null;
        this.birthday=null;
        this.photo=null;
        this.token=null;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
