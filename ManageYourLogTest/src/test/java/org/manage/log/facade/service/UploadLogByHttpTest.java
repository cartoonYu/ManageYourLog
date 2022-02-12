package org.manage.log.facade.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.facade.model.resp.OperateLogResp;
import org.manage.log.facade.service.http.UploadLogInterface;
import org.manage.log.facade.model.req.UploadLogRecordReq;
import org.manage.log.facade.service.http.UploadLogByHttp;
import org.manage.log.base.BaseTest;
import org.manage.log.util.DefineModelUtil;
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
        Mockito.when(uploadLogInterface.uploadSingleLog(ArgumentMatchers.eq(uploadLogRecordReq))).thenReturn(DefineModelUtil.defineHttpResponse(new OperateLogResp<>(true)));
        OperateLogResp<Boolean> operateLogResp = uploadLog.upload(uploadLogRecordReq);
        Assertions.assertFalse(operateLogResp.isHasAbnormal());
        Assertions.assertTrue(operateLogResp.getSuccessResult());
    }

    @DisplayName("upload log list by http")
    @Test
    public void testUploadLogList(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        Mockito.when(uploadLogInterface.uploadLogList(ArgumentMatchers.eq(ImmutableList.of(uploadLogRecordReq)))).thenReturn(DefineModelUtil.defineHttpResponse(new OperateLogResp<>(true)));
        OperateLogResp<Boolean> operateLogResp = uploadLog.upload(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertFalse(operateLogResp.isHasAbnormal());
        Assertions.assertTrue(operateLogResp.getSuccessResult());
    }
}
