package org.manageyourlog.test.server.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.server.service.ReceiveLog;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author cartoon
 * @date 2021/10/31 16:34
 */
public class SyncReceiveLogTest extends BaseTest {

    @Autowired
    @Qualifier("syncReceiveLog")
    private ReceiveLog receiveLog;

    private static UploadLogRecordReq uploadLogRecordReq;

    @Test
    public void testUploadSingleLogNormal(){
        UploadLogResp<Boolean> uploadRes = receiveLog.receive(uploadLogRecordReq);
        Assertions.assertFalse(uploadRes.isHasAbnormal());
        Assertions.assertTrue(uploadRes.getSuccessResult());
    }

    @Test
    public void testUploadLogListNormal(){
        UploadLogResp<Boolean> uploadRes = receiveLog.receive(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertFalse(uploadRes.isHasAbnormal());
        Assertions.assertTrue(uploadRes.getSuccessResult());
    }

    @BeforeAll
    public static void init(){
        uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
    }
}
