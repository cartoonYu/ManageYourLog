package org.manage.log.test.server.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.facade.model.req.UploadLogRecordReq;
import org.manage.log.facade.model.resp.OperateLogResp;
import org.manage.log.server.receive.service.ReceiveLog;
import org.manage.log.test.base.BaseTest;
import org.manage.log.test.util.DefineModelUtil;
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
        OperateLogResp<Boolean> uploadRes = receiveLog.receive(uploadLogRecordReq);
        Assertions.assertFalse(uploadRes.isHasAbnormal());
        Assertions.assertTrue(uploadRes.getSuccessResult());
    }

    @DisplayName("receive log list normal test")
    @Test
    public void testReceiveLogListNormal(){
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        OperateLogResp<Boolean> uploadRes = receiveLog.receive(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertFalse(uploadRes.isHasAbnormal());
        Assertions.assertTrue(uploadRes.getSuccessResult());
    }

    @DisplayName("receive single log using non upload time test")
    @Test
    public void testReceiveSingleLogWithoutUploadTime(){
        OperateLogResp<Boolean> uploadRes = receiveLog.receive(uploadLogRecordReq);
        Assertions.assertTrue(uploadRes.isHasAbnormal());
        Assertions.assertNull(uploadRes.getSuccessResult());
    }

    @DisplayName("receive log list using non upload time test")
    @Test
    public void testReceiveLogListWithoutUploadTime(){
        OperateLogResp<Boolean> uploadRes = receiveLog.receive(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertTrue(uploadRes.isHasAbnormal());
        Assertions.assertNull(uploadRes.getSuccessResult());
    }

    @BeforeEach
    public void init(){
        uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
    }
}
