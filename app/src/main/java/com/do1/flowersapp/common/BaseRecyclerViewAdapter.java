package com.do1.flowersapp.common;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by TuYong N1007
 * On 2015/9/11
 * At 15:08
 * RecyclerView支持Footer和Header
 */
public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int TYPE_HEADER = Integer.MIN_VALUE;
    public static final int TYPE_FOOTER = Integer.MIN_VALUE + 1;
    public static final int TYPE_ADAPTER_OFFSET = 2;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            return onCreateHeaderViewHolder(parent, viewType);
        } else if (viewType == TYPE_FOOTER) {
            return onCreateFooterViewHolder(parent, viewType);
        }
        return onCreateBasicItemViewHolder(parent, viewType - TYPE_ADAPTER_OFFSET);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0 && holder.getItemViewType() == TYPE_HEADER) {
            onBindHeaderView(holder, position);
        } else if (position == getBasicItemCount()+(useHeader() ? 1 : 0) && holder.getItemViewType() == TYPE_FOOTER) {
            onBindFooterView(holder, position);
        } else {
            onBindBasicItemView(holder, position - (useHeader() ? 1 : 0));
        }
    }

    @Override
    public int getItemCount() {
        int itemCount = getBasicItemCount();
        if (useHeader()) {
            itemCount += 1;
        }
        if (useFooter()) {
            itemCount += 1;
        }
        return itemCount;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 && useHeader()) {
            return TYPE_HEADER;
        }
        if (position == getBasicItemCount()+(useHeader() ? 1 : 0) && useFooter()) {
            return TYPE_FOOTER;
        }
        if (getBasicItemType(position) >= Integer.MAX_VALUE - TYPE_ADAPTER_OFFSET) {
            new IllegalStateException("HeaderRecyclerViewAdapter offsets your BasicItemType by " + TYPE_ADAPTER_OFFSET + ".");
        }
        return getBasicItemType(position) + TYPE_ADAPTER_OFFSET;
    }

    public abstract boolean useHeader();

    public abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindHeaderView(RecyclerView.ViewHolder holder, int position);

    public abstract boolean useFooter();

    public abstract RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindFooterView(RecyclerView.ViewHolder holder, int position);

    public abstract RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType);

    public abstract void onBindBasicItemView(RecyclerView.ViewHolder holder, int position);

    public abstract int getBasicItemCount();

    public abstract int getBasicItemType(int position);
}