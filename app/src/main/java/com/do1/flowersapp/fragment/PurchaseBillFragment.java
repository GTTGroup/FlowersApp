package com.do1.flowersapp.fragment;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.do1.flowersapp.R;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.business.model.UserOrder;
import com.do1.flowersapp.tools.ViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gufeng
 * Created on 2015/10/15 00:43
 * 功能作用: 首页--进货单
 */
public class PurchaseBillFragment extends ModuleFragment {

    private OrderAdapter mAdapter;
    private SwipeMenuListView swipeMenuListView;

    private List<UserOrder> orderList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        orderList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_purchase_bill,null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        swipeMenuListView = (SwipeMenuListView) view.findViewById(R.id.list_content);
        swipeMenuListView.addHeaderView(LayoutInflater.from(getActivity()).inflate(R.layout.common_item_view,null));
        swipeMenuListView.addFooterView(LayoutInflater.from(getActivity()).inflate(R.layout.common_item_view,null));
        createAdapter();
    }

    public void createAdapter() {
        mAdapter = new OrderAdapter(getActivity());
        swipeMenuListView.setAdapter(mAdapter);
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem openItem = new SwipeMenuItem(getActivity());
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

    class OrderAdapter extends BaseAdapter {

        private Context context;
        private List<UserOrder> adapterData;

        public OrderAdapter(Context ctx) {
            this.context = ctx;
            this.adapterData = new ArrayList<>();
        }

        @Override
        public int getCount() {
            return 10;
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

            ViewHolder viewHolder = ViewHolder.newInstance(context, convertView, parent, R.layout.list_item_order);

            return viewHolder.convertView;
        }

        public void setData(List list) {
            this.adapterData.clear();
            this.adapterData.addAll(list);
            notifyDataSetChanged();
        }
    }
}
