package seu.com.modulea.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import seu.com.modulea.R;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public class GankFragment extends Fragment {

    public static GankFragment getInstance() {
        GankFragment fragment = new GankFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modulea_fragment_gank, null);
        return view;
    }
}
