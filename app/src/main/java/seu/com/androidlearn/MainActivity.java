package seu.com.androidlearn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;


import com.alibaba.android.arouter.launcher.ARouter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.test.battery.BatteryActivity;
import seu.com.androidlearn.test.broadcast.BroadcastActivity;
import seu.com.androidlearn.test.customview.MultiPicActivity;
import seu.com.androidlearn.test.dialog.AlertDialogActivity;
import seu.com.androidlearn.test.dynamicviewpager.ViewPagerActivity;
import seu.com.androidlearn.test.file.FileActivity;
import seu.com.androidlearn.test.hook.HookActivity;
import seu.com.androidlearn.test.ioc.runtime.RuntimeActivity;
import seu.com.androidlearn.test.js.WebviewActivity;
import seu.com.androidlearn.test.leak.LeakActivity;
import seu.com.androidlearn.test.okhttpcache.OkHttpCacheActivity;
import seu.com.androidlearn.test.recycle.RecyclerViewActivity;
import seu.com.androidlearn.test.statusbar.StatusBarTestActivity;
import seu.com.androidlearn.test.swipe.RecyclerActivity;
import seu.com.androidlearn.test.touch.TouchTestActivity;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btnTouchEvent)
    Button btnTouchEvent;
    @BindView(R.id.btnStatusBar)
    Button btnStatusBar;

    @Override
    public void recreate() {
        Log.e("Tag", "recreate");
        super.recreate();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.e("Tag", "onSaveInstanceState");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("Tag", "onCreate");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setStatusBarColor(getResources().getColor(R.color.C_C6B697));
//        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnTtest)
    protected void clickTest() {
        Log.e("Tag",findViewById(android.R.id.content)+ "");

        ARouter.getInstance().build("/test/testactivity").navigation();
//        TestActivity.launch(this);
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

    @OnClick(R.id.btnViewpager)
    protected void clickViewpager() {
        ViewPagerActivity.launch(this);
    }
    @OnClick(R.id.btnBaseLibrary)
    protected void clicpBaseLibrary() {
//        com.example.baseandroidlib.TestActivity.launch(this);
    }
    @OnClick(R.id.btnBroadcast)
    protected void clicBroadcast() {
        BroadcastActivity.launch(this);
    }
    @OnClick(R.id.btnIOC_Runtime)
    protected void btnIOC_Runtime() {
        RuntimeActivity.launch(this);
    }
    @OnClick(R.id.btn_hook)
    protected void btn_hook() {
        HookActivity.launch(this);
    }
    @OnClick(R.id.btn_module_a)
    protected void btnModulea() {
//        ModuleAMainActivity.launch(this);
        ARouter.getInstance().build("/modulea/activity").navigation();
    }
    @OnClick(R.id.btn_module_b)
    protected void btnModuleb() {
//        ModuleBMainActivity.launch(this);
        ARouter.getInstance().build("/moduleb/activity").navigation();
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
