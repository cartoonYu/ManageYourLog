package org.manageyourlog.test.server.controller;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;

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
        String result = post("/receiveSingleLog", uploadLogRecordReq);
        Assertions.assertNotNull(result);
    }

    @DisplayName("receive log list test")
    @Test
    public void testReceiveLogList() throws Exception{
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        String result = post("/receiveLogList", ImmutableList.of(uploadLogRecordReq));
        Assertions.assertNotNull(result);
    }
}
