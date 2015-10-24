package com.do1.flowersapp.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bruce Too
 * On 10/24/15.
 * At 10:55
 */
public class GoodsOrderActivity extends BaseActivity {

    @Bind(R.id.text_choose)
    TextView mTopChoose;
    @Bind(R.id.top_title)
    TextView mTopTitle;
    @Bind(R.id.text_money)
    TextView mTotalMoney;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order);
        ButterKnife.bind(this);

        mTopChoose.setText("选择商品");
        mTopTitle.setText("订单预订");


    }

    @OnClick(R.id.btn_top_back)
    public void onTopBack() {
        finish();
    }

    @OnClick(R.id.btn_confirm)
    public void onConfirm(){

    }

}
