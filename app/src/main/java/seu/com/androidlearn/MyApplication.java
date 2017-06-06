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
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
