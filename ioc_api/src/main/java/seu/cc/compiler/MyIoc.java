package seu.cc.compiler;

import android.app.Activity;

import seu.cc.ProxyInfo;

/**
 * Created by wuxiangyu on 2017/7/4.
 */

public class MyIoc {
    public static void inject(Activity activity) {
        String proxyClassFullName = activity.getClass().getName() + "$$" + ProxyInfo.PROXY;
        try {
            Class<?> clazz = Class.forName(proxyClassFullName);
            ViewInjector viewInjector = (ViewInjector) clazz.newInstance();
            viewInjector.inject(activity, activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
