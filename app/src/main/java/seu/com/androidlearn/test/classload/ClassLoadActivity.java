package seu.com.androidlearn.test.classload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class ClassLoadActivity extends AppCompatActivity {
    protected TextView tvContent;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ClassLoadActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_classload);
        initView();
        showClassLoader();
    }

    private void showClassLoader() {
        ClassLoader classloadTextView = tvContent.getClass().getClassLoader();
        tvContent.setText("\nContext的ClassLoader： " + Context.class.getClassLoader());
        tvContent.append("\nTextView的ClassLoader： " + TextView.class.getClassLoader());
        tvContent.append("\nActivity的ClassLoader： " + getClassLoader());

    }

    private void initView() {
        tvContent = (TextView) findViewById(R.id.tvContent);
    }
}
