package com.do1.flowersapp.config;

import android.content.Context;
import android.content.SharedPreferences;

import com.do1.flowersapp.constants.SharedPreferencesConstants;

/**
 * Created by gufeng
 * Created on 2015/10/26 01:58
 * 功能作用: 用户个人信息
 */
public class UserConfig {

    public static SharedPreferences getSharedPref(Context ctx,String prefName) {
        SharedPreferences sp = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return sp;
    }

    public static void setUserLoginState(Context context, boolean login) {
        SharedPreferences sp = getSharedPref(context, SharedPreferencesConstants.USER_CONFIG_FILE);
        sp.edit().putBoolean(SharedPreferencesConstants.USER_LOGIN_STATE, login).commit();
    }

    public static boolean getUserLoginState(Context context) {
        SharedPreferences sp = getSharedPref(context, SharedPreferencesConstants.USER_CONFIG_FILE);
        return sp.getBoolean(SharedPreferencesConstants.USER_LOGIN_STATE, false);
    }

    public static void setUserName(Context context, String userName) {
        SharedPreferences sp = getSharedPref(context, SharedPreferencesConstants.USER_CONFIG_FILE);
        sp.edit().putString(SharedPreferencesConstants.USER_NAME, userName).commit();
    }

    public static String getUserName(Context context) {
        SharedPreferences sp = getSharedPref(context, SharedPreferencesConstants.USER_CONFIG_FILE);
        return sp.getString(SharedPreferencesConstants.USER_NAME, "");
    }

    public static void setUserMemberId(Context context, String memberId) {
        SharedPreferences sp = getSharedPref(context, SharedPreferencesConstants.USER_CONFIG_FILE);
        sp.edit().putString(SharedPreferencesConstants.USER_MEMBER_ID, memberId).commit();
    }

    public static String getUserMemberId(Context context) {
        SharedPreferences sp = getSharedPref(context, SharedPreferencesConstants.USER_CONFIG_FILE);
        return sp.getString(SharedPreferencesConstants.USER_MEMBER_ID, "");
    }
}
