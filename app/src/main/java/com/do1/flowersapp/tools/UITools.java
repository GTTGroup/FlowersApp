package com.do1.flowersapp.tools;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by gufeng
 * Created on 2015/10/16 01:05
 * 功能作用:
 */
public class UITools {

    public static void intent(Context context,Class activityClass) {
        Intent intent = new Intent();
        intent.setClass(context,activityClass);
        context.startActivity(intent);
    }

    public static void intent(Context context,Class activityClass,Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(context,activityClass);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

}
