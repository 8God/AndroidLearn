package seu.com.androidlearn.test.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/12.
 * 谷歌的官方不建议使用ProgressDialog，这里我们会使用官方推荐的DialogFragment来创建我的加载框
 * 在屏幕旋转导致activity recreate的时候，就会导致该progress没有句柄，从而无法关闭之前的dialog。
 * dialogfragment旋转屏幕后夜会自动显示
 */

public class AlertDialogActivity extends AppCompatActivity {
    @BindView(R.id.btnShowAlertDialog)
    Button btnShowAlertDialog;
    @BindView(R.id.btnCustonDialog)
    Button btnCustonDialog;

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
    }

    @OnClick(R.id.btnCustonDialog)
    public void onCustomDialog(View view) {
        new CustomDialog.Builder(this).cancel(false).build().show();
    }
    @OnClick(R.id.btnShowAlertDialog)
    public void onAlertClick() {
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
