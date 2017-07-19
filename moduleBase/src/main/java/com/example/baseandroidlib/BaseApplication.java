package com.example.baseandroidlib;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * Created by wuxiangyu on 2017/7/10.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Tag", "BaseApplication: onCreate");
        if (Configs.isDebug) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
    }
}
