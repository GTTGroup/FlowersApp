package com.do1.flowersapp.business.model;

/**
 * Created by boluo on 2015/10/24.
 * 应付账款
 */
public class NeedPayItem {
    public String custName;
    public float needPay;

    public NeedPayItem(String custName, float needPay) {
        this.custName = custName;
        this.needPay = needPay;
    }
}
