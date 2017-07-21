package seu.com.modulea.gank.fuli;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import seu.com.modulea.R;
import seu.com.modulea.R2;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public class GankFuliFragment extends Fragment implements GankFuliContract.View {

    @BindView(R2.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R2.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    GankFuliAdapter mGankFuliAdapter;
    GankFuliContract.Presenter mPresenter;
    public static GankFuliFragment getInstance() {
        GankFuliFragment fragment = new GankFuliFragment();
//        Bundle bundle = new Bundle();
//        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modulea_fragment_gank_fuli, null);
        ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        mPresenter = new GankFuliPresenter(this);
        mGankFuliAdapter = new GankFuliAdapter(getActivity());
        recyclerView.setAdapter(mGankFuliAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.getDataFromWeb();
            }
        });
        mPresenter.getDataFromWeb();
    }


    @Override
    public void updateView(List<FuliEntity> fuliEntities) {
        mGankFuliAdapter.setData(fuliEntities);
        mSwipeRefreshLayout.setRefreshing(false);
    }

}
