package seu.com.androidlearn.test.touch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/6.
 */

public class TouchTestActivity extends AppCompatActivity {
    TouchViewGroup touchViewGroup;
    TouchView touchView;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, TouchTestActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touch_test);
        touchViewGroup = (TouchViewGroup) findViewById(R.id.touchViewGroup);
        touchView = (TouchView) findViewById(R.id.touchView);
    }
}
