package seu.com.androidlearn.file;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;

import seu.com.androidlearn.R;

/**
 * 居然可以访问其他应用的android/data/other_app_name/file/
 * Created by wuxiangyu on 2017/6/19.
 */

public class FileActivity extends AppCompatActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, FileActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        init();
    }

    private void init() {
        String sdcard = Environment.getExternalStorageDirectory().getPath();
        File file = new File(sdcard + "/Android/data/com.netease.iplay");
        String[] list = file.list();
        Log.e("Tag", list[0]);
        File files = new File(file, list[0]);
        files.delete();
    }
}
