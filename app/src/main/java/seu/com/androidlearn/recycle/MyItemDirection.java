package seu.com.androidlearn.recycle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/20.
 */

public class MyItemDirection extends RecyclerView.ItemDecoration {
    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;
    private Drawable mDivider;
    private int mOrientation = VERTICAL_LIST;
    public MyItemDirection(Context context, int orientation) {
        mDivider = context.getResources().getDrawable(R.color.C_C6B697);

    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + 10;
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
            Log.e("Tag", "left: " + left + ", right: " + right + ", top: " + top + ", bottom: " + bottom + ", child: " + child);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(0, 0, 0, 10);
    }
}
