package com.do1.flowersapp.activity;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.do1.flowersapp.R;
import com.do1.flowersapp.config.UserConfig;
import com.do1.flowersapp.context.BaseActivity;
import com.do1.flowersapp.tools.UITools;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by gufeng
 * Created on 2015/10/25 11:55
 * 功能作用: 个人信息
 */
public class UserPersonalActivity extends BaseActivity {

    @Bind(R.id.text_user_name)
    TextView textUserName;
    @Bind(R.id.text_user_nickname)
    TextView textNickName;
    @Bind(R.id.text_user_phone)
    TextView textUserPhone;
    @Bind(R.id.text_user_email)
    TextView textUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        setTextTitle(getString(R.string.title_personalinfo));
        ButterKnife.bind(this);

        textUserName.setText(UserConfig.getUserName(this));
        textUserEmail.setText(UserConfig.getUserEmail(this));
        textNickName.setText(UserConfig.getUserNickName(this));
        textUserPhone.setText(UserConfig.getUserMobile(this));
    }

    @OnClick(R.id.rl_user_address) void showUserAddressList() {
        UITools.intent(this,UserAddressManageActivity.class);
    }
}
