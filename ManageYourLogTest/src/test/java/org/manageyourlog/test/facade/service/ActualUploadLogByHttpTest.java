package org.manageyourlog.test.facade.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlog.facade.http.HttpService;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import org.manageyourlog.facade.service.ActualUploadLogByHttp;
import org.manageyourlog.test.base.BaseTest;
import org.manageyourlog.test.util.DefineModelUtil;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/20 22:05
 */
public class ActualUploadLogByHttpTest extends BaseTest {

    @Mock
    private HttpService httpService;

    @InjectMocks
    @Autowired
    private ActualUploadLogByHttp uploadLogByHttp;

    @Test
    public void testUploadSingleLog(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        Mockito.when(httpService.post(anyString(), eq(uploadLogRecordReq), eq(UploadLogResp.class))).thenReturn(new UploadLogResp<>(true));
        UploadLogResp<Boolean> uploadLogResp = uploadLogByHttp.upload(uploadLogRecordReq);
        Assertions.assertFalse(uploadLogResp.isHasAbnormal());
        Assertions.assertTrue(uploadLogResp.getSuccessResult());
    }

    @Test
    public void testUploadLogList(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        Mockito.when(httpService.post(anyString(), eq(ImmutableList.of(uploadLogRecordReq)), eq(UploadLogResp.class))).thenReturn(new UploadLogResp<>(true));
        UploadLogResp<Boolean> uploadLogResp = uploadLogByHttp.upload(ImmutableList.of(uploadLogRecordReq));
        Assertions.assertFalse(uploadLogResp.isHasAbnormal());
        Assertions.assertTrue(uploadLogResp.getSuccessResult());
    }
}
