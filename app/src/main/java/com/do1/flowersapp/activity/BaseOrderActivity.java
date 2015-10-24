package com.do1.flowersapp.activity;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.do1.flowersapp.R;
import com.do1.flowersapp.business.adapter.GoodsOrderAdapter;
import com.do1.flowersapp.business.listener.GoodsOrderListener;
import com.do1.flowersapp.business.listener.OrderHomeUpdateListener;
import com.do1.flowersapp.business.model.GoodsOrderItem;
import com.do1.flowersapp.context.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Bruce Too
 * On 10/24/15.
 * At 22:23
 * 订单界面的BaseActivity
 */
public class BaseOrderActivity extends BaseActivity implements GoodsOrderListener {

    @Bind(R.id.text_choose)
    TextView mTopChoose;
    @Bind(R.id.top_title)
    TextView mTopTitle;
    @Bind(R.id.text_money)
    TextView mTotalMoney;
    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @Bind(R.id.text_total_count)
    TextView mTotalCount;

    protected List<GoodsOrderItem> datas;
    protected GoodsOrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_order);
        ButterKnife.bind(this);
    }

    protected void setTitleInfo(String titleRight,String titleCenter){
        mTopChoose.setText(titleRight);
        mTopTitle.setText(titleCenter);
    }

    /**
     * 设置recclerView
     * @param hasHeader 是否有头部 买家信息item
     */
    protected void setRecyclerView(boolean hasHeader){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.bottom = (int) getResources().getDimension(R.dimen.return_goods_item_decoration);
            }
        });
        mAdapter = new GoodsOrderAdapter(this,this,datas,hasHeader,new OrderHomeUpdateListener(){

            @Override
            public void onHomeUpdate(int position, GoodsOrderItem data) {
                Log.e("onHomeUpdate:",position+"");
                Log.e("onHomeUpdate:",data.flowers.get(position).counter+"");
                datas.set(position,data);
                updateHomeValue();
            }

            @Override
            public void onEnterStore(String storeId) {
                Toast.makeText(BaseOrderActivity.this,"点击进入花店item",Toast.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 模拟数据
     * @param isStore 数据中是否全是商铺
     */
    protected void initData(boolean isStore){
        datas = new ArrayList<>();
        if(isStore){//全是店铺的情况 模拟
            for(int i = 0; i < 3; i++){
                GoodsOrderItem item = new GoodsOrderItem();
                if(i == 0) {
                    item.shipment = 0;
                }else {
                    item.shipment = 100;
                }
                item.isStore = isStore;
                if (isStore){
                    item.storeName = "花店"+i;
                }else {
                    item.floristsName = "花艺师-0";
                }
                item.flowers = new ArrayList<>();
                for(int j = 0; j < i+1; j++){
                    GoodsOrderItem.GoodsOrderSingle single = new GoodsOrderItem.GoodsOrderSingle();
                    if(isStore) {
                        single.flowerDetail1 = 10;
                        single.flowerDetail3 = "红色 A级 单头";
                        if (j % 2 == 0) {
                            single.flowerDetail2 = 20;
                            single.counter = 20;
                        } else {
                            single.flowerDetail2 = 30;
                            single.counter = 10;
                        }
                        single.flowerName = "花花" + j;
                    }
                    single.tips = "什么j8备注";
                    single.flowerUrl = "http://pic7.nipic.com/20100424/4271569_235714000888_2.jpg";
                    item.flowers.add(single);
                }
                datas.add(item);
            }
        }else { // 有花艺师的情况 模拟
            for (int i = 0; i < 3; i++) {
                isStore = !isStore;
                GoodsOrderItem item = new GoodsOrderItem();
                if (i == 0) {
                    item.shipment = 0;
                } else {
                    item.shipment = 100;
                }
                item.isStore = isStore;
                if (isStore) {
                    item.storeName = "花店" + i;
                } else {
                    item.floristsName = "花艺师-0";
                }
                item.flowers = new ArrayList<>();
                for (int j = 0; j < i + 1; j++) {
                    GoodsOrderItem.GoodsOrderSingle single = new GoodsOrderItem.GoodsOrderSingle();
                    if (isStore) {
                        single.flowerDetail1 = 10;
                        single.flowerDetail3 = "红色 A级 单头";
                        if (j % 2 == 0) {
                            single.flowerDetail2 = 20;
                            single.counter = 20;
                        } else {
                            single.flowerDetail2 = 30;
                            single.counter = 10;
                        }
                        single.flowerName = "花花" + j;
                    } else {
                        single.counter = 10;
                        single.flowerDetail2 = 350;
                        single.flowerDetail3 = "服务范围 广州 深圳";
                        single.flowerDetail4 = "高级花艺师";
                        single.flowerName = "牛逼花艺师" + j;
                    }
                    single.tips = "什么j8备注";
                    single.flowerUrl = "http://pic7.nipic.com/20100424/4271569_235714000888_2.jpg";
                    item.flowers.add(single);
                }
                datas.add(item);
            }
        }
        updateHomeValue();
    }

    /**
     * 更新总共需要的钱和花的数量
     */
    protected void updateHomeValue() {
        int totalMoney = 0;
        int totalCounter = 0;
        for(GoodsOrderItem item : datas){
            if(item.isStore) {
                totalMoney += item.shipment;
            }
            for(GoodsOrderItem.GoodsOrderSingle single : item.flowers){
                totalMoney += single.counter * single.flowerDetail2;
                if(item.isStore) {//花店
                    totalCounter += single.counter;
                }
            }
        }

        mTotalMoney.setText("￥" + totalMoney);
        mTotalCount.setText("共" + totalCounter + "件商品");
    }

    @OnClick(R.id.btn_top_back)
    public void onTopBack() {
        finish();
    }

    @OnClick(R.id.btn_confirm)
    public void onConfirm(){
        Toast.makeText(this,"确定提交",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.text_choose)
    public void onChooseClick() {
        Toast.makeText(this, "选择商品", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChooseLocation(TextView textName, TextView textPhone, TextView textAddress) {
        Toast.makeText(this,"点击收货人item",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChooseEndTime(TextView textEndTime) {
        Toast.makeText(this,"点击交货日期item",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChoosePayWay(TextView textPayWay) {
        Toast.makeText(this,"点击支付方式item",Toast.LENGTH_SHORT).show();
    }
}
