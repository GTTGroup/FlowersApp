package com.do1.flowersapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.do1.flowersapp.R;
import com.do1.flowersapp.business.http.CommonResp;
import com.do1.flowersapp.business.http.ServerApiClient;
import com.do1.flowersapp.business.http.ServerApiClientCallback;
import com.do1.flowersapp.context.ModuleFragment;
import com.google.gson.JsonElement;

import cz.msebera.android.httpclient.Header;

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
        ServerApiClient.getInstance().getOrderList(getActivity(), getClass().getName(), "c01", "10", "1", new ServerApiClientCallback() {
            @Override
            public void onSuccess(CommonResp resp) {

            }

            @Override
            public void onFail(String serverRespCode, String severRespFail, JsonElement responseString) {

            }

            @Override
            public void onError(int statCode, Header[] headers, String responseString) {

            }
        });
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
