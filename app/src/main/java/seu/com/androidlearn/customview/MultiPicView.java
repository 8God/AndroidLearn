package seu.com.androidlearn.customview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/9.
 */

public class MultiPicView extends View {
    private int[] mImgId = {R.drawable.splash_bg, R.drawable.splash_bg, R.drawable.splash_bg};
    private Bitmap[] mCards = new Bitmap[3];

    public MultiPicView(Context context) {
        this(context, null);
    }

    public MultiPicView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MultiPicView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Bitmap temp = null;
        for (int i = 0; i < 3; i++) {
            temp = BitmapFactory.decodeResource(getResources(), mImgId[i]);
            mCards[i] = Bitmap.createScaledBitmap(temp, 400, 600, false);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.save();//save：锁画布，保存当前已经画了的pic
        canvas.translate(20, 120);
//        for (Bitmap bitmap : mCards) {
//            canvas.translate(120, 0);
//            canvas.drawBitmap(bitmap, 0, 0, null);
//        }

        for (int i = 0; i < mCards.length; i++) {
            canvas.translate(120, 0);
            canvas.save();
            if (i < mCards.length - 1) {
                canvas.clipRect(0, 0, 120, mCards[i].getHeight());
            }
            canvas.drawBitmap(mCards[i], 0, 0, null);
            canvas.restore();
        }
        canvas.restore();//返回到上一个save状态，其实就是栈的操作。
    }
}
