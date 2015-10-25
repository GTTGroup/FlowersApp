package com.do1.flowersapp.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.business.http.CommonResp;
import com.do1.flowersapp.business.http.ServerApiClient;
import com.do1.flowersapp.business.http.ServerApiClientCallback;
import com.do1.flowersapp.config.UserConfig;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.tools.UITools;
import com.do1.flowersapp.widget.NiftyProgressDialogBuilder;
import com.google.gson.JsonElement;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cz.msebera.android.httpclient.Header;

/**
 * Created by gufeng
 * Created on 2015/10/21 17:30
 * 功能作用: 新增用户收货地址
 */
public class UserAddAddressActivity extends BaseActivity {

    @Bind(R.id.input_user_name)
    EditText inputUserName;
    @Bind(R.id.input_mobile)
    EditText inputUserMobile;
    @Bind(R.id.input_provinces)
    EditText inputProvince;
    @Bind(R.id.input_street)
    EditText inputStreet;
    @Bind(R.id.input_postcode)
    EditText inputPostCode;

    @Bind(R.id.text_default)
    TextView textDefault;
    @Bind(R.id.text_not_default)
    TextView textNotDefault;

    private String isDefault = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user_address);
        setTextTitle(getString(R.string.title_add_user_address));
        ButterKnife.bind(this);
        textDefault.setSelected(true);
    }

    @OnClick(R.id.text_default) void selectedDefault() {
        textDefault.setSelected(true);
        textNotDefault.setSelected(false);
        isDefault = "1";
    }

    @OnClick(R.id.text_not_default) void setNotDefault() {
        textDefault.setSelected(false);
        textNotDefault.setSelected(true);
        isDefault = "0";
    }

    @OnClick(R.id.btn_comfirm) void addUserAddress() {

        String userName = inputUserName.getText().toString();
        String userMobile = inputUserMobile.getText().toString();
        String province = inputProvince.getText().toString();
        String street = inputStreet.getText().toString();
        String postCode = inputPostCode.getText().toString();

        final NiftyProgressDialogBuilder progressDialogBuilder = new NiftyProgressDialogBuilder(this);
        UITools.createNiftyProgressDialog(progressDialogBuilder, null, false);
        ServerApiClient.getInstance().addUserAddress(this, getClass().getName(), UserConfig.getUserMemberId(this), userName, userMobile, postCode, province, street, isDefault, new ServerApiClientCallback() {
            @Override
            public void onSuccess(CommonResp resp) {
                progressDialogBuilder.dismiss();
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
}
