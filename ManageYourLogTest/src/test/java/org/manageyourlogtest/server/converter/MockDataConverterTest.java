package org.manageyourlogtest.server.converter;

import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.manageyourlogcommon.util.ReadJsonUtil;
import org.manageyourlogserver.converter.MockDataConverter;
import org.manageyourlogserver.dao.mock.LogRecordIndexMockEntity;
import org.manageyourlogserver.dao.mock.LogRecordMockEntity;
import org.manageyourlogserver.model.LogRecord;
import org.manageyourlogtest.base.BaseTest;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/27 09:20
 */
public class MockDataConverterTest extends BaseTest {

    private static List<LogRecordMockEntity> mockLogRecord;

    private static List<LogRecordIndexMockEntity> mockLogRecordIndex;


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
