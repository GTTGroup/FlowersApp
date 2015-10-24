package com.do1.flowersapp.business.listener;

import com.do1.flowersapp.business.model.GoodsOrderItem;

/**
 * Created by Bruce Too
 * On 10/24/15.
 * At 22:33
 * 订单主界面刷新监听
 */
public interface OrderHomeUpdateListener {
    /**
     * 主界面刷新监听
     * @param position
     * @param data
     */
    void onHomeUpdate(int position, GoodsOrderItem data);

    /**
     * 进入花店的点击
     * @param storeId
     */
    void onEnterStore(String storeId);
}
