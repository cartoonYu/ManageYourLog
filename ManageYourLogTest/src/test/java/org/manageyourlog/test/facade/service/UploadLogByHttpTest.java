package org.manageyourlog.test.facade.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlog.common.config.ApplicationConfig;
import org.manageyourlog.common.constants.Error;
import org.manageyourlog.facade.http.HttpService;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.facade.service.UploadLogByHttp;
import org.manageyourlog.facade.service.UploadLogMode;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/20 22:05
 */
public class UploadLogByHttpTest extends BaseTest{

    @Mock
    private HttpService httpService;

    @Autowired
    @Spy
    private ApplicationConfig applicationConfig;

    @InjectMocks
    @Autowired
    private UploadLogByHttp uploadLog;

    @Test
    public void testUploadSingleLog(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        Mockito.when(httpService.post(anyString(), eq(uploadLogRecordReq), eq(UploadLogResp.class))).thenReturn(new UploadLogResp<>(true));
        UploadLogResp<Boolean> uploadLogResp = uploadLog.upload(uploadLogRecordReq);
        Assertions.assertFalse(uploadLogResp.isHasAbnormal());
        Assertions.assertTrue(uploadLogResp.getSuccessResult());
    }

    @Test
    public void testUploadLogList(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        Mockito.when(httpService.post(anyString(), eq(ImmutableList.of(uploadLogRecordReq)), eq(UploadLogResp.class))).thenReturn(new UploadLogResp<>(true));
        UploadLogResp<Boolean> uploadLogResp = uploadLog.upload(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertFalse(uploadLogResp.isHasAbnormal());
        Assertions.assertTrue(uploadLogResp.getSuccessResult());
    }

    @Test
    public void testUploadLogWithoutBaseUrl(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        Mockito.when(applicationConfig.get(UploadLogMode.http.getBaseUrl())).thenReturn(Optional.empty());
        UploadLogResp<Boolean> uploadLogResp = uploadLog.upload(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertTrue(uploadLogResp.isHasAbnormal());
        Assertions.assertEquals(Error.uploadUrlMiss, uploadLogResp.getFailResult());
        Assertions.assertEquals(Error.uploadUrlMiss.getCode(), uploadLogResp.getFailResult().getCode());
        Assertions.assertEquals(Error.uploadUrlMiss.getMsg(), uploadLogResp.getFailResult().getMsg());
    }
}
