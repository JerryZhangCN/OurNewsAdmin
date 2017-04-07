package com.example.peter.newsadmin.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseActivity;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.btn_register_get_code)
    Button get_code;
    @BindView(R.id.btn_register_register)
    Button register;
    @BindString(R.string.register_cover_get_code)
    String recapture;
    @BindString(R.string.register_get_verification_code)
    String get_verification_code;
    @BindString(R.string.register_sms_send)
    String sms_send;

    private boolean result = true;
    private int time = 60;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initLogic();
    }

    @OnClick({R.id.btn_back_user, R.id.btn_register_get_code})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_back_user: {
                finish();
            }
            case R.id.btn_register_get_code: {
                showLoadingDialog();
                Toast.makeText(this, sms_send, Toast.LENGTH_SHORT).show();
                showTime();
                break;
            }
        }
    }

    @Override
    protected void initLogic() {
        super.initLogic();
    }

    public void showTime() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (result) {
                    time--;
                    try {
                        Thread.sleep(1000);
                        get_code.post(new Runnable() {
                            @Override
                            public void run() {
                                get_code.setText(time + recapture);
                                get_code.setClickable(false);
                                get_code.setBackgroundDrawable(getResources().getDrawable(R.drawable.public_user_button_pressed));
                            }
                        });
                        if (time <= 1) {
                            count = 0;
                            result = false;
                            get_code.post(new Runnable() {
                                @Override
                                public void run() {
                                    get_code.setText(get_verification_code);
                                    get_code.setClickable(true);
                                    get_code.setBackgroundDrawable(getResources().getDrawable(R.drawable.public_button_shape));
                                }
                            });
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                result = true;
                time = 60;
            }
        }).start();
    }


}
