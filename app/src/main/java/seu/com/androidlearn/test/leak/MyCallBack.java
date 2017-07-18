package seu.com.androidlearn.test.leak;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by wuxiangyu on 2017/6/14.
 */

public abstract class MyCallBack<T> implements okhttp3.Callback{
    private WeakReference<T> mReference;
    public MyCallBack(T context) {
        mReference = new WeakReference<T>(context);
    }
    @Override
    public void onFailure(Call call, IOException e) {
        if (mReference == null || mReference.get() == null) {
            return;
        }
        onMyFailure(call, e, mReference.get());
    }

    public abstract void onMyFailure(Call call, IOException e, T t);
    public abstract void onMyResponse(Call call, Response response, T t);
    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (mReference == null || mReference.get() == null) {
            return;
        }
        onMyResponse(call, response, mReference.get());
    }
}
