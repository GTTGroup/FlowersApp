package com.do1.flowersapp.context;

import android.os.Environment;

/**
 * Created by gufeng on 2015/9/7.
 */
public class AppConstant {

    public static final String APP_DIR = "/flowersapp";

    public static String getFrescoCacheDir() {
        return Environment.getExternalStorageDirectory().getPath() + APP_DIR + "/frescoCache";
    }
}