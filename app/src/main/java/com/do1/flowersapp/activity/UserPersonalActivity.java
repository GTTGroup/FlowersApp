package com.do1.flowersapp.activity;

import android.os.Bundle;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;

/**
 * Created by gufeng
 * Created on 2015/10/25 11:55
 * 功能作用: 个人信息
 */
public class UserPersonalActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        setTextTitle(getString(R.string.title_personalinfo));
    }
}
