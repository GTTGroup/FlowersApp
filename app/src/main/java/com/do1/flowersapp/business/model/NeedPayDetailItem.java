package com.do1.flowersapp.business.model;

/**
 * Created by boluo on 2015/10/24.
 * 应付账款明细
 */
public class NeedPayDetailItem {
    public String orderId;
    public String custId;
    public String custName;
    public String createTime;
    public float needPay;

    public NeedPayDetailItem(String orderId, String custName, String createTime, float needPay) {
        this.orderId = orderId;
        this.custName = custName;
        this.createTime = createTime;
        this.needPay = needPay;
    }
}
