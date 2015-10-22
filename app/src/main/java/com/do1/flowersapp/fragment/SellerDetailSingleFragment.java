package com.do1.flowersapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.tools.ViewHolder;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by TuYong N1007
 * On 2015/10/22
 * At 20:28
 * 单个列表 商家商品信息，条件不同显示的内容不同
 */
public class SellerDetailSingleFragment extends ModuleFragment{

    @Bind(R.id.grid_view)
    GridView mGridView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seller_detail_single,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGridView.setAdapter(new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int position) {
                return position;
            }

            @Override
            public long getItemId(int position) {
                return position;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder = ViewHolder.newInstance(getActivity(),convertView,parent,R.layout.layout_seller_detail_single_item);
                return holder.getView();
            }
        });
    }
}
