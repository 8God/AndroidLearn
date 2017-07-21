package seu.com.baseandroidlib.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.Collections;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public class ImageLoader {
    private static Context mContext;
    private volatile static boolean hasInit = false;
    private  static Picasso mPicasso;

    private static volatile ImageLoader mInstance;
    public static ImageLoader getInstance(){
        if(mInstance == null)
            synchronized (ImageLoader.class) {
                if (mInstance == null){
                    mInstance = new ImageLoader();
                }
            }
        return mInstance;
    }

    private ImageLoader() {
    }

    public static void init(Context context) {//由于是多是在组件中初始化的，所以很蛋疼。
        mContext = context;
        hasInit = true;
        OkHttpClient client = new OkHttpClient.Builder()
                .protocols(Collections.singletonList(Protocol.HTTP_1_1))
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();

        mPicasso = new Picasso.Builder(mContext).downloader(new OKHttp3Downloader(client)).build();
        Picasso.setSingletonInstance(mPicasso);
    }

    public void loadView(String url, ImageView imageView) {
        if (!hasInit) {
            throw new IllegalStateException("ImageLoader not init");
        }
        mPicasso.with(mContext).load(url).into(imageView);
    }
}
