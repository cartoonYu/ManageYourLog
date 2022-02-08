package org.manage.log.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/02/06 16:13
 */
public class LocalDateTimeFormatUtil {

    private static final LocalDateTimeFormatUtil INSTANCE = new LocalDateTimeFormatUtil();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTimeFormatUtil getInstance(){
        return INSTANCE;
    }

    public String format(LocalDateTime localDateTime){
        return FORMATTER.format(localDateTime);
    }

    public LocalDateTime format(String time){
        return LocalDateTime.parse(time, FORMATTER);
    }

    private LocalDateTimeFormatUtil(){}
}
