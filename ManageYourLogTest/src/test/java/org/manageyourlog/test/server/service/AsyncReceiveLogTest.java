package org.manageyourlog.test.server.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.server.service.receive.ReceiveLog;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2021/11/17 23:36
 */
@DisplayName("async receive log test")
public class AsyncReceiveLogTest extends BaseTest {

    @Autowired
    @Qualifier("asyncReceiveLog")
    private ReceiveLog receiveLog;

    private static UploadLogRecordReq uploadLogRecordReq;

    @DisplayName("receive single log normal test")
    @Test
    public void testReceiveSingleLogNormal(){
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        UploadLogResp<Boolean> uploadRes = receiveLog.receive(uploadLogRecordReq);
        Assertions.assertFalse(uploadRes.hasAbnormal());
        Assertions.assertTrue(uploadRes.successResult());
    }

    @DisplayName("receive log list normal test")
    @Test
    public void testReceiveLogListNormal(){
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        UploadLogResp<Boolean> uploadRes = receiveLog.receive(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertFalse(uploadRes.hasAbnormal());
        Assertions.assertTrue(uploadRes.successResult());
    }

    @DisplayName("receive single log using non upload time test")
    @Test
    public void testReceiveSingleLogWithoutUploadTime(){
        UploadLogResp<Boolean> uploadRes = receiveLog.receive(uploadLogRecordReq);
        Assertions.assertTrue(uploadRes.hasAbnormal());
        Assertions.assertNull(uploadRes.successResult());
    }

    @DisplayName("receive log list using non upload time test")
    @Test
    public void testReceiveLogListWithoutUploadTime(){
        UploadLogResp<Boolean> uploadRes = receiveLog.receive(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertTrue(uploadRes.hasAbnormal());
        Assertions.assertNull(uploadRes.successResult());
    }

    @BeforeEach
    public void init(){
        uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
    }
}
