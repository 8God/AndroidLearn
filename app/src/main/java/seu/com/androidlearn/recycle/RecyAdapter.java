package seu.com.androidlearn.recycle;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxiangyu on 2017/6/13.
 */

public class RecyAdapter extends RecyclerView.Adapter<BaseViewHolder>{
    public static final int ITEM_HEADER = 1;
    public static final int ITEM_CONTENT = 2;

    List<ItemEntity> list;

    public RecyAdapter(Context context) {
        list = new ArrayList<>();
    }
    public void setData(List<ItemEntity> list) {
        if (list == null) {
            return;
        }
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    public void append(List<ItemEntity> list) {
        list.addAll(list);
    }

    public ItemEntity getItem(int position) {
        return list.get(position);
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_HEADER) {
            return new HeaderHolder(parent);
        } else if (viewType == ITEM_CONTENT) {
            return new ContentHolder(parent);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        ItemEntity entity = getItem(position);
        holder.render(entity);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
