package com.example.baseandroidlib;

/**
 * Created by wuxiangyu on 2017/7/7.
 */

public class StringUtil {
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}
