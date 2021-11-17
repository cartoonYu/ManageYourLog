package org.manageyourlog.test.server.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.facade.service.ActualUploadLog;
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
    private ActualUploadLog uploadLog;

    private static UploadLogRecordReq uploadLogRecordReq;

    @Test
    public void testUploadSingleLogNormal(){
        UploadLogResp<Boolean> uploadRes = uploadLog.upload(uploadLogRecordReq);
        Assertions.assertFalse(uploadRes.isHasAbnormal());
        Assertions.assertTrue(uploadRes.getSuccessResult());
    }

    @Test
    public void testUploadLogListNormal(){
        UploadLogResp<Boolean> uploadRes = uploadLog.upload(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertFalse(uploadRes.isHasAbnormal());
        Assertions.assertTrue(uploadRes.getSuccessResult());
    }

    @BeforeAll
    public static void init(){
        uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
    }
}
