package seu.com.androidlearn;

import android.app.Application;

/**
 * Created by wuxiangyu on 2017/6/6.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            /**
             *有些地方会把耗时的初始化操作放在IntentService中操作：
             *http://www.jianshu.com/p/4f10c9a10ac9,个人感觉不能保证第一个Activity出现的时候已经确保都初始化完毕了。
             */
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
