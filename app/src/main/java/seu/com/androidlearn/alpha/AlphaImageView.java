package seu.com.androidlearn.alpha;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.logging.Logger;

/**
 * Created by wuxiangyu on 2017/6/6.
 */

public class AlphaImageView extends ImageView {
    public AlphaImageView(Context context) {
        this(context, null);
    }

    public AlphaImageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AlphaImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("cccc", event.getAction() + "");
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            this.setAlpha(11);
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            this.setAlpha(255);
        }
        return super.onTouchEvent(event);
    }
}
