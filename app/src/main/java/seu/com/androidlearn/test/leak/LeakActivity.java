package seu.com.androidlearn.test.leak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import seu.com.androidlearn.R;
import seu.com.androidlearn.test.js.WebviewActivity;

/**
 * Created by wuxiangyu on 2017/6/14.
 */

public class LeakActivity extends AppCompatActivity {
    @BindView(R.id.btnCreateCallback)
    Button btnCreateCallback;
    Call call;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        ButterKnife.bind(this);
//        runThread();
    }


    public void runThread() {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(60 * 1000 * 10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, LeakActivity.class);
        activity.startActivity(intent);
    }
    public void updateUi() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                btnCreateCallback.setText("ddddddddd");
            }
        });
    }
    @Override
    protected void onDestroy() {
        if (call != null) {
            call.cancel();
        }
        super.onDestroy();
    }
    /**
     * 最正确的姿势应该是这样的call
     */
    static class NewCall extends MyCallBack<LeakActivity> {

        public NewCall(LeakActivity context) {
            super(context);
        }

        @Override
        public void onMyFailure(Call call, IOException e, LeakActivity leakActivity) {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            leakActivity.updateUi();
        }

        @Override
        public void onMyResponse(Call call, Response response, LeakActivity leakActivity) {
            try {
                Thread.sleep(5 * 1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            leakActivity.updateUi();
        }

    }

    NewCall mNewCall = new NewCall(this);

    @OnClick(R.id.btnCreateCallback)
    public void clickCreateCallback() {
        StringBuilder sb = new StringBuilder();
        OkHttpClient client = new OkHttpClient.Builder().build();
        final Request request = new Request.Builder()
                .url("http://game.play.163.com/zoo/zookeeper/iplayhybird/special/2017/promotion-tools/#/")
                .build();
        Call call = client.newCall(request);
        final long currentTime = System.currentTimeMillis();
        Log.e("Tag", "start time: " + currentTime);
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
                Log.e("Tag", "spend time: "+ (System.currentTimeMillis() - currentTime));
            }
        });
    }

    private static Callback callback = new Callback() {
        @Override
        public void onFailure(Call call, IOException e) {
            try {
                Thread.sleep(60 * 1000);
                Log.e("Tag", " onFailure");
            } catch (InterruptedException e1) {
                e.printStackTrace();
            }
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            try {
                Thread.sleep(60 * 1000);
                Log.e("Tag", " onResponse");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    };
}
