package seu.com.androidlearn.test.classload;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.io.File;

import dalvik.system.DexClassLoader;
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
        test();
    }

    private void showClassLoader() {
        ClassLoader classloadTextView = tvContent.getClass().getClassLoader();
        tvContent.setText("\nContext的ClassLoader： " + Context.class.getClassLoader());
        tvContent.append("\nTextView的ClassLoader： " + TextView.class.getClassLoader());
        tvContent.append("\nActivity的ClassLoader： " + getClassLoader());
        tvContent.append("\nRecyclerView的ClassLoader： " + RecyclerView.class.getClassLoader());

    }
    private void test() {

        File dexOutputDir = this.getDir("dex", 0);
        final String dexOutputPath = dexOutputDir.getAbsolutePath();
        ClassLoader localClassLoader = ClassLoader.getSystemClassLoader();
        DexClassLoader dexClassLoader = new DexClassLoader("/sdcard/apk.apk", dexOutputPath, null, localClassLoader);
        getResources().getString(R.string.app_name);

        try {
            AssetManager assetManager = AssetManager.class.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    private void initView() {
        tvContent = (TextView) findViewById(R.id.tvContent);
    }
}
