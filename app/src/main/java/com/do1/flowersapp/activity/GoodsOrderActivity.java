package com.do1.flowersapp.activity;

import android.os.Bundle;

import com.do1.flowersapp.business.listener.GoodsOrderListener;

/**
 * Created by Bruce Too
 * On 10/24/15.
 * At 10:55
 * 一键下单界面
 */
public class GoodsOrderActivity extends BaseOrderActivity implements GoodsOrderListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleInfo("选择商品", "订单预订");
        initData(true);
        setRecyclerView(true);
    }
}
