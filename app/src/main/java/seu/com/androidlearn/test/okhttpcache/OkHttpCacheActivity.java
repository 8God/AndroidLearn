package seu.com.androidlearn.test.okhttpcache;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import seu.com.androidlearn.R;

/**
 * http://blog.csdn.net/briblue/article/details/52920531
 * 开启okhttp本地缓存，在缓存事件内，都不会去网络请求。
 * Created by wuxiangyu on 2017/6/20.
 */

public class OkHttpCacheActivity extends AppCompatActivity {
    public static final String ULR = "http://gank.io/api/data/Android/10/1";
    @BindView(R.id.btnOkhttpCache)
    Button btnOkhttp;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.btnOkhttpHttp)
    Button btnOkhttpHttp;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, OkHttpCacheActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp_cache);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.btnOkhttpHttp)
    public void clickOkhttpHttp(View view) {
        File cacheFile = new File(getCacheDir(), "okhttpcache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb

        OkHttpClient client = new OkHttpClient.Builder().cache(cache).build();
        final Request request = new Request.Builder()
                .url(ULR)
                .build();
        Call call = client.newCall(request);
        final long currentTime = System.currentTimeMillis();
        Log.e("Tag", "start time http: " + currentTime);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                try {
                    Thread.sleep(5 * 1000);
                    Log.e("Tag", " onFailure");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody body = response.body();
                String string = body.string();
                Log.e("Tag", string);
                Log.e("Tag", "spend time http:" + (System.currentTimeMillis() - currentTime));
            }
        });
    }

    class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originResponse = chain.proceed(chain.request());
            //设置缓存时间为60秒，并移除了pragma消息头，移除它的原因是因为pragma也是控制缓存的一个消息头属性
            return originResponse.newBuilder().removeHeader("pragma")
                    .header("Cache-Control","max-age=60").build();
        }
    }

    public void init() {
        cacheFile = new File(getExternalCacheDir(), "okhttpcache");
        cache = new Cache(cacheFile, 1024 * 1024 * 100); //100Mb
        client = new OkHttpClient.Builder()
                .addNetworkInterceptor(new CacheInterceptor())
                .cache(cache)
                .build();
    }
    CacheControl cacheControl = new CacheControl.Builder().maxAge(10, TimeUnit.MINUTES).build();

    File cacheFile;
    Cache cache;
    OkHttpClient client;
    @OnClick(R.id.btnOkhttpCache)
    public void cliclOkhttpCache(View view) {

        final Request request = new Request.Builder()
//                .cacheControl(cacheControl)
                .url(ULR)
                .build();
        Call call = client.newCall(request);
        final long currentTime = System.currentTimeMillis();
        Log.e("Tag", "start time cache: " + currentTime);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                try {
                    Thread.sleep(5 * 1000);
                    Log.e("Tag", " onFailure");
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
//                ResponseBody body = response.body();
//                String string = body.string();
                Response httpResponse = response.networkResponse();
                Response cacheRespone = response.cacheResponse();
//                Log.e("Tag", "response: "+ string.substring(0, 10));

                Log.e("Tag","testCache: response1 :"+response.body().string().substring(0, 10));
                Log.e("Tag", "testCache: response1 cache :"+response.cacheResponse());
                if (response.cacheResponse() != null && response.cacheResponse().body()!= null) {
                    Log.e("Tag", "cache: " + response.cacheResponse().body().string().substring(0, 10));
                }
                Log.e("Tag","testCache: response1 network :"+response.networkResponse());
                Log.e("Tag", "spend time cache: " + (System.currentTimeMillis() - currentTime));
            }
        });
    }
}
