package com.do1.flowersapp.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.EditText;
import android.widget.Toast;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.fragment.SellerDetailSingleFragment;
import com.do1.flowersapp.widget.PagerSlidingTabStrip;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by TuYong N1007
 * On 2015/10/22
 * At 20:11
 * 所有商品类别 有tab类别区分
 */
public class SellerDetailAllActivity extends BaseActivity {

    @Bind(R.id.pager_slide)
    PagerSlidingTabStrip mPagerSlide;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.edit_search_flower)
    EditText mEditSearch;

    private String[] mTabNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_seller_detail_all);
        ButterKnife.bind(this);

        mTabNames = new String[]{"全部","销量","价格"};

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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

    /**
     * 搜索按钮点击
     */
    @OnClick(R.id.img_search)
    public void searchClick(){

    }


    @OnClick(R.id.btn_top_back)
    public void topBackClick(){
        finish();
    }

    @OnClick(R.id.img_message)
    public void messageClick(){
        Toast.makeText(this,"点击消息",Toast.LENGTH_SHORT).show();
    }
}
