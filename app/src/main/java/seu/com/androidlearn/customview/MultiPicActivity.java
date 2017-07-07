package seu.com.androidlearn.customview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/9.
 */

public class MultiPicActivity extends AppCompatActivity {

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, MultiPicActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MultiPicView view = new MultiPicView(this);
        setContentView(R.layout.activity_multi_pic);
    }
}
