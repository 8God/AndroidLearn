package seu.com.baseandroidlib;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;

import butterknife.BindView;

/**
 * Created by wuxiangyu on 2017/7/7.
 */
@Route(path = "/modulebase/activity")
public class TestActivity extends AppCompatActivity {
    @BindView(R2.id.btnLibd)
    Button btnLib;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, TestActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        btnLib = (Button) findViewById(R.id.btnLibd);
        btnLib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(TestActivity.this, "libToast", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
