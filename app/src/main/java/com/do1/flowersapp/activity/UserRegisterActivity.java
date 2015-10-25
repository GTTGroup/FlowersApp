package com.do1.flowersapp.activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.business.http.CommonResp;
import com.do1.flowersapp.business.http.ServerApiClient;
import com.do1.flowersapp.business.http.ServerApiClientCallback;
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
 * Created on 2015/10/21 18:30
 * 功能作用: 用户注册
 */
public class UserRegisterActivity extends BaseActivity {

    @Bind(R.id.input_user_account)
    EditText inputUserAccount;
    @Bind(R.id.input_user_password)
    EditText inputUserPassword;
    @Bind(R.id.input_user_comfirm_password)
    EditText inputUserComfirmPassword;
    @Bind(R.id.input_user_nick)
    EditText inputUserNick;
    @Bind(R.id.input_user_mobile)
    EditText inputUserMobile;
    @Bind(R.id.input_user_address)
    EditText inputUserAddress;

    @Bind(R.id.rb_user)
    TextView textUser;
    @Bind(R.id.rb_shop)
    TextView textShop;
    @Bind(R.id.rb_wholesaler)
    TextView textWholesaler;
    @Bind(R.id.rb_manufacturer)
    TextView textManufacturer;

    private String userType = "1";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_register);
        ButterKnife.bind(this);
        setTextTitle(getString(R.string.title_register));
        textUser.setSelected(true);
    }

    private void setTextDefault() {
        textUser.setSelected(false);
        textShop.setSelected(false);
        textWholesaler.setSelected(false);
        textManufacturer.setSelected(false);
    }

    @OnClick(R.id.rb_user) void selectedUser() {
        setTextDefault();
        textUser.setSelected(true);
        userType = "1";
    }

    @OnClick(R.id.rb_shop) void selectedShop() {
        setTextDefault();
        textShop.setSelected(true);
        userType = "3";
    }

    @OnClick(R.id.rb_wholesaler) void selectedWholesaler() {
        setTextDefault();
        textWholesaler.setSelected(true);
        userType = "2";
    }

    @OnClick(R.id.rb_manufacturer) void selectedManufacturer() {
        setTextDefault();
        textManufacturer.setSelected(true);
        userType = "3";
    }

    @OnClick(R.id.btn_register) void register() {
        String userName = inputUserAccount.getText().toString();
        String userPassword = inputUserPassword.getText().toString();
        String userComfirmPassword = inputUserComfirmPassword.getText().toString();
        String userMobile = inputUserMobile.getText().toString();
        String userNickName = inputUserNick.getText().toString();
        String userAddress = inputUserAddress.getText().toString();
        final NiftyProgressDialogBuilder progressDialogBuilder = new NiftyProgressDialogBuilder(this);
        UITools.createNiftyProgressDialog(progressDialogBuilder, null, false);
        ServerApiClient.getInstance().register(this, getClass().getName(), userName, userPassword, userType, userNickName, userMobile, userAddress, new ServerApiClientCallback() {
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
