package seu.com.androidlearn.test.typeface;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class TypefaceAfterActivity extends AppCompatActivity {
    public static final String CHANGE_TYPEFACE = "CHANGE_TYPEFACE";
    @BindView(R.id.btnChangeTypeFace)
    Button btnChangeTypeFace;
    @BindView(R.id.tvContent1)
    TextView tvContent1;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, TypefaceAfterActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeface_after);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnChangeTypeFace)
    public void onViewClicked() {
        Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/wawa.ttf");
        tvContent1.setTypeface(typeFace);
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(CHANGE_TYPEFACE));//通知其他before页面更新ui。
    }
}
