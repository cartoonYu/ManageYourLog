package org.manageyourlogtest.server.dao.mysql.converter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.manageyourlogcommon.constants.LogRecordIndexSort;
import org.manageyourlogserver.dao.mysql.converter.LogRecordIndexConverter;
import org.manageyourlogserver.dao.mysql.entity.LogRecordIndexMysqlEntity;
import org.manageyourlogserver.model.LogRecordIndex;
import org.manageyourlogtest.base.BaseTest;
import org.manageyourlogtest.util.CompareObjectUtil;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/06 11:43
 */
public class LogRecordIndexConverterTest extends BaseTest {

    private static LogRecordIndex logRecordIndex;

    private static LogRecordIndexMysqlEntity logRecordIndexMysqlEntity;

    private static LocalDateTime currentTime;

    @Test
    public void testConvert(){
        LogRecordIndex res = LogRecordIndexConverter.convert(logRecordIndexMysqlEntity);
        Assertions.assertTrue(CompareObjectUtil.compare(LogRecordIndex.class, logRecordIndex, res));
    }

    @Test
    public void testConvertToMysqlEntity(){
        LogRecordIndexMysqlEntity res = LogRecordIndexConverter.convertToMysqlEntity(logRecordIndex);
        Assertions.assertTrue(CompareObjectUtil.compare(LogRecordIndexMysqlEntity.class, logRecordIndexMysqlEntity, res));
    }

    @BeforeAll
    public static void init(){
        currentTime = LocalDateTime.now();
        logRecordIndex = new LogRecordIndex();
        logRecordIndex.setIndexId(111L)
                .setLogRecordId(111L)
                .setLogRecordIndexSort(LogRecordIndexSort.Id)
                .setIndexValue("111")
                .setVersion(1)
                .setCreateTime(currentTime)
                .setModifyTime(currentTime);
        logRecordIndexMysqlEntity = new LogRecordIndexMysqlEntity();
        logRecordIndexMysqlEntity.setIndexId(111L)
                .setLogRecordId(111L)
                .setIndexSortIndexId(LogRecordIndexSort.Id.getSortId())
                .setIndexValue("111")
                .setVersion(1)
                .setCreateTime(currentTime)
                .setModifyTime(currentTime);
    }
}
