package com.do1.flowersapp.business.listener;

/**
 * Created by TuYong N1007
 * On 2015/10/23
 * At 16:12
 * 异常退款界面点击监听
 */
public interface ReturnGoodsListener {

    /**
     * 退款类型
     * @param position 第几个item
     */
    void onReturnTypeClick(int position);

    /**
     * 添加图片btn
     */
    void onAddBtnClick();

    /**
     * 退款说明文本
     */
    void onReturnIllustrate(String info);
}
