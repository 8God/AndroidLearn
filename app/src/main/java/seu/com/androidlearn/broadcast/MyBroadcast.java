package seu.com.androidlearn.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by wuxiangyu on 2017/6/26.
 */

public class MyBroadcast extends BroadcastReceiver {
    public MyBroadcast() {
        Log.e("Tag", "constructor: "+ this);
    }
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Tag", this + ":" + context);
    }
}
