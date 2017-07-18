package seu.com.androidlearn.base;

import android.util.Log;

import seu.com.androidlearn.BuildConfig;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class Logger {
    public static boolean isDebug = BuildConfig.DEBUG;
    public static void e(String tag, String msg) {
        if (isDebug) {
            Log.e(tag, msg);
        }
    }
}
