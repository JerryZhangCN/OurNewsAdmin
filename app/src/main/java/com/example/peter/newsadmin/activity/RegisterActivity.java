package com.example.peter.newsadmin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.peter.newsadmin.R;
import com.example.peter.newsadmin.base.BaseActivity;
import com.example.peter.newsadmin.present.presentImpl.RegisterPresenter;
import com.example.peter.newsadmin.present.presentView.RegisterView;
import com.example.peter.newsadmin.utils.StringUtil;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity implements RegisterView {
    @BindView(R.id.btn_register_get_code)
    Button get_code;
    @BindView(R.id.btn_register_register)
    Button register;
    @BindView(R.id.register_number)
    EditText phone;
    @BindView(R.id.register_password)
    EditText password;
    @BindView(R.id.code_edit)
    EditText code;
    @BindString(R.string.register_cover_get_code)
    String recapture;
    @BindString(R.string.register_get_verification_code)
    String get_verification_code;
    @BindString(R.string.register_sms_send)
    String sms_send;

    private boolean result = true;
    private int time = 60;
    private int count = 0;
    private RegisterPresenter presenter = new RegisterPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initLogic();
    }

    @OnClick({R.id.btn_back_user, R.id.btn_register_get_code,R.id.btn_register_register})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_back_user: {
                finish();
                break;
            }
            case R.id.btn_register_get_code: {
//                showLoadingDialog();
                if (StringUtil.isEmpty(phone.getText().toString().trim())) {
                    showInfo("请输入手机号");
                    return;
                }
                if (phone.getText().toString().trim().length() != 11) {
                    showInfo("请输入正确的手机号");
                    return;
                }
                presenter.getCode(phone.getText().toString().trim());
                Toast.makeText(this, sms_send, Toast.LENGTH_SHORT).show();
                showTime();
                break;
            }
            case R.id.btn_register_register: {
                if (StringUtil.isEmpty(phone.getText().toString().trim())) {
                    showInfo("请输入手机号");
                    return;
                }
                if (phone.getText().toString().trim().length() != 11) {
                    showInfo("请输入正确的手机号");
                    return;
                }
                if (StringUtil.isEmpty(code.getText().toString().trim())) {
                    showInfo("请输入验证码");
                    return;
                }
                if (StringUtil.isEmpty(password.getText().toString().trim())) {
                    showInfo("请输入密码");
                    return;
                }
                if(password.getText().toString().trim().length()<8||password.getText().toString().trim().length()>12){
                    showInfo("密码长度为8到12位");
                    return;
                }
                presenter.register(phone.getText().toString().trim(), code.getText().toString().trim(),password.getText().toString().trim());
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


    @Override
    public void toHomeActivity() {
        Intent intent=new Intent(RegisterActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
