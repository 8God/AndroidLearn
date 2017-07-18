package seu.com.androidlearn.test.typeface;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import static seu.com.androidlearn.test.typeface.TypefaceAfterActivity.CHANGE_TYPEFACE;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public class TypeTextView extends TextView {

    private BroadcastReceiver changeTypeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(CHANGE_TYPEFACE)) {
                Typeface typeFace = Typeface.createFromAsset(getContext().getAssets(), "fonts/wawa.ttf");
                setTypeface(null, Typeface.BOLD);//设置字体，并且加粗
//                setTypeface(typeFace);//设置字体，并且加粗
                Log.e("Tag", "onReceive");
                invalidate();
            }
        }
    };
    public TypeTextView(Context context) {
        this(context, null);
    }

    public TypeTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TypeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        IntentFilter intentFilter = new IntentFilter(CHANGE_TYPEFACE);
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(changeTypeReceiver, intentFilter);
    }

    @Override
    protected void onDetachedFromWindow() {
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(changeTypeReceiver);
        super.onDetachedFromWindow();
    }
}
