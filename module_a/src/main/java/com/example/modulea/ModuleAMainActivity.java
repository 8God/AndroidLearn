package com.example.modulea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.module_a.R;

/**
 * Created by wuxiangyu on 2017/7/10.
 */

public class ModuleAMainActivity extends AppCompatActivity {
    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ModuleAMainActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modulea_activity_main);
    }
}
