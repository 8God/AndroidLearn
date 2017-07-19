package seu.cc.compiler;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class Launch {
    public static void launch(String name, Activity activity) {
        String launchClass = "com.seu.util.LaunchUtil";
        try {
            Class<?> clazz = Class.forName(launchClass);
            LaunchInjector launchInjector = (LaunchInjector) clazz.newInstance();
            String packagename = launchInjector.getPackageName(name);
            clazz = Class.forName(packagename);
            Intent intent = new Intent();
            intent.setClass(activity, clazz);
            activity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        String proxyClassFullName = activity.getClass().getName() + "$$" + "ViewInject";
    }
}
