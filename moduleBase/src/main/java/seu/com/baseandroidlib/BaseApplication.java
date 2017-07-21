package seu.com.baseandroidlib;

import android.app.Application;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;

import seu.api.compiler.Launch;
import seu.com.baseandroidlib.imageloader.ImageLoader;

/**
 * Created by wuxiangyu on 2017/7/10.
 */

public class BaseApplication extends Application implements IApplication{
    @Override
    public void onCreate() {
        super.onCreate();
        load(this);
    }

    /**
     * 组件化的时候，baselib中的BaseApplication其实是不会被创建的，但是我们想办法在app module中发射了这个load方法，间接的把application对象传入了load中来初始化
     * @param application
     */
    @Override
    public void load(Application application) {
//        if (Configs.isDebug) {
            Log.e("Tag", "debugggggggggg");
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
//        }
        ARouter.init(application);
        Launch.init(application);
        ImageLoader.init(application);
    }
}
