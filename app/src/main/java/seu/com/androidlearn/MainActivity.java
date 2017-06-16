package seu.com.androidlearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.battery.BatteryActivity;
import seu.com.androidlearn.customview.MultiPicActivity;
import seu.com.androidlearn.dialog.AlertDialogActivity;
import seu.com.androidlearn.js.WebviewActivity;
import seu.com.androidlearn.leak.LeakActivity;
import seu.com.androidlearn.recycle.RecyclerViewActivity;
import seu.com.androidlearn.statusbar.StatusBarTestActivity;
import seu.com.androidlearn.swipe.RecyclerActivity;
import seu.com.androidlearn.touch.TouchTestActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnTouchEvent)
    Button btnTouchEvent;
    @BindView(R.id.btnStatusBar)
    Button btnStatusBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTouchEvent)
    protected void clickTouchButton() {
        TouchTestActivity.launch(this);
    }
    @OnClick(R.id.btnStatusBar)
    protected void clickStatusBarButton() {
        StatusBarTestActivity.launch(this);
    }
    @OnClick(R.id.btnMultiPic)
    protected void clickMultiPic() {
        MultiPicActivity.launch(this);
    }
    @OnClick(R.id.btnShowAlertDialog)
    protected void clickAlertDialog() {
        AlertDialogActivity.launch(this);
    }
    @OnClick(R.id.btnBattery)
    protected void clickBattery() {
        BatteryActivity.launch(this);
    }
    @OnClick(R.id.btnSwipe)
    protected void clickSwipe() {
        RecyclerActivity.launch(this);
    }
    @OnClick(R.id.btnGridRecyc)
    protected void clickGridRecyc() {
        RecyclerViewActivity.launch(this);
    }
    @OnClick(R.id.btnCreateCallback)
    protected void clickCreateCallback() {
        LeakActivity.launch(this);
    }
    @OnClick(R.id.btnClickWebView)
    protected void clickWebView() {
        WebviewActivity.launch(this);
    }
}
