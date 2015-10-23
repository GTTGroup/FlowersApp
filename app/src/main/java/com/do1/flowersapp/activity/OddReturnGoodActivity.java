package com.do1.flowersapp.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bruce Too
 * On 10/23/15.
 * At 00:22
 * 异常退款，一键下单
 */
public class OddReturnGoodActivity extends BaseActivity{

    @Bind(R.id.recycler_view)
    RecyclerView mRecylerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_odd_return_good);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btn_top_back)
    public void onTopBack(){
        finish();
    }

    /**
     * 选择按钮
     */
    @OnClick(R.id.text_choose)
    public void onTopChoose(){

    }


}
