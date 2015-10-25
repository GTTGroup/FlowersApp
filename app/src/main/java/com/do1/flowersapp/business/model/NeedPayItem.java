package com.do1.flowersapp.business.model;

/**
 * Created by boluo on 2015/10/24.
 * 应付账款
 */
public class NeedPayItem {
    public String custNane;
    public float needPay;

    public NeedPayItem(String custNane, float needPay) {
        this.custNane = custNane;
        this.needPay = needPay;
    }
}
