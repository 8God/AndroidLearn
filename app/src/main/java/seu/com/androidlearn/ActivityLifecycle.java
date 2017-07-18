package seu.com.androidlearn;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import seu.com.androidlearn.base.Logger;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {
    public static final String TAG = "ActivityLifeCycle";
    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Logger.e(TAG, activity.getLocalClassName() + "  :create");
    }

    @Override
    public void onActivityStarted(Activity activity) {
        Logger.e(TAG, activity.getLocalClassName() + "  :started");
    }

    @Override
    public void onActivityResumed(Activity activity) {

        Logger.e(TAG, activity.getLocalClassName() + "  :resumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Logger.e(TAG, activity.getLocalClassName() + "  :paused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        Logger.e(TAG, activity.getLocalClassName() + "  :stopped");
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Logger.e(TAG, activity.getLocalClassName() + "  :saveInstance");
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Logger.e(TAG, activity.getLocalClassName() + "  :destroyed");
    }
}
