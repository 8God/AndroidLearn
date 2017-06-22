package seu.com.androidlearn.test;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import seu.com.androidlearn.MyApplication;
import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/22.
 */

public class TestActivity extends AppCompatActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, TestActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testPackageManager();
    }

    public void testPackageManager() {
        PackageManager manager = MyApplication.getInstance().getPackageManager();
        int state = manager.getApplicationEnabledSetting("com.android.providers.downloads");
        int state1 = manager.getApplicationEnabledSetting("com.seu.cc");
        if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_USER
                || state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED_UNTIL_USED) {
            Log.e("Tag", "error");
        }
        Log.e("Tag", "true");
    }
}
