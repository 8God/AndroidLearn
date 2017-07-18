package seu.com.androidlearn.test.recycle;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/13.
 */

public class ContentHolder extends BaseViewHolder {
    @BindView(R.id.tvContent)
    TextView tvContent;

    public ContentHolder(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycle_content, parent, false));
        ButterKnife.bind(this, itemView);
    }

    @Override
    void render(ItemEntity entity) {
        tvContent.setText(entity.getName());
    }
}
