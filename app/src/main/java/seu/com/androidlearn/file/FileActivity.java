package seu.com.androidlearn.file;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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
        try {
            init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void init() throws IOException{
        String sdcard = Environment.getExternalStorageDirectory().getPath();
        File file = new File(sdcard + "/Android/data/com.netease.iplay");
        String[] list = file.list();
        Log.e("Tag", list[0]);
        File files = new File(file, list[0]);
        File[] fileStrs = files.listFiles();
        Log.e("Tag", Arrays.toString(fileStrs));
        for (File delFile : fileStrs) {
            if (file.isDirectory()) {
                deleteContents(file);
            }
            if (file.exists()) {
                file.delete();
            }
        }
        files.delete();
    }
    public void deleteContents(File directory) throws IOException {
        if (directory == null || !directory.exists()) {
            return;
        }
        File[] files = directory.listFiles();
        if (files == null || files.length == 0 ) {
            return;
        }
        for (File file : files) {
            if (file.isDirectory()) {
                deleteContents(file);
            }
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
