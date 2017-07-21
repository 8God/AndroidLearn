package seu.com.modulea.network;

import java.util.Collections;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public class RetrofitUtil {
    private static Retrofit mGankRetrofit;
    private static final String GANK_BASE_URL = "http://gank.io";
    static {
        init();
    }
    public static void init() {

        mGankRetrofit = new Retrofit.Builder().client(OkHttpUtil.getInstance().getOkHttpClient()).baseUrl(GANK_BASE_URL)
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static GankService getGankRetrofit() {
        return mGankRetrofit.create(GankService.class);
    }

}
