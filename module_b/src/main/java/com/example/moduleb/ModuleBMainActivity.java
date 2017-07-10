package com.example.moduleb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by wuxiangyu on 2017/7/10.
 */

public class ModuleBMainActivity extends AppCompatActivity {
    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ModuleBMainActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_main);
    }
}
