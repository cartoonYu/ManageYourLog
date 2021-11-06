package org.manageyourlog.test.server.biz;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlog.server.biz.LogRecordBiz;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cartoon
 * @date 2021/10/23 18:32
 */
public class LogRecordBizTest extends BaseTest {

    @Autowired
    private LogRecordBiz logRecordBiz;

    @Test
    public void testSaveRecordNormal(){
        LogRecord logRecord = DefineModelUtil.defineLogRecord();
        boolean saveRes = logRecordBiz.saveRecord(logRecord);
        Assertions.assertTrue(saveRes);
    }

    @Test
    public void testSaveRecordListNormal(){
        LogRecord logRecord = DefineModelUtil.defineLogRecord();
        boolean saveRes = logRecordBiz.saveRecord(ImmutableList.of(logRecord));
        Assertions.assertTrue(saveRes);
    }

    @Test
    public void testSaveRecordWithEmptyObject(){
        boolean saveRes = logRecordBiz.saveRecord(ImmutableList.of());
        Assertions.assertFalse(saveRes);
    }

}
