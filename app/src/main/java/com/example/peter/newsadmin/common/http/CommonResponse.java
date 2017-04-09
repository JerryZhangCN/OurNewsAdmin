package com.example.peter.newsadmin.common.http;

/**
 * Created by cdxy_ on 2017/4/8.
 */

public class CommonResponse<T> {
    private int error_code;
    private String message;
    private String result;
    private T data;

    public int getStatus() {
        return error_code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setStatus(int status) {
        this.error_code = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
