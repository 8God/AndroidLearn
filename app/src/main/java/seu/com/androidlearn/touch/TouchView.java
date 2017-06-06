package seu.com.androidlearn.touch;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by wuxiangyu on 2017/6/6.
 */

public class TouchView extends Button {
    private static final String TAG = TouchView.class.getSimpleName();

    public TouchView(Context context) {
        this(context, null);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TouchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);//这里的context，说白了就是Activity
        Log.e("TouchView", "context: " + context);
        setClickable(true);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "dispatchTouchEvent-----ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "dispatchTouchEvent-----ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "dispatchTouchEvent-----ACTION_UP");
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(event);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.e(TAG, "onTouchEvent-----ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "onTouchEvent-----ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e(TAG, "onTouchEvent-----ACTION_UP");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }
}
