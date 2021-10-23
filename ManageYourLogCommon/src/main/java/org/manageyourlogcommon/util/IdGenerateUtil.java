package org.manageyourlogcommon.util;

/**
 * @author cartoon.yu
 * @date 2021/10/23 17:51
 */
public class IdGenerateUtil {

    public static String generate(int length){
        if(length == 0){
            return "";
        }
        StringBuilder res = new StringBuilder();
        while (length != 0){
            String nanoTimestamp = String.valueOf(System.nanoTime());
            if(length <= nanoTimestamp.length()){
                res.append(nanoTimestamp.substring(0, length));
                length = 0;
                continue;
            }
            res.append(nanoTimestamp);
            length -= nanoTimestamp.length();
        }
        return res.toString();
    }
}
