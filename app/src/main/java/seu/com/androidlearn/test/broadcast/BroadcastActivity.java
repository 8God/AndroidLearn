package seu.com.androidlearn.test.broadcast;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/26.
 */

public class BroadcastActivity extends AppCompatActivity {
    My2Broadcast broadcast;
    class My2Broadcast extends BroadcastReceiver {
        public My2Broadcast() {
            Log.e("Tag", "constructor: " + this);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("Tag", this + ":" + context);
        }
    }
    @BindView(R.id.btnSend)
    Button btnSend;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, BroadcastActivity.class);
        activity.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcast);
        ButterKnife.bind(this);
        init();
    }

    @OnClick(R.id.btnSend)
    public void onClick() {
        sendBroadcast(new Intent("com.seu.cc.test"));
    }

    private void init() {
        broadcast = new My2Broadcast();
        IntentFilter fileter = new IntentFilter("com.seu.cc.test");
        registerReceiver(broadcast, fileter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(broadcast);
        super.onDestroy();
    }
}
