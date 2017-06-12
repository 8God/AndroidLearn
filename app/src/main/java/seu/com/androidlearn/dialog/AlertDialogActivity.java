package seu.com.androidlearn.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/12.
 * 谷歌的官方不建议使用ProgressDialog，这里我们会使用官方推荐的DialogFragment来创建我的加载框
 * 在屏幕旋转导致activity recreate的时候，就会导致该progress没有句柄，从而无法关闭之前的dialog。
 */

public class AlertDialogActivity extends AppCompatActivity {
    @BindView(R.id.btnShowAlertDialog)
    Button btnShowAlertDialog;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, AlertDialogActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert_dialog);
        ButterKnife.bind(this);
        Log.e("TAG", "onCreate________________");
    }

    @OnClick(R.id.btnShowAlertDialog)
    public void onClick() {
        /**
         * 屏幕旋转后，就不就保留该弹窗，且android.view.WindowLeaked
         */
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        builder.setPositiveButton("Sign in",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        // sign in the user ...
                    }
                }).setNegativeButton("Cancel", null).show();
    }
}
