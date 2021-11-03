package org.manageyourlogtest.facade;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlogfacade.UploadLog;
import org.manageyourlogfacade.model.req.UploadLogRecordReq;
import org.manageyourlogfacade.model.resp.UploadLogResp;
import org.manageyourlogtest.base.BaseTest;
import org.manageyourlogtest.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/03 09:24
 */
public class UploadLogTest extends BaseTest {

    @Autowired
    @Qualifier("syncUploadLog")
    private UploadLog uploadLog;

    @Test
    public void testUploadLog(){
        UploadLogRecordReq req = DefineModelUtil.defineLogRecordReq();
        UploadLogResp<Boolean> res = uploadLog.upload(req);
        Assertions.assertFalse(res.isHasAbnormal());
        Assertions.assertTrue(res.getSuccessResult());
    }

    @Test
    public void testUploadLogList(){
        UploadLogRecordReq req = DefineModelUtil.defineLogRecordReq();
        UploadLogResp<Boolean> res = uploadLog.upload(ImmutableList.of(req));
        Assertions.assertFalse(res.isHasAbnormal());
        Assertions.assertTrue(res.getSuccessResult());
    }
}
