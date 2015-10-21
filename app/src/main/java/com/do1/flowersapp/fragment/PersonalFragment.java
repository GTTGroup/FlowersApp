package com.do1.flowersapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.do1.flowersapp.R;
import com.do1.flowersapp.activity.OrderActivity;
import com.do1.flowersapp.activity.UserAddressManageActivity;
import com.do1.flowersapp.activity.UserCollectActivity;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.tools.UITools;

/**
 * Created by gufeng
 * Created on 2015/10/14 00:44
 * 功能作用:
 */
public class PersonalFragment extends ModuleFragment implements View.OnClickListener {

    private LinearLayout llCollectCommodity;
    private LinearLayout llCollectShop;
    private LinearLayout llUserOrder;
    private ImageView btnSetting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal,null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        llCollectCommodity = (LinearLayout) view.findViewById(R.id.ll_collect_commodity);
        llCollectCommodity.setOnClickListener(this);
        llCollectShop = (LinearLayout) view.findViewById(R.id.ll_collect_shop);
        llCollectShop.setOnClickListener(this);
        llUserOrder = (LinearLayout) view.findViewById(R.id.ll_user_order);
        llUserOrder.setOnClickListener(this);
        btnSetting = (ImageView) view.findViewById(R.id.btn_setting);
        btnSetting.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == llCollectCommodity || v == llCollectShop) {
            UITools.intent(getActivity(), UserCollectActivity.class);
        } else if(v == llUserOrder) {
            UITools.intent(getActivity(), OrderActivity.class);
        } else if(v == btnSetting) {
            UITools.intent(getActivity(), UserAddressManageActivity.class);
        }
    }
}
