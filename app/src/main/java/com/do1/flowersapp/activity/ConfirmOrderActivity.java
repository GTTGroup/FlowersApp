package com.do1.flowersapp.activity;

import android.os.Bundle;

/**
 * Created by Bruce Too
 * On 10/24/15.
 * At 22:20
 * 确认订单界面
 */
public class ConfirmOrderActivity extends BaseOrderActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleInfo("", "订单预订");
        initData(false);
        setRecyclerView(false);
    }
}
