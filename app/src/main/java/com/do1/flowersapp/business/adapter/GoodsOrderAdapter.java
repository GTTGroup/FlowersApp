package com.do1.flowersapp.business.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.activity.GoodsOrderActivity;
import com.do1.flowersapp.business.listener.GoodsOrderListener;
import com.do1.flowersapp.business.model.GoodsOrderItem;
import com.do1.flowersapp.common.BaseRecyclerViewAdapter;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bruce Too
 * On 10/24/15.
 * At 13:19
 * 一键下单adapter
 */
public class GoodsOrderAdapter extends BaseRecyclerViewAdapter {

    private Context mContext;
    private GoodsOrderListener mListener;
    private List<GoodsOrderItem> datas;
    private GoodsOrderActivity.HomeUpdateListener homeUpdateListener;

    public GoodsOrderAdapter(Context context, GoodsOrderListener listener, List<GoodsOrderItem> datas,
                             GoodsOrderActivity.HomeUpdateListener homeUpdateListener) {
        this.mContext = context;
        this.mListener = listener;
        this.datas = datas;
        this.homeUpdateListener = homeUpdateListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_goods_order_header, parent, false);
        return new HeaderViewHolder(view);
    }

    @Override
    public void onBindHeaderView(RecyclerView.ViewHolder holder, int position) {
        final HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
        viewHolder.bottomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onChooseLocation(viewHolder.textName,viewHolder.textPhone,viewHolder.textAddress);
            }
        });

        viewHolder.centerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onChooseEndTime(viewHolder.textEndTime);
            }
        });

        viewHolder.bottomLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onChoosePayWay(viewHolder.textPayWay);
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_goods_order_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindBasicItemView(RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        viewHolder.recyclerView.setAdapter(new GoodsOrderItemAdapter(mContext, mListener,
                datas.get(position),viewHolder.recyclerView,homeUpdateListener,position));
        viewHolder.recyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                (int) (mContext.getResources().getDimension(R.dimen.goods_order_item_height)+ datas.get(position).flowers.size()
                        * mContext.getResources().getDimension(R.dimen.goods_order_normal_height))));
    }

    @Override
    public int getBasicItemCount() {
        return datas.size();
    }

    @Override
    public int getBasicItemType(int position) {
        return 0;
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.layout_top)
        View topLayout;
        @Bind(R.id.text_name)
        TextView textName;
        @Bind(R.id.text_phone)
        TextView textPhone;
        @Bind(R.id.text_address)
        TextView textAddress;
        @Bind(R.id.layout_center)
        View centerLayout;
        @Bind(R.id.text_end_time)
        TextView textEndTime;
        @Bind(R.id.layout_bottom)
        View bottomLayout;
        @Bind(R.id.text_pay_way)
        TextView textPayWay;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.recycler_view)
        RecyclerView recyclerView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public boolean useHeader() {
        return true;
    }

    @Override
    public boolean useFooter() {
        return false;
    }

    @Override
    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindFooterView(RecyclerView.ViewHolder holder, int position) {

    }
}
