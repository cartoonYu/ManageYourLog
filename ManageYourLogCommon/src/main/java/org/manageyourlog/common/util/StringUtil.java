package org.manageyourlog.common.util;

/**
 * @author cartoon
 * @date 2021/11/14 23:59
 */
public class StringUtil {

    public static boolean stringIsEmpty(String source){
        return source == null || source.length() == 0;
    }

    public static boolean stringIsNotEmpty(String source){
        return source != null && source.length() != 0;
    }
}
