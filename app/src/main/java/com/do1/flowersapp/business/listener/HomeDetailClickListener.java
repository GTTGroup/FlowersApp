package com.do1.flowersapp.business.listener;

import android.widget.TextView;

/**
 * Created by TuYong N1007
 * On 2015/10/22
 * At 16:22
 * 商家详情home tab点击监听
 */
public interface HomeDetailClickListener{
    //热门花点击 包含更多按钮
    void onHotFlowerClick(TextView view);

    //普通Item点击
    void onItemClick();

    //顶部广告栏点击
    void onTopAdClick();
}
