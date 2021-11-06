package org.manageyourlog.test.facade;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/03 09:24
 */
public class UploadLogTest extends BaseTest {

    @Autowired
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
