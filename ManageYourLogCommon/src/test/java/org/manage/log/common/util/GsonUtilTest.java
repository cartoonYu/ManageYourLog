package org.manage.log.common.util;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.common.model.LogRecord;

import java.util.List;

/**
 * @author cartoon
 * @since 2022/10/23 18:37
 */
@DisplayName("gson util test")
public class GsonUtilTest {

    @DisplayName("[normal] get instance")
    @Test
    public void testGetInstance(){
        Assertions.assertNotNull(GsonUtil.getInstance());
    }

    @DisplayName("[normal] write list to string")
    @Test
    public void testWriteListToString(){
        List<LogRecord> testData = ImmutableList.of(MockData.initLogRecord());
        String compareListData = listData();
        String formatListData = GsonUtil.getInstance().writeJson(testData);
        Assertions.assertEquals(compareListData, formatListData);
    }

    @DisplayName("[normal] write object to string")
    @Test
    public void testWriteObjectToString(){
        LogRecord testData = MockData.initLogRecord();
        String compareListData = objectData();
        String formatListData = GsonUtil.getInstance().writeJson(testData);
        Assertions.assertEquals(compareListData, formatListData);
    }

    @DisplayName("[normal] write string to list")
    @Test
    public void testStringToList(){
        String testData = listData();
        List<LogRecord> formatData = GsonUtil.getInstance().readJson(testData, LogRecord.class);
        Assertions.assertNotNull(formatData);
        Assertions.assertEquals(1, formatData.size());
    }

    @DisplayName("[normal] write string to object")
    @Test
    public void testStringToObject(){
        String testData = objectData();
        LogRecord formatData = GsonUtil.getInstance().readJsonObject(testData, LogRecord.class);
        Assertions.assertNotNull(formatData);
    }

    private String objectData(){
        return "{\"recordId\":\"111\",\"content\":\"test\",\"operatorSort\":\"DEFAULT\",\"operator\":\"test\",\"logRecordSort\":\"DEFAULT\",\"indexList\":[{\"indexId\":\"111\",\"logRecordId\":\"111\",\"logRecordIndexSort\":\"ID\",\"indexValue\":\"111\",\"version\":1,\"createTime\":\"2022-10-23 00:00:00\",\"modifyTime\":\"2022-10-23 00:00:00\"}],\"version\":1,\"createTime\":\"2022-10-23 00:00:00\",\"modifyTime\":\"2022-10-23 00:00:00\"}";
    }

    private String listData(){
        return "[{\"recordId\":\"111\",\"content\":\"test\",\"operatorSort\":\"DEFAULT\",\"operator\":\"test\",\"logRecordSort\":\"DEFAULT\",\"indexList\":[{\"indexId\":\"111\",\"logRecordId\":\"111\",\"logRecordIndexSort\":\"ID\",\"indexValue\":\"111\",\"version\":1,\"createTime\":\"2022-10-23 00:00:00\",\"modifyTime\":\"2022-10-23 00:00:00\"}],\"version\":1,\"createTime\":\"2022-10-23 00:00:00\",\"modifyTime\":\"2022-10-23 00:00:00\"}]";
    }
}
