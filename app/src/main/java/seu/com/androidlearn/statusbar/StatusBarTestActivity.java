package seu.com.androidlearn.statusbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import seu.com.androidlearn.R;
import seu.com.androidlearn.touch.TouchView;
import seu.com.androidlearn.touch.TouchViewGroup;

/**
 * 对于透明主题，4.4的导航栏不会到达顶部，但是5.0的就会到达顶部。
 * 设置了fitsSystemWindows以后，4.4没有改变，而5.0会做一个padding，此时状态栏的颜色就是window背景色
 * Created by wuxiangyu on 2017/6/6.
 */

public class StatusBarTestActivity extends AppCompatActivity {
    TouchViewGroup touchViewGroup;
    TouchView touchView;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, StatusBarTestActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusbar_test);
        touchViewGroup = (TouchViewGroup) findViewById(R.id.touchViewGroup);
        touchView = (TouchView) findViewById(R.id.touchView);
    }
}
