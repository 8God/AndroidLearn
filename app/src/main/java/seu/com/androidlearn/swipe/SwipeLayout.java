package seu.com.androidlearn.swipe;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wuxiangyu on 2017/6/13.
 */

public class SwipeLayout extends ViewGroup {
    private View mViewContent;
    private View mViewHide;

    float lastTouchX;
    float lastTouchY;
    public SwipeLayout(Context context) {
        this(context, null);
    }

    public SwipeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SwipeLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        mViewContent = getChildAt(0);
        mViewHide = getChildAt(1);
        for (int i =0; i < childCount; i++) {
            measureChild(getChildAt(i), widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int left = 0;
        int top = 0;
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            int childWidth = child.getMeasuredWidth();
            int childHeight = child.getMeasuredHeight();
            child.layout(left, top, left + childWidth, top + childHeight);
            left = left + childWidth;
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastTouchX = x;
                lastTouchY = y;
                Log.e("Tag", "lastTouchX： " + lastTouchX);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("Tag", "lastTouchX： " + lastTouchX + ", x :" + x + ", getScrollX: "+ getScrollX());
                int delta = (int) (lastTouchX - x);
                //加了scroller就没有这种问题了
                //delta >0 表示左滑
                if (delta < 0 && getScrollX() <= 0) {
                    scrollTo(0, 0);//一定要滚动，content会显示在屏幕往右
                    break;
                }
                if (delta > 0 && getScrollX() > mViewHide.getWidth()) {
                    break;
                }
                if (Math.abs(lastTouchX - x) > Math.abs(lastTouchY - y)) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
//                else {
//                    getParent().requestDisallowInterceptTouchEvent(false);
//                }
                scrollBy((int) (lastTouchX - x), 0);

                lastTouchX = x;
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                getParent().requestDisallowInterceptTouchEvent(false);
                break;
            default:
                break;
        }
        return true;

    }
}
