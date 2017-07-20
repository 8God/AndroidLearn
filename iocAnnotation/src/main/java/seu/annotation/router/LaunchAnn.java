package seu.annotation.router;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wuxiangyu on 2017/7/3.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface LaunchAnn {
    String value();
}