package com.do1.flowersapp.business.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.swipe.SwipeLayout;
import com.do1.flowersapp.R;
import com.do1.flowersapp.business.listener.OrderHomeUpdateListener;
import com.do1.flowersapp.business.model.GoodsOrderItem;
import com.do1.flowersapp.common.BaseRecyclerViewAdapter;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Bruce Too
 * On 10/24/15.
 * At 14:55
 * 单个Item RecyclerView的adapter
 */
public class GoodsOrderItemAdapter extends BaseRecyclerViewAdapter {
    private Context mContext;
    private GoodsOrderItem data;
    private RecyclerView recyclerView;
    private OrderHomeUpdateListener orderHomeUpdateListener;
    private boolean hasHeader;
    private int postionOuter;// 第几个item

    public GoodsOrderItemAdapter(Context context,
                                 GoodsOrderItem data, RecyclerView recyclerView,
                                 OrderHomeUpdateListener orderHomeUpdateListener, boolean hasHeader, int position) {
        this.mContext = context;
        this.data = data;
        this.recyclerView = recyclerView;
        this.orderHomeUpdateListener = orderHomeUpdateListener;
        this.hasHeader = hasHeader;
        this.postionOuter = position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_goods_order_item_header, parent, false);
        return new HeaderViewHolder(headerView);
    }

    @Override
    public void onBindHeaderView(RecyclerView.ViewHolder holder, int position) {
        HeaderViewHolder viewHolder = (HeaderViewHolder) holder;
        if(data.isStore){
            viewHolder.storeName.setText(data.storeName);
        }else {
            viewHolder.storeName.setText(data.floristsName);
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderHomeUpdateListener.onEnterStore("s01");
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType) {
        View footerView = LayoutInflater.from(mContext).inflate(R.layout.layout_goods_order_item_footer, parent, false);
        return new FooterViewHolder(footerView);
    }

    @Override
    public void onBindFooterView(RecyclerView.ViewHolder holder, int position) {
        FooterViewHolder viewHolder = (FooterViewHolder) holder;
        String str = data.shipment == 0 ? "免邮" : ("￥"+data.shipment);
        viewHolder.sendMoney.setText(str);
        int totalCount = 0;
        int totalMoney = 0;
        for(int i = 0; i < data.flowers.size(); i++){
            GoodsOrderItem.GoodsOrderSingle single = data.flowers.get(i);
            totalCount += single.counter;
            totalMoney += single.counter * single.flowerDetail2;
        }
        viewHolder.totalCount.setText("共"+totalCount+"个商品");
        viewHolder.totalMoney.setText("￥"+totalMoney);
    }

    @Override
    public RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_goods_order_item_normal, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindBasicItemView(RecyclerView.ViewHolder holder, final int position) {
        Log.e("onBindBasicItemView", "" + data.flowers.size());
        final ItemViewHolder viewHolder = (ItemViewHolder) holder;
        final GoodsOrderItem.GoodsOrderSingle single = data.flowers.get(position);
        if(data.isStore) {//商品
            viewHolder.flowerDetail1.setText(single.flowerDetail1 + "/扎");
            viewHolder.flowerDetail2.setText("￥" + single.flowerDetail2 + "/扎");
        }else { //花艺师
            viewHolder.flowerDetail1.setText(single.flowerDetail4);
            viewHolder.flowerDetail2.setText("￥" + single.flowerDetail2 + "/人天");
        }
        viewHolder.flowerDetail3.setText(single.flowerDetail3);
        viewHolder.flowerImg.setImageURI(Uri.parse(single.flowerUrl));
        viewHolder.flowerName.setText(single.flowerName);
        viewHolder.counter.setText(String.valueOf(single.counter));
        viewHolder.tips.setText("备注:" + single.tips);

        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        viewHolder.swipeLayout.addDrag(SwipeLayout.DragEdge.Left, viewHolder.layoutBottom);
        viewHolder.swipeLayout.setLeftSwipeEnabled(false);
        //点击删除
        viewHolder.layoutBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.flowers.remove(position);
                //通知该界面刷新
                updateLayout();
//                notifyItemRemoved(position + 1);
                updateFooterView();
                //通知外层界面刷新
                notifyHomeUpdate(postionOuter);
                Log.e("onHomeUpdate:", "移除店铺'" + data.storeName + "'中的" + single.counter + "朵 <" + single.flowerName + ">花");
            }
        });

        viewHolder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                single.counter += 1;
                notifyItemChanged(position+1);
                updateFooterView();
                //通知外层界面刷新
                notifyHomeUpdate(postionOuter);
            }
        });

        viewHolder.btnSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(single.counter > 0) {
                    single.counter -= 1;
                    //刷新当前布局 +1 是header占据的位置
                    notifyItemChanged(position+1);
                    updateFooterView();
                    //通知外层界面刷新
                    notifyHomeUpdate(postionOuter);
                }
            }
        });

    }

    @Override
    public int getBasicItemCount() {
        return data.flowers.size();
    }

    @Override
    public int getBasicItemType(int position) {
        return 0;
    }

    @Override
    public boolean useHeader() {
        return true;
    }

    @Override
    public boolean useFooter() {
        return data.isStore;
    }

    /**
     * 更新最外层的数据
     * @param position
     */
    private void notifyHomeUpdate(int position) {
        orderHomeUpdateListener.onHomeUpdate(position, data);
    }

    /**
     * 更新footerview
     */
    private void updateFooterView(){
        notifyItemChanged(getBasicItemCount()+1);
    }

    /**
     * 强制刷新布局
     */
    private void updateLayout(){
        if(hasHeader) {
            recyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) (mContext.getResources().getDimension(R.dimen.goods_order_item_header)+
                            mContext.getResources().getDimension(R.dimen.goods_order_item_footer)+ data.flowers.size()
                            * mContext.getResources().getDimension(R.dimen.goods_order_normal_height))));
        }else {
            recyclerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    (int) (mContext.getResources().getDimension(R.dimen.goods_order_item_header) + data.flowers.size()
                            * mContext.getResources().getDimension(R.dimen.goods_order_normal_height))));
        }
        recyclerView.requestLayout();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_store_name)
        TextView storeName;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.text_send_money)
        TextView sendMoney;
        @Bind(R.id.text_money)
        TextView totalMoney;
        @Bind(R.id.text_total_count)
        TextView totalCount;

        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.swipe_layout)
        SwipeLayout swipeLayout;
        @Bind(R.id.bottom_layout)
        View layoutBottom;
        @Bind(R.id.drawee_cover)
        SimpleDraweeView flowerImg;
        @Bind(R.id.text_name)
        TextView flowerName;
        @Bind(R.id.text_detail1)
        TextView flowerDetail1; // 20/扎
        @Bind(R.id.text_detail2)
        TextView flowerDetail2; // ￥25/扎
        @Bind(R.id.text_detail3)
        TextView flowerDetail3; // 红色 单头 A级
        @Bind(R.id.text_tips)
        TextView tips;
        @Bind(R.id.image_sub)
        ImageView btnSub;
        @Bind(R.id.image_add)
        ImageView btnAdd;
        @Bind(R.id.text_count)
        TextView counter;

        public ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
