package seu.api.compiler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import dalvik.system.DexFile;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class Launch {
    Map<String, Class<?>> routerMap = new HashMap<>();
    static {

    }

    public static List<String> getClassListByPackageName(String packageName, Activity activity) {
        List<String> classNameList=new ArrayList<String>();
        try {
            DexFile df = new DexFile(activity.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
            Enumeration<String> enumeration = df.entries();//获取df中的元素  这里包含了所有可执行的类名 该类名包含了包名+类名的方式
            while (enumeration.hasMoreElements()) {//遍历
                String className = (String) enumeration.nextElement();

                if (className.contains(packageName)) {//在当前所有可执行的类里面查找包含有该包名的所有类
                    classNameList.add(className);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  classNameList;
    }
    public static void launch(String name, Activity activity) {
        List<String> classList = getClassListByPackageName(Constants.PACKAGE_NAME, activity);
        Log.e("Tag", classList.toString());

        String launchClass = "seu.com.util.LaunchUtil%%app";
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
