package seu.com.androidlearn.test.typeface;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class TypefaceBeforeActivity extends AppCompatActivity {
    @BindView(R.id.btnGotoPage)
    Button btnGotoPage;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, TypefaceBeforeActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_typeface_before);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnGotoPage)
    public void onViewClicked() {
        TypefaceAfterActivity.launch(this);
    }
}
