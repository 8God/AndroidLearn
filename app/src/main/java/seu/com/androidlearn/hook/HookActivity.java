package seu.com.androidlearn.hook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/7/7.
 */

public class HookActivity extends AppCompatActivity {
    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, HookActivity.class);
        activity.startActivity(intent);
    }

    Button btnHook;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook);
        btnHook = (Button) findViewById(R.id.btnHook);
        HookViewClickUtil.hookView(btnHook);
    }
}
