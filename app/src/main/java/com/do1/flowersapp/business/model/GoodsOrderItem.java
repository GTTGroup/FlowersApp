package com.do1.flowersapp.business.model;

import java.util.List;

/**
 * Created by Bruce Too
 * On 10/24/15.
 * At 15:23
 * 单个Item的Bean
 */
public class GoodsOrderItem {
    //花店名
    public String storeName;
    public List<GoodsOrderSingle> flowers;

    //单个花店下的Model
    public static class GoodsOrderSingle {
        public String flowerUrl;
        public String flowerName;
        public int flowerDetail1; //20/扎
        public int flowerDetail2; //￥25/扎
        public String flowerDetail3; //红色 A级 单头
        public int counter; // 加减的个数
        public String tips;//备注
    }

    public int shipment;//运费
//    public int totalFlowers;//总共商品数
//    public int totalMoney;

}
