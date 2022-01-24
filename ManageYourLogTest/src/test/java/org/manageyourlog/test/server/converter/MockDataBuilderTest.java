package org.manageyourlog.test.server.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manageyourlog.common.util.GsonUtil;
import org.manageyourlog.common.util.ReadJsonUtil;
import org.manageyourlog.server.repository.builder.MockDataBuilder;
import org.manageyourlog.server.dao.mock.model.LogRecordIndexMockEntity;
import org.manageyourlog.server.dao.mock.model.LogRecordMockEntity;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.test.base.BaseTest;

import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/27 09:20
 */
@DisplayName("mock data converter test")
public class MockDataBuilderTest extends BaseTest {

    private static List<LogRecordMockEntity> mockLogRecord;

    private static List<LogRecordIndexMockEntity> mockLogRecordIndex;

    @DisplayName("convert test")
    @Test
    public void testConvert(){
        List<LogRecord> logRecords = MockDataBuilder.getInstance().convert(mockLogRecord, mockLogRecordIndex);
        Assertions.assertNotNull(logRecords);
        Assertions.assertEquals(1, logRecords.size());
    }

    @BeforeAll
    public static void init(){
        mockLogRecord = ReadJsonUtil.readArray("logRecordData.json", LogRecordMockEntity.class);
        mockLogRecordIndex = ReadJsonUtil.readArray("logRecordIndexData.json", LogRecordIndexMockEntity.class);
        System.out.println(GsonUtil.getInstance().writeJson(mockLogRecord));
        System.out.println(GsonUtil.getInstance().writeJson(mockLogRecordIndex));
    }
}
