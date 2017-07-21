package seu.com.androidlearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnTest)
    Button btnTest;
    @BindView(R.id.btnGank_moduleA)
    Button btnGankModuleA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTest)
    public void onClickTest(View view) {
        TestMainActivity.launch(this);
    }
    @OnClick(R.id.btnGank_moduleA)
    public void onClickGank(View view) {
        ARouter.getInstance().build("/modulea/ModuleAMainActivity").navigation();
    }


}
