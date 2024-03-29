package org.manage.log.common.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.manage.log.common.model.log.constants.LogRecordIndexSort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @since 2022/10/23 23:30
 */
@DisplayName("log record index sort enum test")
public class LogRecordIndexSortTest {

    @DisplayName("[normal] test code is not null and in range")
    @Test
    @Order(1)
    public void testCodeIsNotNullAndInRange(){
        List<Long> sortIdList = getSortId();
        sortIdList.forEach(sortId -> {
            Assertions.assertNotNull(sortId);
            Assertions.assertTrue(1 <= sortId && sortId <= 1000, "code must between 100 and 1000");
        });
    }

    @DisplayName("[normal] test description is not null")
    @Test
    @Order(1)
    public void testMsgIsNotNull(){
        List<String> descriptionList = getDescription();
        descriptionList.forEach(Assertions::assertNotNull);
    }

    @DisplayName("[normal] test parse")
    @Test
    @Order(1)
    public void testParse(){
        List<String> descriptionList = getDescription();
        descriptionList.forEach(description -> {
            Assertions.assertNotNull(description);
            Assertions.assertNotNull(LogRecordIndexSort.parse(description));
        });
    }

    @DisplayName("[abnormal] test parse when param is null")
    @Test
    @Order(2)
    public void testParseWhenParamIsNull(){
        Assertions.assertNull(LogRecordIndexSort.parse(null));
    }

    @DisplayName("[abnormal] test parse when param is null")
    @Test
    @Order(2)
    public void testParseWhenParamIsOutOfCondition(){
        Assertions.assertNull(LogRecordIndexSort.parse("mock"));
    }

    private List<Long> getSortId(){
        return Arrays.stream(LogRecordIndexSort.values()).map(LogRecordIndexSort::getSortId).collect(Collectors.toList());
    }

    private List<String> getDescription(){
        return Arrays.stream(LogRecordIndexSort.values()).map(LogRecordIndexSort::getSortDescription).collect(Collectors.toList());
    }
}
