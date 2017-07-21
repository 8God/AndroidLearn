package seu.com.modulea.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import seu.com.modulea.gank.fuli.FuliEntity;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public interface GankService {
    @GET("/api/data/福利/{pageSize}/{pageNum}")
    Call<GankResponse<List<FuliEntity>>> getFuliData(@Path("pageSize") int pageSize, @Path("pageNum") int pageNum);
}
