package org.manageyourlog.test.facade.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manageyourlog.facade.UploadLog;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author cartoon
 * @date 2021/12/5 23:26
 */
@DisplayName("default upload log test")
public class UploadLogByDefaultTest extends BaseTest {

    @Autowired
    @Qualifier("uploadLogByDefault")
    private UploadLog uploadLog;

    @DisplayName("upload single log by default")
    @Test
    public void testUploadSingleLog(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        UploadLogResp<Boolean> uploadLogResp = uploadLog.upload(uploadLogRecordReq);
        Assertions.assertFalse(uploadLogResp.isHasAbnormal());
        Assertions.assertTrue(uploadLogResp.getSuccessResult());
    }

    @DisplayName("upload log list by default")
    @Test
    public void testUploadLogList(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        UploadLogResp<Boolean> uploadLogResp = uploadLog.upload(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertFalse(uploadLogResp.isHasAbnormal());
        Assertions.assertTrue(uploadLogResp.getSuccessResult());
    }
}
