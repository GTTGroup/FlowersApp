package com.do1.flowersapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.activity.MainActivity;
import com.do1.flowersapp.activity.UserForgetPasswordActivity;
import com.do1.flowersapp.activity.UserRegisterActivity;
import com.do1.flowersapp.business.http.CommonResp;
import com.do1.flowersapp.business.http.ServerApiClient;
import com.do1.flowersapp.business.http.ServerApiClientCallback;
import com.do1.flowersapp.business.model.UserInfo;
import com.do1.flowersapp.context.ModuleFragment;
import com.do1.flowersapp.tools.UITools;
import com.do1.flowersapp.widget.NiftyProgressDialogBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by gufeng
 * Created on 2015/10/25 23:29
 * 功能作用: 普通密码登录
 */
public class DefaultLoginFragment extends ModuleFragment {

    @Bind(R.id.input_user_account)
    EditText inputUserAccount;
    @Bind(R.id.input_user_password)
    EditText inputUserPassword;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_default_login, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btn_login) void login() {
        String userAccount = inputUserAccount.getText().toString();
        String userPassword = inputUserPassword.getText().toString();
        final NiftyProgressDialogBuilder progressDialogBuilder = new NiftyProgressDialogBuilder(getActivity());
        UITools.createNiftyProgressDialog(progressDialogBuilder,null,false);
        ServerApiClient.getInstance().login(getActivity(), getClass().getName(), userAccount, userPassword, new ServerApiClientCallback() {
            @Override
            public void onSuccess(CommonResp resp) {
                UserInfo userInfo = new Gson().fromJson(resp.getData(),UserInfo.class);
                progressDialogBuilder.dismiss();
                UITools.intent(getActivity(), MainActivity.class);
                getActivity().finish();
            }

            @Override
            public void onFail(String serverRespCode, String severRespFail, JsonElement responseString) {
                progressDialogBuilder.dismiss();
            }

            @Override
            public void onError(int statCode, Header[] headers, String responseString) {
                progressDialogBuilder.dismiss();
            }
        });
    }

    @OnClick(R.id.text_register) void register() {
        UITools.intent(getActivity(), UserRegisterActivity.class);
    }

    @OnClick(R.id.text_forget_password) void forgetPassword() {
        UITools.intent(getActivity(), UserForgetPasswordActivity.class);
    }
}
