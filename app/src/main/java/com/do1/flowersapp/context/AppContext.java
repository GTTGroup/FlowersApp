package com.do1.flowersapp.context;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Created by gufeng
 * Created on 2015/10/14 00:37
 * 功能作用:
 */
public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
