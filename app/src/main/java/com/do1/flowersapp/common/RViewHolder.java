package com.do1.flowersapp.common;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

/**
 * Created by boluo on 2015/10/24.
 */
public class RViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> holder;
    public int viewType;

    public RViewHolder(View itemView) {
        super(itemView);
        holder = new SparseArray<View>();
    }

    public static RViewHolder newInstance(ViewGroup viewGroup, int layoutId, int viewType) {
        RViewHolder holder = new RViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(layoutId, viewGroup, false));
        holder.viewType = viewType;
        return holder;
    }

    public static RViewHolder newInstance(ViewGroup viewGroup, int[] layoutIds, int viewType) {
        RViewHolder holder = new RViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(layoutIds[viewType], viewGroup, false));
        holder.viewType = viewType;
        return holder;
    }

    public RViewHolder setView(int id, View view) {
        holder.put(id, view);
        return this;
    }

    public <T extends View> T getView(int viewId) {
        return retrieveView(viewId);
    }

    @SuppressWarnings("unchecked")
    private <T extends View> T retrieveView(int viewId) {
        View view = holder.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            holder.put(viewId, view);
        }
        return (T) view;
    }

    public RViewHolder setText(int id, String value) {
        TextView view = retrieveView(id);
        view.setText(value);
        return this;
    }

    public RViewHolder setText(int id, int valueResId) {
        TextView view = retrieveView(id);
        view.setText(valueResId);
        return this;
    }

    public RViewHolder setImageResource(int viewId, int imageResId) {
        ImageView view = retrieveView(viewId);
        view.setImageResource(imageResId);
        return this;
    }

    public RViewHolder setBackgroundColor(int viewId, int color) {
        View view = retrieveView(viewId);
        view.setBackgroundColor(color);
        return this;
    }

    public RViewHolder setBackgroundRes(int viewId, int backgroundRes) {
        View view = retrieveView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public RViewHolder setBackgroundDrawable(int viewId, Drawable drawable) {
        View view = retrieveView(viewId);
        view.setBackgroundDrawable(drawable);
        return this;
    }

    public RViewHolder setTextColor(int viewId, int textColor) {
        TextView view = retrieveView(viewId);
        view.setTextColor(textColor);
        return this;
    }

    public RViewHolder setTextColorRes(int viewId, int textColorRes) {
        TextView view = retrieveView(viewId);
        view.setTextColor(textColorRes);
        return this;
    }

    public RViewHolder setImageDrawable(int viewId, Drawable drawable) {
        ImageView view = retrieveView(viewId);
        view.setImageDrawable(drawable);
        return this;
    }

    public RViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = retrieveView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public RViewHolder setVisible(int viewId, boolean visible) {
        View view = retrieveView(viewId);
        view.setVisibility(visible ? View.VISIBLE : View.GONE);
        return this;
    }

    public RViewHolder setProgress(int viewId, int progress) {
        ProgressBar view = retrieveView(viewId);
        view.setProgress(progress);
        return this;
    }

    public RViewHolder setProgress(int viewId, int progress, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        view.setProgress(progress);
        return this;
    }

    public RViewHolder setMax(int viewId, int max) {
        ProgressBar view = retrieveView(viewId);
        view.setMax(max);
        return this;
    }
}
