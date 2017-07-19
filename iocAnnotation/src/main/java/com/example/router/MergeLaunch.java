package com.example.router;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by wuxiangyu on 2017/7/19.
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)
public @interface MergeLaunch {
}
