package com.do1.flowersapp.activity;

import android.os.Bundle;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;

/**
 * Created by gufeng
 * Created on 2015/10/25 23:37
 * 功能作用:忘记密码
 */
public class UserForgetPasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_forgetpassword);
        setTextTitle(getString(R.string.title_forgetpassword));
    }
}
