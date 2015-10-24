package com.do1.flowersapp.common;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by boluo on 2015/10/24.
 */
public abstract class RVAapter<M> extends RecyclerView.Adapter<RViewHolder> {
    private List<M> items = new ArrayList<M>();
    private int[] layoutIds;

    public RVAapter(List<M> items, int layoutIds) {
        this.items = items;
        this.layoutIds = new int[]{layoutIds};
    }

    public RVAapter(List<M> items, int[] layoutIds) {
        this.items = items;
        this.layoutIds = layoutIds;
    }

    public List<M> getData() {
        return items;
    }

    public void setData(List<M> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public M getItem(int index) {
        return items.get(index);
    }

    @Override
    public RViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return RViewHolder.newInstance(parent, layoutIds, viewType);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
