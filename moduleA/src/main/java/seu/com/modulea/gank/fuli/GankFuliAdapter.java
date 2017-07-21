package seu.com.modulea.gank.fuli;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import seu.com.baseandroidlib.imageloader.ImageLoader;
import seu.com.modulea.R;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public class GankFuliAdapter extends RecyclerView.Adapter<GankFuliAdapter.ViewHolder> {
    private Context mContext;

    public GankFuliAdapter(Context context) {
        this.mContext = context;
    }
    private List<FuliEntity> mDataList = new ArrayList<>();

    public void appendData(List<FuliEntity> list) {
        if (list != null) {
            mDataList.addAll(list);
            notifyDataSetChanged();
        }
    }
    public void setData(List<FuliEntity> list) {
        if (list != null) {
            mDataList = list;
            notifyDataSetChanged();
        }
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.modulea_item_gank_fuli, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FuliEntity entity = mDataList.get(position);
        ImageLoader.getInstance().loadView("https://img6.bdstatic.com/img/image/smallpic/weijupaishexiaochongzi.jpg", holder.mIvPic);
        holder.mTvAuthor.setText(entity.getWho());
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mIvPic;
        private TextView mTvAuthor;
        public ViewHolder(View itemView) {
            super(itemView);
            mIvPic = (ImageView) itemView.findViewById(R.id.ivPic);
            mTvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
        }
    }
}
