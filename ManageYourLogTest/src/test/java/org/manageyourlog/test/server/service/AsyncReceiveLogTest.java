package org.manageyourlog.test.server.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.LocalDateTime;

/**
 * @author cartoon
 * @date 2021/11/17 23:36
 */
public class AsyncReceiveLogTest extends BaseTest {

    @Autowired
    @Qualifier("asyncReceiveLog")
    private UploadLog uploadLog;

    private static UploadLogRecordReq uploadLogRecordReq;

    @Test
    public void testUploadSingleLogNormal(){
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        UploadLogResp<Boolean> uploadRes = uploadLog.upload(uploadLogRecordReq);
        Assertions.assertFalse(uploadRes.isHasAbnormal());
        Assertions.assertTrue(uploadRes.getSuccessResult());
    }

    @Test
    public void testUploadLogListNormal(){
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
        UploadLogResp<Boolean> uploadRes = uploadLog.upload(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertFalse(uploadRes.isHasAbnormal());
        Assertions.assertTrue(uploadRes.getSuccessResult());
    }

    @Test
    public void testUploadSingleLogWithoutUploadTime(){
        UploadLogResp<Boolean> uploadRes = uploadLog.upload(uploadLogRecordReq);
        Assertions.assertTrue(uploadRes.isHasAbnormal());
        Assertions.assertNull(uploadRes.getSuccessResult());
    }

    @Test
    public void testUploadLogListWithoutUploadTime(){
        UploadLogResp<Boolean> uploadRes = uploadLog.upload(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertTrue(uploadRes.isHasAbnormal());
        Assertions.assertNull(uploadRes.getSuccessResult());
    }

    @BeforeEach
    public void init(){
        uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
    }
}
