package com.example.jerei.videodemo;

import com.orhanobut.logger.Logger;

/**
 * 类描述:https://github.com/Sunzxyong/Recovery
 * 创建人:   tangchao
 * 创建时间:  2017/10/27 14:30
 * 联系方式:419704299@qq.com
 * 修改人:    tangchao
 * 修改时间: 2017/10/27 14:30
 * 修改备注:  [说明本次修改内容]
 */
public class MyCrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mUncaughtExceptionHandler;

    public MyCrashHandler() {
        mUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        Logger.e("myCrashHandler...");
        mUncaughtExceptionHandler.uncaughtException(t, e);
    }


    public static void register() {
        Thread.setDefaultUncaughtExceptionHandler(new MyCrashHandler());
    }
}
