package seu.com.androidlearn.test.dynamicviewpager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import seu.com.androidlearn.R;

/**
 * Created by wuxiangyu on 2017/6/22.
 */

public class MyFragment extends Fragment {
    private static final String TITLE = "title";
    @BindView(R.id.tvContent)
    TextView tvContent;

    public static MyFragment getInstance(String title) {
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        MyFragment fragment = new MyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            tvContent.setText(getArguments().getString(TITLE));
        } else {
            tvContent.setText("Default");
        }
        return view;
    }

    public void refresh(String title) {
        if (tvContent == null) {
            return;
        }
        tvContent.setText(title);
    }
}
