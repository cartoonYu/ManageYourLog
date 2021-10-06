package org.manageyourlogcommon.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/06 17:54
 */
public class DateUtil {

    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

    public static String convertToString(LocalDateTime localDateTime){
        return ofNullable(localDateTime).map(sourceData -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
            return dateTimeFormatter.format(sourceData);
        }).orElse(null);
    }

    public static LocalDateTime convertToLocalDateTime(String timeString){
        return ofNullable(timeString).map(time -> {
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateTimeFormat);
            return LocalDateTime.parse(time, dateTimeFormatter);
        }).orElse(null);
    }
}
