package seu.api.compiler;

import java.util.Map;

/**
 * Created by wuxiangyu on 2017/7/18.
 */

public interface LaunchInjector {
    Class<?> getPackageName(String key);
    Map<String, Class<?>> getMap();
}
