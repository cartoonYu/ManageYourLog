package org.manageyourlog.test.server.converter;

import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manageyourlog.common.util.ReadJsonUtil;
import org.manageyourlog.server.converter.repository.MockDataConverter;
import org.manageyourlog.server.dao.mock.LogRecordIndexMockEntity;
import org.manageyourlog.server.dao.mock.LogRecordMockEntity;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.test.base.BaseTest;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/27 09:20
 */
@DisplayName("mock data converter test")
public class MockDataConverterTest extends BaseTest {

    private static List<LogRecordMockEntity> mockLogRecord;

    private static List<LogRecordIndexMockEntity> mockLogRecordIndex;

    @DisplayName("convert test")
    @Test
    public void testConvert(){
        List<LogRecord> logRecords = MockDataConverter.convert(mockLogRecord, mockLogRecordIndex);
        Assertions.assertNotNull(logRecords);
        Assertions.assertEquals(1, logRecords.size());
    }

    @BeforeAll
    public static void init(){
        JSONArray logRecordJsonArray = ReadJsonUtil.readJsonArray("logRecordData.json");
        mockLogRecord =  logRecordJsonArray.toJavaList(LogRecordMockEntity.class);
        JSONArray logRecordIndexJsonArray = ReadJsonUtil.readJsonArray("logRecordIndexData.json");
        mockLogRecordIndex = logRecordIndexJsonArray.toJavaList(LogRecordIndexMockEntity.class);

    }
}
