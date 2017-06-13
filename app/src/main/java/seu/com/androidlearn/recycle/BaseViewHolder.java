package seu.com.androidlearn.recycle;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wuxiangyu on 2017/6/13.
 */

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {
    public BaseViewHolder(ViewGroup parent) {
        super(parent);
    }
    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    abstract void render(ItemEntity entity);
}
