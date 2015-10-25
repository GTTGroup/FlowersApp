package com.do1.flowersapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.activity.MainActivity;
import com.do1.flowersapp.activity.NeedPayActivity;
import com.do1.flowersapp.activity.OrderActivity;
import com.do1.flowersapp.activity.UserPersonalActivity;
import com.do1.flowersapp.activity.SettingActivity;
import com.do1.flowersapp.activity.UserAddressManageActivity;
import com.do1.flowersapp.activity.UserCollectActivity;
import com.do1.flowersapp.config.UserConfig;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.tools.UITools;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by gufeng
 * Created on 2015/10/14 00:44
 * 功能作用:
 */
public class PersonalFragment extends ModuleFragment implements View.OnClickListener {

    @Bind(R.id.ll_collect_commodity)
    LinearLayout llCollectCommodity;
    @Bind(R.id.ll_collect_shop)
    LinearLayout llCollectShop;
    @Bind(R.id.ll_user_order)
    LinearLayout llUserOrder;
    @Bind(R.id.rl_pay_money)
    RelativeLayout rlPayMoney;
    @Bind(R.id.rl_purchase_bill)
    RelativeLayout rlPurchaseBill;
    @Bind(R.id.btn_setting)
    ImageView btnSetting;
    @Bind(R.id.drawee_avator)
    SimpleDraweeView draweeView;
    @Bind(R.id.text_user_name)
    TextView textUserName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        draweeView.setOnClickListener(this);
        llCollectCommodity.setOnClickListener(this);
        llCollectShop.setOnClickListener(this);
        llUserOrder.setOnClickListener(this);
        btnSetting.setOnClickListener(this);
        rlPayMoney.setOnClickListener(this);
        rlPurchaseBill.setOnClickListener(this);
        textUserName.setText(UserConfig.getUserNickName(getActivity()));
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
            UITools.intent(getActivity(), UserPersonalActivity.class);
        } else if(v == rlPurchaseBill) {
            ((MainActivity)getActivity()).instantiateFragment(2);
        }
    }
}
