package com.do1.flowersapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.fragment.DefaultLoginFragment;
import com.do1.flowersapp.fragment.DynamicLoginFragment;
import com.do1.flowersapp.widget.PagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gufeng
 * Created on 2015/10/21 20:49
 * 功能作用:
 */
public class UserLoginActivity extends BaseActivity {

    @Bind(R.id.text_top_title)
    TextView textTopTitle;
    @Bind(R.id.tab_title)
    PagerSlidingTabStrip tabStrip;
    @Bind(R.id.pager_content)
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        textTopTitle.setText(getString(R.string.title_login));
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {

            private String[] title = {"普通登录", "动态密码登录"};

            @Override
            public Fragment getItem(int position) {
                Fragment fragment = null;
                if (position == 0) {
                    fragment = new DefaultLoginFragment();
                } else if (position == 1) {
                    fragment = new DynamicLoginFragment();
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
