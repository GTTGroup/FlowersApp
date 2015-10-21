package com.do1.flowersapp.activity;

import android.os.Bundle;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;

/**
 * Created by gufeng
 * Created on 2015/10/21 17:30
 * 功能作用: 新增用户收货地址
 */
public class AddUserAddressActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_address);
        setTextTitle(getString(R.string.title_add_user_address));
    }
}
