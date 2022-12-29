package org.manage.log.receive.provider.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.base.test.BaseTest;
import org.manage.log.receive.facade.dto.OperateLogResp;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.manage.log.receive.provider.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/6 20:26
 */
@DisplayName("async receive log test")
public class AsyncReceiveLogTest extends BaseTest {

    @Autowired
    @Qualifier("asyncReceiveLog")
    private ReceiveLog receiveLog;

    private static UploadLogRecordReq uploadLogRecordReq;

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
        uploadLogRecordReq.setUploadTime(null);
    }
}