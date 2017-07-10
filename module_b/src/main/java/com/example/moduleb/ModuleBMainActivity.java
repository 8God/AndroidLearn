package com.example.moduleb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * Created by wuxiangyu on 2017/7/10.
 */
@Route(path = "/moduleb/activity")
public class ModuleBMainActivity extends AppCompatActivity {
    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ModuleBMainActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moduleb_activity_main);
    }
}