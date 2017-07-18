package seu.com.androidlearn.test.hook;

import android.view.View;
import android.widget.Toast;

import seu.com.androidlearn.MyApplication;

/**
 * Created by wuxiangyu on 2017/7/7.
 */

public class OnClickListenerProxy implements View.OnClickListener {
    View.OnClickListener listener;
    public OnClickListenerProxy(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        String viewname = MyApplication.getInstance().getResources().getResourceName(v.getId());
        Toast.makeText(MyApplication.getInstance(), viewname, Toast.LENGTH_SHORT).show();
        if (listener != null) {
            listener.onClick(v);
        }
    }
}
