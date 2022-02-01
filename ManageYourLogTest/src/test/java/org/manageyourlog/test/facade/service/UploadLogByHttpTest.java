package org.manageyourlog.test.facade.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manageyourlog.facade.service.http.UploadLogInterface;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.facade.service.http.UploadLogByHttp;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/20 22:05
 */
@DisplayName("upload log by http test")
public class UploadLogByHttpTest extends BaseTest{

    @Mock
    private UploadLogInterface uploadLogInterface;

    @InjectMocks
    @Autowired
    private UploadLogByHttp uploadLog;

    @DisplayName("upload single log by http")
    @Test
    public void testUploadSingleLog(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        Mockito.when(uploadLogInterface.uploadSingleLog(ArgumentMatchers.eq(uploadLogRecordReq))).thenReturn(DefineModelUtil.defineHttpResponse(new UploadLogResp<>(true)));
        UploadLogResp<Boolean> uploadLogResp = uploadLog.upload(uploadLogRecordReq);
        Assertions.assertFalse(uploadLogResp.isHasAbnormal());
        Assertions.assertTrue(uploadLogResp.getSuccessResult());
    }

    @DisplayName("upload log list by http")
    @Test
    public void testUploadLogList(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        Mockito.when(uploadLogInterface.uploadLogList(ArgumentMatchers.eq(ImmutableList.of(uploadLogRecordReq)))).thenReturn(DefineModelUtil.defineHttpResponse(new UploadLogResp<>(true)));
        UploadLogResp<Boolean> uploadLogResp = uploadLog.upload(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertFalse(uploadLogResp.isHasAbnormal());
        Assertions.assertTrue(uploadLogResp.getSuccessResult());
    }
}
