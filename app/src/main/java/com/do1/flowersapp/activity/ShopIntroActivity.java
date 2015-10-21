package com.do1.flowersapp.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.widget.PagerSlidingTabStrip;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by gufeng
 * Created on 2015/10/19 22:28
 * 功能作用: 商家介绍
 */
public class ShopIntroActivity extends BaseActivity {

    private PagerSlidingTabStrip tabStrip;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tab_title);
        viewPager = (ViewPager) findViewById(R.id.pager_content);
        viewPager.setAdapter(new PagerAdapter() {

            private String[] titles = {"基本信息","认证信息","联系方式"};

            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView((SimpleDraweeView) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView textView = (TextView) LayoutInflater.from(ShopIntroActivity.this).inflate(R.layout.pager_item_shop_intro,null);
                container.addView(textView);
                return textView;
            }
        });
        tabStrip.setViewPager(viewPager);
    }


}
