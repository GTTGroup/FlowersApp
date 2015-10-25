package com.do1.flowersapp.business.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boluo on 2015/10/24.
 * 应付账款明细
 */
public class NeedPayDetail {
    public float totalQuotas;
    public float useQuotas;
    public float remainQuotas;
    public float needPayQuotas;
    public List<NeedPayDetailItem> items = new ArrayList<NeedPayDetailItem>();

    public NeedPayDetail(float totalQuotas, float useQuotas, float remainQuotas, float needPayQuotas) {
        this.totalQuotas = totalQuotas;
        this.useQuotas = useQuotas;
        this.remainQuotas = remainQuotas;
        this.needPayQuotas = needPayQuotas;
    }
}
