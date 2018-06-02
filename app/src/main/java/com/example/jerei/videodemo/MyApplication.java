package com.example.jerei.videodemo;

import android.app.Application;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.CsvFormatStrategy;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.zxy.recovery.callback.RecoveryCallback;
import com.zxy.recovery.core.Recovery;



public class MyApplication  extends Application{


    @Override
    public void onCreate() {
        super.onCreate();
        OpenLogMethod();
        OpenCrashMethod();
    }


    /**
     * 方法描述: 开启日志记录方法
     * 创建人:  tangchao
     * 创建时间: 2017/10/23 12:37
     * 联系方式:419704299@qq.com
     */
    private void OpenLogMethod() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .methodOffset(3)        // (Optional) Skips some method invokes in stack trace. Default 5
//        .logStrategy(customLog) // (Optional) Changes the log strategy to print out. Default LogCat
                .tag("videoDemo")   // (Optional) Custom tag for each log. Default PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));

        FormatStrategy formatStrategyLocation = CsvFormatStrategy.newBuilder()
                .tag("videoDemo")
                .build();
        Logger.addLogAdapter(new DiskLogAdapter(formatStrategyLocation));
    }

    /**
     * 方法描述: 开启崩溃日志
     * 创建人:  tangchao
     * 创建时间: 2017/10/27 14:18
     * 联系方式:419704299@qq.com
     */
    private void OpenCrashMethod() {
        Recovery.getInstance()
                .debug(true)
                .recoverInBackground(false)
                .recoverStack(true)
                .mainPage(MainActivity.class)
                .recoverEnabled(true)
                .callback(new MyCrashCallback())
                .silent(false, Recovery.SilentMode.RECOVER_ACTIVITY_STACK)
                .skip(MainActivity.class)
                .init(this);

        MyCrashHandler.register();
    }

    /**
     * 类描述:https://github.com/Sunzxyong/Recovery
     * 创建人:   tangchao
     * 创建时间:  2017/10/27 14:30
     * 联系方式:419704299@qq.com
     * 修改人:    tangchao
     * 修改时间: 2017/10/27 14:30
     * 修改备注:  [说明本次修改内容]
     */
    static final class MyCrashCallback implements RecoveryCallback {
        @Override
        public void stackTrace(String exceptionMessage) {
            Logger.e("exceptionMessage:" + exceptionMessage);
        }

        @Override
        public void cause(String cause) {
            Logger.e("cause:" + cause);
        }

        @Override
        public void exception(String exceptionType, String throwClassName, String throwMethodName, int throwLineNumber) {
            Logger.e("exceptionClassName:" + exceptionType);
            Logger.e("throwClassName:" + throwClassName);
            Logger.e("throwMethodName:" + throwMethodName);
            Logger.e("throwLineNumber:" + throwLineNumber);
        }

        @Override
        public void throwable(Throwable throwable) {

        }
    }

}
