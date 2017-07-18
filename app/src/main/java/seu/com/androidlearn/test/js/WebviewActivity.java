package seu.com.androidlearn.test.js;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.DownloadListener;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
    String data;
    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, WebviewActivity.class);
        activity.startActivity(intent);
    }
    public static void launch(Activity activity, String data) {
        Intent intent = new Intent();
        intent.setClass(activity, WebviewActivity.class);
        intent.putExtra("Data", data);
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
        data = getIntent().getStringExtra("Data");
        if (TextUtils.isEmpty(data)) {
            webview.loadUrl("file:///android_asset/test.html");
        } else {
            webview.loadData(data, "text/html", "UTF-8");
        }
        webview.setWebViewClient(new MyWebViewClient());//url跳转在本webview内进行
        webview.setDownloadListener(new DownloadListener() {//下载功能实现直接跳转到系统下载
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {
                Uri uri = Uri.parse(url);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
    }
    class MyWebViewClient extends WebViewClient {
        //重写shouldOverrideUrlLoading方法，使点击链接后不使用其他的浏览器打开。
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            view.loadUrl(url);
            //如果不需要其他对点击链接事件的处理返回true，否则返回false
            return false;

        }



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
