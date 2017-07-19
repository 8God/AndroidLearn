package com.example.modulea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.router.LaunchAnn;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by wuxiangyu on 2017/7/10.
 */
@LaunchAnn("ModuleAMainActivity")
@Route(path = "/modulea/activity")
public class ModuleAMainActivity extends AppCompatActivity {
    @BindView(R2.id.btnGoModuleB)
    protected Button btnGoModuleB;
    @BindView(R2.id.btnToast)
    protected Button btnToast;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ModuleAMainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modulea_activity_main);
        ButterKnife.bind(this);
    }


    @OnClick(R2.id.btnGoModuleB)
    public void onViewClicked() {
        ARouter.getInstance().build("/moduleb/activity").navigation();
    }
    @OnClick(R2.id.btnToast)
    public void onClickToast(View view) {
        Toast.makeText(this, "btnToast", Toast.LENGTH_SHORT).show();
    }
}
