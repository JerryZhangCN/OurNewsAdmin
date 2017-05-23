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

public class LoginActivity extends BaseActivity implements RegisterView{
    @BindView(R.id.btn_register_register)
    Button register;
    @BindView(R.id.register_number)
    EditText phone;
    @BindView(R.id.register_password)
    EditText password;
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
        setContentView(R.layout.activity_login);
        initLogic();
    }

    @OnClick({R.id.btn_back_user_login,R.id.btn_register_register,R.id.go_register})
    public void onclick(View view) {
        switch (view.getId()) {
            case R.id.btn_back_user_login: {
                finish();
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
                if (StringUtil.isEmpty(password.getText().toString().trim())) {
                    showInfo("请输入验证码");
                    return;
                }
                presenter.login(phone.getText().toString().trim(), password.getText().toString().trim());
                break;
            }
            case R.id.go_register:{
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    protected void initLogic() {
        super.initLogic();
    }




    @Override
    public void toHomeActivity() {
        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
