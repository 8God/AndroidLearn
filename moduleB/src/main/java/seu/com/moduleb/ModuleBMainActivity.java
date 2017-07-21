package seu.com.moduleb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.moduleb.R;
import com.example.moduleb.R2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.annotation.router.LaunchAnn;
import seu.api.compiler.Launch;

/**
 * Created by wuxiangyu on 2017/7/10.
 */
@LaunchAnn("ModuleBMainActivity")
@Route(path = "/moduleb/activity")
public class ModuleBMainActivity extends AppCompatActivity {
    @BindView(R2.id.btnGoModuleA)
    protected Button btnGoModuleA;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, ModuleBMainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moduleb_activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R2.id.btnGoModuleA)
    public void onViewClicked() {
//        ARouter.getInstance().build("/modulea/activity").navigation();
        Toast.makeText(this, "btnGoModuleA", Toast.LENGTH_SHORT).show();
        Launch.launch("ModuleAMainActivity", this);
    }
}
