package seu.com.modulea.network;

/**
 * Created by wuxiangyu on 2017/7/21.
 */

public interface GankCallBack<T> {
    public abstract void onSuccess(T t);
    public abstract void onFailed(Exception e);

}
