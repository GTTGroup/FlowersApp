package com.do1.flowersapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.do1.flowersapp.R;
import com.do1.flowersapp.activity.NeedPayActivity;
import com.do1.flowersapp.activity.OrderActivity;
import com.do1.flowersapp.activity.PersonalActivity;
import com.do1.flowersapp.activity.SettingActivity;
import com.do1.flowersapp.activity.UserAddressManageActivity;
import com.do1.flowersapp.activity.UserCollectActivity;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.tools.UITools;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by gufeng
 * Created on 2015/10/14 00:44
 * 功能作用:
 */
public class PersonalFragment extends ModuleFragment implements View.OnClickListener {

    private LinearLayout llCollectCommodity;
    private LinearLayout llCollectShop;
    private LinearLayout llUserOrder;
    private RelativeLayout rlPayMoney;
    private ImageView btnSetting;
    private SimpleDraweeView draweeView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal,null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        draweeView = (SimpleDraweeView) view.findViewById(R.id.drawee_avator);
        draweeView.setOnClickListener(this);
        llCollectCommodity = (LinearLayout) view.findViewById(R.id.ll_collect_commodity);
        llCollectCommodity.setOnClickListener(this);
        llCollectShop = (LinearLayout) view.findViewById(R.id.ll_collect_shop);
        llCollectShop.setOnClickListener(this);
        llUserOrder = (LinearLayout) view.findViewById(R.id.ll_user_order);
        llUserOrder.setOnClickListener(this);
        btnSetting = (ImageView) view.findViewById(R.id.btn_setting);
        btnSetting.setOnClickListener(this);
        rlPayMoney = (RelativeLayout) view.findViewById(R.id.rl_pay_money);
        rlPayMoney.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == llCollectCommodity || v == llCollectShop) {
            UITools.intent(getActivity(), UserCollectActivity.class);
        } else if(v == llUserOrder) {
            UITools.intent(getActivity(), OrderActivity.class);
        } else if(v == btnSetting) {
            UITools.intent(getActivity(), SettingActivity.class);
        } else if(v == rlPayMoney) {
            UITools.intent(getActivity(), NeedPayActivity.class);
        } else if(v == draweeView) {
            UITools.intent(getActivity(), PersonalActivity.class);
        }
    }
}
