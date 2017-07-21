package seu.com.modulea.gank.fuli;

import java.util.List;

import seu.com.modulea.gank.fuli.GankFuliContract.Presenter;
import seu.com.modulea.network.API;
import seu.com.modulea.network.GankCallBack;
import seu.com.modulea.network.RetrofitUtil;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public class GankFuliPresenter implements Presenter {
    private GankFuliContract.View mView;

    public GankFuliPresenter(GankFuliContract.View view) {
        this.mView = view;
    }

    @Override
    public void getDataFromWeb() {
        API.enqueue(RetrofitUtil.getGankRetrofit().getFuliData(10, 1), new GankCallBack<List<FuliEntity>>() {
            @Override
            public void onSuccess(List<FuliEntity> fuliEntities) {
                mView.updateView(fuliEntities);
            }

            @Override
            public void onFailed(Exception e) {

            }
        });
    }
}
