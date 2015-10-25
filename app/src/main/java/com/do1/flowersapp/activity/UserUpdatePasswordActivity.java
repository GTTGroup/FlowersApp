package com.do1.flowersapp.activity;

import android.os.Bundle;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;

/**
 * Created by gufeng
 * Created on 2015/10/26 03:45
 * 功能作用: 修改密码
 */
public class UserUpdatePasswordActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        setTextTitle(getString(R.string.title_updatepassword));
    }
}
