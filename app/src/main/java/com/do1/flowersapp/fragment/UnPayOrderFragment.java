package com.do1.flowersapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.do1.flowersapp.R;
import com.do1.flowersapp.context.ModuleFragment;

/**
 * Created by gufeng
 * Created on 2015/10/18 20:04
 * 功能作用: 待用户处理订单
 */
public class UnPayOrderFragment extends ModuleFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_unpay_order,null);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
