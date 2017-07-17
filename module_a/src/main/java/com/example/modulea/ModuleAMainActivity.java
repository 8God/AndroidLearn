package com.example.modulea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.module_a.R;
/**
 * Created by wuxiangyu on 2017/7/10.
 */

@Route(path = "/modulea/activity")
public class ModuleAMainActivity extends AppCompatActivity {
    protected Button btnGoModuleB;
    protected Button btnToast;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ModuleAMainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.modulea_activity_main);
        initView();
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build("/moduleb/activity").navigation();
            }
        });
    }

    private void initView() {
        btnGoModuleB = (Button) findViewById(R.id.btnGoModuleB);
        btnToast = (Button) findViewById(R.id.btnToast);
    }
}
