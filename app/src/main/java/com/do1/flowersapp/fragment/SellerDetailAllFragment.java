package com.do1.flowersapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.widget.PagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TuYong N1007
 * On 2015/10/22
 * At 20:11
 * 所有商品类别 有tab类别区分
 */
public class SellerDetailAllFragment extends ModuleFragment {

    @Bind(R.id.pager_slide)
    PagerSlidingTabStrip mPagerSlide;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;

    private String[] mTabNames;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_detail_all,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mTabNames = new String[]{"全部","销量","价格"};

        mViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return new SellerDetailSingleFragment();
            }

            @Override
            public int getCount() {
                return mTabNames.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return mTabNames[position];
            }
        });
        mPagerSlide.setViewPager(mViewPager);
    }
}
