package seu.api.compiler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;
import java.util.Map;
import dalvik.system.DexFile;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class Launch {
    static Map<String, Class<?>> routerMap = new HashMap<>();

    private volatile static boolean inited = false;
    public static void init(Context context) {
        inited = true;
        initMap(Constants.PACKAGE_NAME, context);
    }
    /**
     * todo暂无考虑多个dex的情况
     */
    public static List<String> getClassListByPackageName(String packageName, Context context) {
        List<String> classNameList=new ArrayList<String>();
        try {
            DexFile df = new DexFile(context.getPackageCodePath());//通过DexFile查找当前的APK中可执行文件
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

    public static void initMap(String name, Context context) {
        if (!inited) {
            throw new IllegalStateException("Launch not init");
        }
        List<String> classList = getClassListByPackageName(Constants.PACKAGE_NAME, context);
        Log.e("Tag", classList.toString());

        if (classList == null) {
            return;
        }
        for (String fullClassName : classList) {
            Class<?> clazz;
            try {
                clazz = Class.forName(fullClassName);
                LaunchInjector launchInjector = (LaunchInjector) clazz.newInstance();
                Map<String, Class<?>> mapFromLib = launchInjector.getMap();
                routerMap.putAll(mapFromLib);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void launch(String name, Activity activity) {
        Class<?> clazz = routerMap.get(name);
        if (clazz != null) {
            Intent intent = new Intent();
            intent.setClass(activity, clazz);
            activity.startActivity(intent);
        }
    }
}
