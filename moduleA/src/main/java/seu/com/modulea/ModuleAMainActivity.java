package seu.com.modulea;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.annotation.router.LaunchAnn;


/**
 * Created by wuxiangyu on 2017/7/10.
 */
//@LaunchAnn("ModuleAMainActivity")
@Route(path = "/modulea/ModuleAMainActivity")
public class ModuleAMainActivity extends AppCompatActivity {
    @BindView(R2.id.btnGank)
    Button mBtnGank;

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

    @OnClick(R2.id.btnGank)
    public void onClickGank(View view) {
        ARouter.getInstance().build("/modulea/GankActivity").navigation();
    }

}
