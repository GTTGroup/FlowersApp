package com.do1.flowersapp.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.widget.PagerSlidingTabStrip;

/**
 * Created by gufeng
 * Created on 2015/10/19 23:43
 * 功能作用:花艺师详情界面
 */
public class FloristsInformationActivity extends BaseActivity {

    private PagerSlidingTabStrip tabStrip;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_florists_info);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tab_title);
        viewPager = (ViewPager) findViewById(R.id.pager_content);
//        viewPager.setAdapter(new PagerAdapter() {
//
//            private String[] title = {"TA的作品","个人介绍","联系客服"};
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                return title[position];
//            }
//
//            @Override
//            public boolean isViewFromObject(View view, Object object) {
//                return view == object;
//            }
//
//            @Override
//            public void destroyItem(ViewGroup container, int position, Object object) {
//                container.removeView((SimpleDraweeView) object);
//            }
//
//            @Override
//            public int getCount() {
//                return 3;
//            }
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position) {
//                if (position == 0)
//                SimpleDraweeView imageView = new SimpleDraweeView(getActivity());
//                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//                String logo = logoList.get(position);
//                imageView.setImageURI(Uri.parse(logo));
//                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);
//                container.addView(imageView, params);
//                return imageView;
//            }
//        });
//        tabStrip.setViewPager(viewPager);
    }
}
