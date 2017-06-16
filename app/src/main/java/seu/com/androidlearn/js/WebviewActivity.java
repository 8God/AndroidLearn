package seu.com.androidlearn.js;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/14.
 */

public class WebviewActivity extends AppCompatActivity {

    @BindView(R.id.webview)
    WebView webview;
    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, WebviewActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        JavaScriptInterface JSInterface;
        webview.getSettings().setJavaScriptEnabled(true); ///------- 设置javascript 可用
        JSInterface = new JavaScriptInterface(this); ////------
        webview.addJavascriptInterface(JSInterface, "js2java"); // 设置js接口  第一个参数事件接口实例，第二个是实例在js中的别名，这个在js中会用到
        webview.loadUrl("file:///android_asset/test.html");
    }
    class JavaScriptInterface {
        Context mContext;
        JavaScriptInterface(Context c) {
            mContext = c;
        }

        @JavascriptInterface
        public void changeActivity() {

            Toast.makeText(mContext, "from webview Click0", Toast.LENGTH_LONG).show();
        }
        //注：在4.2之前是不需要添加@JavascriptInterface的，
        public void changeActivity1() {
            Toast.makeText(mContext, "from webview Click1", Toast.LENGTH_LONG).show();
        }
    }

}
