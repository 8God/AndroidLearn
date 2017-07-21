package seu.com.modulea.network;

import java.util.Collections;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import seu.com.baseandroidlib.imageloader.ImageLoader;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public class OkHttpUtil {
    private static volatile OkHttpUtil mInstance;
    private OkHttpUtil(){}
    public static OkHttpUtil getInstance() {

        if(mInstance == null)
            synchronized (OkHttpUtil.class) {
                if (mInstance == null){
                    mInstance = new OkHttpUtil();
                }
            }
        return mInstance;
    }
    public OkHttpClient getOkHttpClient() {
        OkHttpClient client = new OkHttpClient.Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .build();
        return client;
    }
}
