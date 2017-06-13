package seu.com.androidlearn.battery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import seu.com.androidlearn.R;

import static android.content.Intent.ACTION_BATTERY_CHANGED;
import static android.content.Intent.ACTION_POWER_CONNECTED;
import static android.content.Intent.ACTION_POWER_DISCONNECTED;
import static android.os.BatteryManager.BATTERY_PLUGGED_AC;
import static android.os.BatteryManager.BATTERY_PLUGGED_USB;

/**
 * Created by wuxiangyu on 2017/6/12.
 */

public class BatteryActivity extends AppCompatActivity {
    @BindView(R.id.tvBatteryInfo1)
    TextView tvBatteryInfo1;
    @BindView(R.id.tvBatteryInfo2)
    TextView tvBatteryInfo2;

    public static void launch(Activity activity) {
        Intent intent = new Intent();
        intent.setClass(activity, BatteryActivity.class);
        activity.startActivity(intent);
    }
    BroadcastReceiver batteryChargeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            showBatteryInfo();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);
        ButterKnife.bind(this);
        showBatteryInfo();
        registerBattery();
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(batteryChargeReceiver);
        super.onDestroy();
    }

    private void registerBattery() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_POWER_CONNECTED);
        filter.addAction(ACTION_POWER_DISCONNECTED);
        registerReceiver(batteryChargeReceiver, filter);
    }

    private void showBatteryInfo() {
        IntentFilter ifilter = new IntentFilter(ACTION_BATTERY_CHANGED);
        Intent batteryStatus = registerReceiver(null, ifilter);//黏性广播，可以直接使用
        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;
        int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
        boolean usbCharge = chargePlug == BATTERY_PLUGGED_USB;
        boolean acCharge = chargePlug == BATTERY_PLUGGED_AC;
        StringBuilder sb = new StringBuilder();
        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
        sb.append("is charging: ").append(isCharging).append("\nchargePlug: ").append(usbCharge ? "useCharge" : "acCharge").append(", level: " +level);
        tvBatteryInfo1.setText(sb.toString());
    }

}
