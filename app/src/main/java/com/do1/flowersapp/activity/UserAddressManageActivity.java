package com.do1.flowersapp.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.do1.flowersapp.R;
import com.do1.flowersapp.business.model.UserAddress;
import com.do1.flowersapp.business.model.UserOrder;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.tools.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gufeng
 * Created on 2015/10/21 02:01
 * 功能作用: 用户收货地址管理
 */
public class UserAddressManageActivity extends BaseActivity {

    private TextView btnAddUserAddress;
    private SwipeMenuListView swipeMenuListView;
    private AddressAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_address_manage);
        setTextTitle(getString(R.string.title_user_address_manage));
        btnAddUserAddress = (TextView) findViewById(R.id.btn_add_user_address);
        swipeMenuListView = (SwipeMenuListView) findViewById(R.id.list_content);
        createAdapter();
        initData();
    }

    private void createAdapter() {
        mAdapter = new AddressAdapter(this);
        swipeMenuListView.setAdapter(mAdapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(UserAddressManageActivity.this);
                openItem.setBackground(R.color.FF5001);
                openItem.setWidth(getResources().getDimensionPixelSize(R.dimen.item_swipe_width));
                openItem.setTitle("删除");
                openItem.setTitleSize(14);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);
            }
        };
        swipeMenuListView.setMenuCreator(creator);
        swipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu swipeMenu, int index) {
                return false;
            }
        });
    }

    private void initData() {

        List<UserAddress> list = new ArrayList<>();

        UserAddress userAddress = new UserAddress();
        userAddress.receveUserName = "张三";
        userAddress.receveUserAddress = "中国广东省广州市天河区科韵路建中路66号佳都商务大厦西塔9楼";
        userAddress.defaultState = true;
        list.add(userAddress);

        UserAddress userAddress1 = new UserAddress();
        userAddress1.receveUserName = "ISI";
        userAddress1.receveUserAddress = "中国广东省广州市天河区";
        userAddress1.defaultState = false;
        list.add(userAddress1);

        mAdapter.setData(list);
    }

    class AddressAdapter extends BaseAdapter {

        private Context context;
        private List<UserAddress> adapterData;

        public AddressAdapter(Context ctx) {
            this.context = ctx;
            this.adapterData = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return adapterData.size();
        }

        @Override
        public Object getItem(int position) {
            return this.adapterData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            UserAddress userAddress = adapterData.get(position);
            ViewHolder viewHolder = ViewHolder.newInstance(context, convertView, parent, R.layout.list_item_user_address);
            viewHolder.setText(R.id.text_user_name,userAddress.receveUserName);
            if(userAddress.defaultState) {
                String defaultAddress = "【默认】  "+userAddress.receveUserAddress;
                SpannableString spannableString = new SpannableString(defaultAddress);
                spannableString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.FCA724)),0,6, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                viewHolder.setText(R.id.text_user_address,spannableString);
            } else {
                viewHolder.setText(R.id.text_user_address,userAddress.receveUserAddress);
            }
            return viewHolder.convertView;
        }

        public void setData(List list) {
            this.adapterData.clear();
            this.adapterData.addAll(list);
            notifyDataSetChanged();
        }
    }
}
