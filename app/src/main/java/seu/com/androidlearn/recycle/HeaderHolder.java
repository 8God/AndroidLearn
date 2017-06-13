package seu.com.androidlearn.recycle;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/13.
 */

public class HeaderHolder extends BaseViewHolder {

    @BindView(R.id.tvHeader)
    TextView tvHeader;

    public HeaderHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_header, parent, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    void render(ItemEntity entity) {
        tvHeader.setText(entity.getName());
    }
}
