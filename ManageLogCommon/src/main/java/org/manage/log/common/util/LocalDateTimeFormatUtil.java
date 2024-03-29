package org.manage.log.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * @author cartoon
 * @version 1.
 * @since 2022/02/06 16:13
 */
public class LocalDateTimeFormatUtil {

    private static final LocalDateTimeFormatUtil INSTANCE = new LocalDateTimeFormatUtil();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LocalDateTimeFormatUtil getInstance(){
        return INSTANCE;
    }

    public String format(LocalDateTime localDateTime){
        if(Objects.isNull(localDateTime)){
            return null;
        }
        return FORMATTER.format(localDateTime);
    }

    public LocalDateTime format(String time){
        if(Objects.isNull(time)){
            return null;
        }
        return LocalDateTime.parse(time, FORMATTER);
    }

    private LocalDateTimeFormatUtil(){}
}
