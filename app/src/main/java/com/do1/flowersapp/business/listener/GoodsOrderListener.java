package com.do1.flowersapp.business.listener;

import android.widget.TextView;

/**
 * Created by Bruce Too
 * On 10/24/15.
 * At 13:40
 * 一键订单 界面相关点击的监听
 */
public interface GoodsOrderListener {

    /**
     * 收件人名字，地点，电话的点击
     * @param textName
     * @param textPhone
     * @param textAddress
     */
   void onChooseLocation(TextView textName, TextView textPhone, TextView textAddress);

    /**
     * 交货时间的点击
     * @param textEndTime
     */
    void onChooseEndTime(TextView textEndTime);

    /**
     * 支付方法的点击
     * @param textPayWay
     */
    void onChoosePayWay(TextView textPayWay);

    /**
     * 进入花店的点击
     * @param storeId
     */
    void onEnterStore(String storeId);
}
