package seu.com.baseandroidlib.imageloader;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public class ImageLoader {
    private static Context mContext;
    private volatile static boolean hasInit = false;
    private Picasso mPicasso;

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
    }

    public void loadView(String url, ImageView imageView) {
        if (!hasInit) {
            throw new IllegalStateException("ImageLoader not init");
        }
        Picasso.with(mContext).load(url).into(imageView);
    }
}
