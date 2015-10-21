package com.do1.flowersapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.fragment.SellerDetailHomeFragment;
import com.do1.flowersapp.widget.PagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bruce Too
 * On 10/21/15.
 * At 21:09
 */
public class SellerDetailActivity extends BaseActivity {

    @Bind(R.id.pager_slide)
    PagerSlidingTabStrip mPagerSlide;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private String[] mTabNames;
    private int[] mTabIcons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_seller_details);
        ButterKnife.bind(this);

        mTabNames = new String[]{"商家首页","全部商品","新品上架"};
        mTabIcons = new int[]{R.drawable.icon_home,R.drawable.icon_all,R.drawable.icon_new};

        mViewPager.setAdapter(new SellerPagerAdapter(getSupportFragmentManager()));
        mPagerSlide.setViewPager(mViewPager);
    }

    class SellerPagerAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.IconAndTabProvider{

        public SellerPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position){
                case 0:
                    fragment = new SellerDetailHomeFragment();
                    break;
                case 1:
                    fragment = new SellerDetailHomeFragment();
                    break;
                case 2:
                    fragment = new SellerDetailHomeFragment();
                    break;
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return mTabNames.length;
        }

        @Override
        public int getPageIconResId(int position) {
            return mTabIcons[position];
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTabNames[position];
        }
    }
}
