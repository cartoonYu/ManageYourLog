package org.manage.log.common.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/10/21 00:10
 */
@DisplayName("local date time format util test")
public class LocalDateTimeFormatUtilTest {

    @Test
    @DisplayName("[normal] get instance")
    public void testGetInstance(){
        LocalDateTimeFormatUtil localDateTimeFormatUtil = LocalDateTimeFormatUtil.getInstance();
        Assertions.assertNotNull(localDateTimeFormatUtil);
    }

    @Test
    @DisplayName("[normal] format to string")
    public void testFormatToString(){
        LocalDateTime localDateTime = LocalDateTime.of(2022, 10, 21, 0, 15, 0);
        String compareString = "2022-10-21 00:15:00";
        String formatString = LocalDateTimeFormatUtil.getInstance().format(localDateTime);
        Assertions.assertEquals(compareString, formatString);
    }

    @Test
    @DisplayName("[abnormal] format to string when param is null")
    public void testFormatToStringParamIsNull(){
        LocalDateTime localDateTime = null;
        Assertions.assertNull(LocalDateTimeFormatUtil.getInstance().format(localDateTime));
    }

    @Test
    @DisplayName("[normal] format to time")
    public void testFormatToTime(){
        String str = "2022-10-21 00:15:00";
        LocalDateTime compareLocalDateTime = LocalDateTime.of(2022, 10, 21, 0, 15, 0);
        LocalDateTime formatLocalDateTime = LocalDateTimeFormatUtil.getInstance().format(str);
        Assertions.assertEquals(0, compareLocalDateTime.compareTo(formatLocalDateTime));
    }

    @Test
    @DisplayName("[abnormal] format to time when param is null")
    public void testFormatToTimeParamIsNull(){
        String str = null;
        Assertions.assertNull(LocalDateTimeFormatUtil.getInstance().format(str));
    }
}
