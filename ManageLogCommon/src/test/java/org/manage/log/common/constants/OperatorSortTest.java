package org.manage.log.common.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.manage.log.common.model.log.constants.OperatorSort;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @since 2022/10/23 23:30
 */
@DisplayName("operator sort enum test")
public class OperatorSortTest {

    @DisplayName("[normal] test code is not null and in range")
    @Test
    @Order(1)
    public void testCodeIsNotNullAndInRange(){
        List<Long> sortIdList = getSortId();
        sortIdList.forEach(sortId -> {
            Assertions.assertNotNull(sortId);
            Assertions.assertTrue(0 <= sortId && sortId <= 1000, "code must between 100 and 1000");
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
            Assertions.assertNotNull(OperatorSort.parse(description));
        });
    }

    @DisplayName("[abnormal] test parse when param is null")
    @Test
    @Order(2)
    public void testParseWhenParamIsNull(){
        Assertions.assertNull(OperatorSort.parse(null));
    }

    @DisplayName("[abnormal] test parse when param is null")
    @Test
    @Order(2)
    public void testParseWhenParamIsOutOfCondition(){
        Assertions.assertNull(OperatorSort.parse("mock"));
    }

    private List<Long> getSortId(){
        return Arrays.stream(OperatorSort.values()).map(OperatorSort::getSortId).collect(Collectors.toList());
    }

    private List<String> getDescription(){
        return Arrays.stream(OperatorSort.values()).map(OperatorSort::getSortDescription).collect(Collectors.toList());
    }
}
