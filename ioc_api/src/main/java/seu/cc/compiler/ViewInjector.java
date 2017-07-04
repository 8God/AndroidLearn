package seu.cc.compiler;

/**
 * Created by wuxiangyu on 2017/7/4.
 */

public interface ViewInjector<T> {
    void inject(T t, Object source);
}
