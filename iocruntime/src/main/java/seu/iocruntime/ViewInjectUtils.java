package seu.iocruntime;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import seu.iocruntime.ann.ContentView;
import seu.iocruntime.ann.OnClick;
import seu.iocruntime.ann.ViewInject;

/**
 * Created by wuxiangyu on 17/7/2.
 */

public class ViewInjectUtils {
    public static void inject(Activity activity) {
        injectContentView(activity);
        injectViews(activity);
        injectClicks(activity);
    }
    /**
     * 注入setContentView
     * @param activity
     */
    private static void injectContentView(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        ContentView contentView = clazz.getAnnotation(ContentView.class);
        if (contentView != null) {
            int contentLayoutId = contentView.value();
            try {
                Method method = clazz.getMethod("setContentView", int.class);
                method.setAccessible(true);
                method.invoke(activity, contentLayoutId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 注入findViewById
     * @param activity
     */
    private static void injectViews(Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Field[] fields = clazz.getDeclaredFields();//自己的所有类型的field
        for (Field field : fields) {
            ViewInject viewInjectAnn = field.getAnnotation(ViewInject.class);
            if (viewInjectAnn != null) {
                int viewId = viewInjectAnn.value();
                try {
                    Method method = clazz.getMethod("findViewById", int.class);
//                    method.setAccessible(true);//对于public方法可以不处理
                    View view = (View) method.invoke(method, viewId);
                    field.setAccessible(true);
                    field.set(activity, view);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    /**
     * 注入setOnClickListener
     * @param activity
     */
    private static void injectClicks(final Activity activity) {
        Class<? extends Activity> clazz = activity.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        for (final Method method : methods) {
            OnClick onClickAnn = method.getAnnotation(OnClick.class);
            if (onClickAnn != null) {
                int viewId = onClickAnn.value();
                String name = method.getName();
                View view = activity.findViewById(viewId);
                if (view != null) {
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            method.setAccessible(true);
                            try {
                                method.invoke(activity, v);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        }
    }

}
