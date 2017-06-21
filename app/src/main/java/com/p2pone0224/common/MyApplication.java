package com.p2pone0224.common;

import android.app.Application;
import android.content.Context;

/**
 * 作者：田学伟 on 2017/6/20 18:28
 * QQ：93226539
 * 作用：
 */

public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化给上下文
        context = this;

        //CrashHandler.getInstance().init(this);

    }
}
