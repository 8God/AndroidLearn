package seu.com.androidlearn;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.battery.BatteryActivity;
import seu.com.androidlearn.customview.MultiPicActivity;
import seu.com.androidlearn.dialog.AlertDialogActivity;
import seu.com.androidlearn.file.FileActivity;
import seu.com.androidlearn.js.WebviewActivity;
import seu.com.androidlearn.leak.LeakActivity;
import seu.com.androidlearn.okhttpcache.OkHttpCacheActivity;
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
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(getResources().getColor(R.color.C_C6B697));
//        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTouchEvent)
    protected void clickTouchButton() {
        Log.e("Tag",findViewById(android.R.id.content)+ "");
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
    @OnClick(R.id.btnFile)
    protected void clickFile() {
        FileActivity.launch(this);
    }

    @OnClick(R.id.btnOkhttpCache)
    protected void clickOkhttpCache() {
        OkHttpCacheActivity.launch(this);
    }

    @Override
    protected void onPause() {
        Log.e("Tag", "onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.e("Tag", "onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.e("Tag", "onDestroy");
        super.onDestroy();
    }
}
