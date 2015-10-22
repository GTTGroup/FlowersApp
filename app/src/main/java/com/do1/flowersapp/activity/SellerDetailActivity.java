package com.do1.flowersapp.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.Toast;

import com.do1.flowersapp.R;
import com.do1.flowersapp.business.http.CommonResp;
import com.do1.flowersapp.business.http.ServerApiClient;
import com.do1.flowersapp.business.http.ServerApiClientCallback;
import com.do1.flowersapp.business.model.SellerDetail;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.fragment.SellerDetailHomeFragment;
import com.do1.flowersapp.fragment.SellerDetailSingleFragment;
import com.do1.flowersapp.tools.ViewHolder;
import com.do1.flowersapp.widget.PagerSlidingTabStrip;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by Bruce Too
 * On 10/21/15.
 * At 21:09
 *  商家详情,店铺详情
 */
public class SellerDetailActivity extends BaseActivity {

    @Bind(R.id.pager_slide)
    PagerSlidingTabStrip mPagerSlide;
    @Bind(R.id.view_pager)
    ViewPager mViewPager;
    @Bind(R.id.edit_search_flower)
    EditText mEditSearch;
    @Bind(R.id.img_message)
    ImageView mShowMessage;

    private String[] mTabNames;
    private int[] mTabIcons;
    private int[] mMessageIcons;
    private String[] mMessageDescs;
    private ListPopupWindow mPopupWindow;
    private SellerDetail mBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_seller_details);
        ButterKnife.bind(this);

        mTabNames = new String[]{"商家首页","全部商品","新品上架"};
        mTabIcons = new int[]{R.drawable.icon_home,R.drawable.icon_all,R.drawable.icon_new};
        mMessageDescs = getResources().getStringArray(R.array.pop_msg);
        mMessageIcons = new int[]{R.drawable.icon_message,R.drawable.icon_home_page,R.drawable.icon_keep,
        R.drawable.icon_share,R.drawable.icon_store,R.drawable.icon_customer_service};

        mViewPager.setAdapter(new SellerPagerAdapter(getSupportFragmentManager()));
        mPagerSlide.setViewPager(mViewPager);

        ServerApiClient.getInstance().getSellerDetailMessage(this, SellerDetailActivity.class.toString(),
                "s01", new ServerApiClientCallback() {
                    @Override
                    public void onSuccess(CommonResp resp) {
                        mBean = new Gson().fromJson(resp.getData(), SellerDetail.class);
                    }

                    @Override
                    public void onFail(String serverRespCode, String severRespFail, JsonElement responseString) {

                    }

                    @Override
                    public void onError(int statCode, Header[] headers, String responseString) {

                    }
                });

        ServerApiClient.getInstance().getSellerDetailList(this, SellerDetailActivity.class.toString(),
                "s01", "10", "1", "2", "红", new ServerApiClientCallback() {
                    @Override
                    public void onSuccess(CommonResp resp) {

                    }

                    @Override
                    public void onFail(String serverRespCode, String severRespFail, JsonElement responseString) {

                    }

                    @Override
                    public void onError(int statCode, Header[] headers, String responseString) {

                    }
                });
    }

    //header click
    @OnClick(R.id.btn_top_back)
    public void topBackClick(){
        finish();
    }

    @OnClick(R.id.img_search)
    public void searchClick(){

    }

    //footer click
    @OnClick({R.id.text_category_foods, R.id.text_seller_intro, R.id.text_contact_seller})
    public void onFooterClick(){
        Toast.makeText(this, "哦哦哦哦哦哦哦", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.img_message)
    public void messageClick(){
        if(null == mPopupWindow) {
            mPopupWindow = new ListPopupWindow(this);
            mPopupWindow.setAdapter(new BaseAdapter() {
                @Override
                public int getCount() {
                    return mMessageDescs.length;
                }

                @Override
                public Object getItem(int position) {
                    return mMessageDescs[position];
                }

                @Override
                public long getItemId(int position) {
                    return position;
                }

                @Override
                public View getView(int position, View convertView, ViewGroup parent) {
                    ViewHolder holder = ViewHolder.newInstance(parent.getContext(), convertView, parent, R.layout.layout_message_item);
                    holder.setText(R.id.text_desc, mMessageDescs[position]);
                    holder.setImageResource(R.id.image_icon, mMessageIcons[position]);
                    return holder.getView();
                }
            });
            mPopupWindow.setWidth((int) getResources().getDimension(R.dimen.message_pop_window_width));
            mPopupWindow.setHeight(ListPopupWindow.WRAP_CONTENT);
            mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_msg_bg));
            mPopupWindow.setModal(false);
            mPopupWindow.setAnchorView(mShowMessage);
            mPopupWindow.setVerticalOffset((int) getResources().getDimension(R.dimen.top_height) - mShowMessage.getBottom());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                mPopupWindow.setDropDownGravity(Gravity.END);
            }
            mPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(SellerDetailActivity.this, "点击了第"+position+"个item", Toast.LENGTH_SHORT).show();
                }
            });
            mPopupWindow.show();
        }else {
            if(!mPopupWindow.isShowing()){
                mPopupWindow.show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(null != mPopupWindow){
            if (mPopupWindow.isShowing()){
                mPopupWindow.dismiss();
                mPopupWindow = null;
            }else {
                super.onBackPressed();
            }
        }else {
            super.onBackPressed();
        }
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
                    fragment = new SellerDetailSingleFragment();
                    break;
                case 2:
                    fragment = new SellerDetailSingleFragment();
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
