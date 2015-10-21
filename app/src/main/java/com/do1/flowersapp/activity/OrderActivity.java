package com.do1.flowersapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.fragment.OrderListFragment;
import com.do1.flowersapp.fragment.UnPayOrderFragment;
import com.do1.flowersapp.widget.PagerSlidingTabStrip;

/**
 * Created by gufeng
 * Created on 2015/10/17 21:04
 * 功能作用: 用户订单
 */
public class OrderActivity extends BaseActivity {

    private PagerSlidingTabStrip tabStrip;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tab_title);
        viewPager = (ViewPager) findViewById(R.id.pager_container);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] title = {"待处理","全部"};

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                if (position == 0) {
                    fragment = new UnPayOrderFragment();
                } else if(position == 1) {
                    fragment = new OrderListFragment();
                }
                return fragment;
            }

            @Override
            public int getCount() {
                return 2;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return title[position];
            }
        });
        tabStrip.setViewPager(viewPager);
    }
}
