package seu.com.androidlearn.recycle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;


import seu.com.androidlearn.R;

/**
 * 感觉还是很强大的
 * Created by wuxiangyu on 2017/6/30.
 */

public class StickItemDirection extends RecyclerView.ItemDecoration{
    private int mGroupHeight = 80;  //悬浮栏高度
    private int mLeftMargin = 10;   //文字距离左边的距离
    private int mTextSize = 40;     //字体大小
    private Drawable mDivider;
    private static final int HEIGHT = 40;
    private Paint mGroutPaint;
    private int mGroupBackground = Color.GRAY;//group背景色，默认灰色
    private int mGroupTextColor = Color.WHITE;//字体颜色，默认白色
    private TextPaint mTextPaint;
    public StickItemDirection(Context context) {
        mDivider = context.getResources().getDrawable(R.color.colorPrimary);
        mGroutPaint = new Paint();
        mGroutPaint.setColor(mGroupBackground);

        mTextPaint = new TextPaint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mGroupTextColor);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
    }

    /**
     * 装饰进行绘画
     * @param c
     * @param parent
     * @param state
     */
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
//        int left = parent.getPaddingLeft();
//        int right = parent.getWidth() - parent.getPaddingRight();
//        final int childCount = parent.getChildCount();
//        for (int i = 0; i < childCount; i++) {
//            final View child = parent.getChildAt(i);
//            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
//            final int bottom = child.getTop() + params.topMargin;
//            final int top = bottom - HEIGHT;
//            mDivider.setBounds(left, top, right, bottom);
//            mDivider.draw(c);
//        }



        final int itemCount = state.getItemCount();
        final int childCount = parent.getChildCount();
        final int left = parent.getLeft() + parent.getPaddingLeft();
        final int right = parent.getRight() - parent.getPaddingRight();
        //标记上一个item对应的Group
        String preGroupName;

        //当前item对应的Group
        String currentGroupName = null;

        for (int i = 0; i < childCount; i++) {
            View view = parent.getChildAt(i);
            int position = parent.getChildAdapterPosition(view);
            preGroupName = currentGroupName;
            currentGroupName = getGroupName(position);
            if (currentGroupName == null || TextUtils.equals(currentGroupName, preGroupName))
                continue;
            int viewBottom = view.getBottom();
            //top 决定当前顶部第一个悬浮Group的位置
            float top = Math.max(HEIGHT, view.getTop());
            if (position + 1 < itemCount) {
                //获取下个GroupName
                String nextGroupName = getGroupName(position + 1);
                //下一组的第一个View接近头部
                if (!currentGroupName.equals(nextGroupName) && viewBottom < top) {
                    top = viewBottom;
                }
            }
            //根据top绘制group
            c.drawRect(left, top - HEIGHT, right, top, mGroutPaint);
            Paint.FontMetrics fm = mTextPaint.getFontMetrics();
            //文字竖直居中显示
            float baseLine = top - (mGroupHeight - (fm.bottom - fm.top)) / 2 - fm.bottom;
            c.drawText(currentGroupName, left + mLeftMargin, baseLine, mTextPaint);
//            view.draw(c);
        }

        Log.e("Tag", "onDraw：" + "itemCount: "+ itemCount + " ;childCount: "+ childCount);
    }

    /**
     * 给装饰留下空间
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int pos = parent.getChildAdapterPosition(view);
        String groupId = getGroupName(pos);
        if (groupId == null) return;
        //只有是同一组的第一个才显示悬浮栏
        if (isFirstInGroup(pos)) {
            outRect.top = HEIGHT;
        }
        Log.e("Tag", "getItemOffsets: " + isFirstInGroup(pos));
    }

    private boolean isFirstInGroup(int position) {
        if (position == 0) {
            return true;
        } else {
            String preGroupId = getGroupName(position - 1);
            String groupId = getGroupName(position);
            if (TextUtils.isEmpty(preGroupId)) {
                return false;
            } else {
                return !preGroupId.equals(groupId);
            }
        }
    }

    private GroupListener mListener;

    public void setGroupListener(GroupListener listener) {
        this.mListener = listener;
    }
    public String getGroupName(int position) {
        if (mListener != null) {
            return mListener.getGroupName(position);
        } else {
            return null;
        }
    }
}

