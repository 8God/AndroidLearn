package seu.com.androidlearn.test.ioc.runtime;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ann.BindView;
import com.example.test.GetMsg;

import seu.cc.compiler.MyIoc;

import seu.com.androidlearn.R;
import seu.iocruntime.ViewInjectUtils;
import seu.iocruntime.ann.ContentView;
import seu.iocruntime.ann.OnClick;
import seu.iocruntime.ann.ViewInject;

/**
 * Created by wuxiangyu on 17/7/2.
 */
@ContentView(R.layout.activity_runtime)
public class RuntimeActivity extends AppCompatActivity {
    @ViewInject(R.id.btnRuntime_ioc)
    Button btnRuntime_ioc;
    @BindView(R.id.btnClass_ioc)
    Button btnClass_ioc;
    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, RuntimeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_runtime);
        ViewInjectUtils.inject(this);
        MyIoc.inject(this);
            btnClass_ioc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(RuntimeActivity.this, "show ioc_click_class", Toast.LENGTH_LONG).show();
                }
            });
    }

    @OnClick(R.id.btnRuntime_ioc)
    public void clickRuntime(View view) {
        Toast.makeText(this, "show ioc_click_runtime", Toast.LENGTH_LONG).show();
    }
    @GetMsg(id = 10)
    public void testGetMsg(String name) {
        Log.e("Tag", "TestGetMsg");
    }
}
