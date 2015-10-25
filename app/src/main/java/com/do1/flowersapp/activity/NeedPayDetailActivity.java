package com.do1.flowersapp.activity;

import android.os.Bundle;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by boluo on 2015/10/24.
 * 应付款明细
 */
public class NeedPayDetailActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_pay_detail);
    }
}
