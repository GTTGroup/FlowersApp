package com.do1.flowersapp.activity;

import android.os.Bundle;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;

/**
 * Created by gufeng
 * Created on 2015/10/25 22:02
 * 功能作用: 系统设置
 */
public class SettingActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setTextTitle(getString(R.string.title_setting));
    }
}
