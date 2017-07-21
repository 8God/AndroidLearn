package seu.com.modulea.gank.fuli;

import java.util.List;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public class GankFuliContract {
    interface View {
        void updateView(List<FuliEntity> fuliEntities);
    }
    interface Presenter {
        void getDataFromWeb();
    }
}
