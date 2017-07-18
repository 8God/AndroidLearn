package seu.com.androidlearn;

import android.app.Application;
import android.graphics.Typeface;
import android.util.Log;

import com.example.baseandroidlib.BaseApplication;
import com.squareup.leakcanary.LeakCanary;

import java.lang.reflect.Field;

/**
 * Created by wuxiangyu on 2017/6/6.
 */

public class MyApplication extends BaseApplication {
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("Tag", "MyApplication: onCreate");
        instance = this;
        try {
            /**
             *有些地方会把耗时的初始化操作放在IntentService中操作：
             *http://www.jianshu.com/p/4f10c9a10ac9,个人感觉不能保证第一个Activity出现的时候已经确保都初始化完毕了。
             */
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        initLeakCanary();
        initTypeFace();
        initLifecycle();
    }
    public static MyApplication getInstance() {
        return instance;
    }
    private void initLifecycle() {
        ActivityLifecycle activityLifecycle = new ActivityLifecycle();
        registerActivityLifecycleCallbacks(activityLifecycle);
    }
    private void initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }

    private void initTypeFace() {
        Typeface typeFaceYaHei = Typeface.createFromAsset(getAssets(), "fonts/webfont.ttf");
        try
        {
            Field field = Typeface.class.getDeclaredField("MONOSPACE");
            field.setAccessible(true);
            field.set(null, typeFaceYaHei);
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
    }


}
