package org.manageyourlogtest.server.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.manageyourlogfacade.UploadLog;
import org.manageyourlogfacade.model.req.LogRecordReq;
import org.manageyourlogfacade.model.resp.UploadLogResp;
import org.manageyourlogtest.base.BaseTest;
import org.manageyourlogtest.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author cartoon
 * @date 2021/10/31 16:34
 */
public class SyncUploadLogTest extends BaseTest {

    @Autowired
    @Qualifier("syncUploadLog")
    private UploadLog uploadLog;

    private static LogRecordReq logRecordReq;

    @Test
    public void testUploadSingleLogNormal(){
        UploadLogResp<Boolean> uploadRes = uploadLog.upload(logRecordReq);
        Assertions.assertFalse(uploadRes.isHasAbnormal());
        Assertions.assertTrue(uploadRes.getSuccessResult());
    }

    @Test
    public void testUploadLogListNormal(){
        UploadLogResp<Boolean> uploadRes = uploadLog.upload(ImmutableList.of(logRecordReq));
        Assertions.assertFalse(uploadRes.isHasAbnormal());
        Assertions.assertTrue(uploadRes.getSuccessResult());
    }

    @BeforeAll
    public static void init(){
        logRecordReq = DefineModelUtil.defineLogRecordReq();
    }
}
