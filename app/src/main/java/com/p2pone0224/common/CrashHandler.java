package com.p2pone0224.common;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.widget.Toast;

/**
 * 作者：田学伟 on 2017/6/21 16:33
 * QQ：93226539
 * 作用：
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Context context;

    private CrashHandler() {
    }

    private static CrashHandler crashHandler = new CrashHandler();

    public static CrashHandler getInstance() {
        return crashHandler;
    }

    public void init(Context context) {
        //告诉系统 崩溃的操作 由我来执行
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.context = context;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        /**
         * 1.提醒用户
         * 2.捕获异常
         * 3.退出程序
         */
        new Thread() {
            public void run() {
                Looper.prepare();
                //执行在主线程中的代码
                Toast.makeText(context, "系统崩溃...", Toast.LENGTH_SHORT).show();
                Looper.loop();
            }
        }.start();

        collection(e);

        AppManager.getInstance().removeAll();
        //杀死当前的进程
        android.os.Process.killProcess(android.os.Process.myPid());
        //参数 除了0以外都表示非正常退出
        System.exit(0);//退出虚拟机
    }

    private void collection(Throwable e) {
        String version = Build.BOARD;
        //发送给服务器
    }
}
