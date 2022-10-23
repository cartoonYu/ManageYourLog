package org.manage.log.common.constants;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author cartoon
 * @since 2022/10/23 23:14
 */
@DisplayName("handle error enum test")
public class HandleErrorTest {

    @DisplayName("[normal] test code is not null and in range")
    @Test
    public void testCodeIsNotNullAndInRange(){
        List<Integer> codeList = getCode();
        codeList.forEach(code -> {
            Assertions.assertNotNull(code);
            Assertions.assertTrue(100 <= code && code <= 1000, "code must between 100 and 1000");
        });
    }

    @DisplayName("[normal] test msg is not null")
    @Test
    public void testMsgIsNotNull(){
        List<String> msgList = getMsg();
        msgList.forEach(Assertions::assertNotNull);
    }

    private List<Integer> getCode(){
        return Arrays.stream(HandleError.values()).map(HandleError::getCode).collect(Collectors.toList());
    }

    private List<String> getMsg(){
        return Arrays.stream(HandleError.values()).map(HandleError::getMsg).collect(Collectors.toList());
    }
}
