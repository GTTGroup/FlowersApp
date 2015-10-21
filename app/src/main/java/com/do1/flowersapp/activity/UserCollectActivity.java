package com.do1.flowersapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.fragment.UserCollectCommodityFragment;
import com.do1.flowersapp.fragment.UserCollectOrderFragment;
import com.do1.flowersapp.fragment.UserCollectShopFragment;
import com.do1.flowersapp.widget.PagerSlidingTabStrip;

/**
 * Created by gufeng
 * Created on 2015/10/16 00:34
 * 功能作用: 用户收藏列表--收藏的商品，收藏的店铺，收藏的订单
 */
public class UserCollectActivity extends BaseActivity {

    private PagerSlidingTabStrip tabStrip;
    private ViewPager pagerContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_collect);
        setTextTitle(getString(R.string.title_collect));
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tab_title);
        pagerContent = (ViewPager) findViewById(R.id.pager_content);
        pagerContent.setAdapter(new CollectPagerFragmentAdapter(getSupportFragmentManager()));
        tabStrip.setViewPager(pagerContent);

    }

    class CollectPagerFragmentAdapter extends FragmentPagerAdapter {

        private String[] title = {"商品","订单","商家"};

        public CollectPagerFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            if (position == 0) {
                fragment = new UserCollectCommodityFragment();
            } else if(position == 1) {
                fragment = new UserCollectOrderFragment();
            } else if(position == 2) {
                fragment = new UserCollectShopFragment();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }
}
