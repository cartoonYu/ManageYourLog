package org.manage.log.test.server.controller;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.facade.model.req.UploadLogRecordReq;
import org.manage.log.test.base.BaseTest;
import org.manage.log.test.util.DefineModelUtil;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/01 22:55
 */
@DisplayName("receive log controller test")
public class ReceiveLogByHttpTest extends BaseTest {

    @DisplayName("receive single log test")
    @Test
    public void testReceiveSingleLog() throws Exception{
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        String result = post("/receive/singleLog", uploadLogRecordReq);
        Assertions.assertNotNull(result);
    }

    @DisplayName("receive log list test")
    @Test
    public void testReceiveLogList() throws Exception{
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        String result = post("/receive/logList", ImmutableList.of(uploadLogRecordReq));
        Assertions.assertNotNull(result);
    }
}
