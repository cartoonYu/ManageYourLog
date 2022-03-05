package org.manage.log.server.receive.service;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.*;
import org.manage.log.base.BaseTest;
import org.manage.log.common.constants.HandleError;
import org.manage.log.facade.model.req.UploadLogRecordReq;
import org.manage.log.facade.model.resp.OperateLogResp;
import org.manage.log.util.DefineModelUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/03/05 16:17
 */
@DisplayName("receive log")
public class ReceiveLogTest extends BaseTest {

    private List<ReceiveLog> receiveLogs = getAllImplement(ReceiveLog.class);

    private UploadLogRecordReq uploadLogRecordReq;

    @DisplayName("receive single log normal")
    @Test
    public void testReceiveSingleLogNormal(){
        receiveLogs.forEach(receiveLog -> {
            OperateLogResp<Boolean> uploadRes = receiveLog.receive(uploadLogRecordReq);
            Assertions.assertFalse(uploadRes.isHasAbnormal());
            Assertions.assertTrue(uploadRes.getSuccessResult());
        });
    }

    @DisplayName("receive log list normal")
    @Test
    public void testReceiveLogListNormal(){
        receiveLogs.forEach(receiveLog -> {
            OperateLogResp<Boolean> uploadRes = receiveLog.receive(ImmutableList.of(uploadLogRecordReq));
            Assertions.assertFalse(uploadRes.isHasAbnormal());
            Assertions.assertTrue(uploadRes.getSuccessResult());
        });
    }

    @DisplayName("receive log with empty content")
    @Test
    public void testReceiveLogWithEmptyContent(){
        receiveLogs.forEach(receiveLog -> {
            uploadLogRecordReq.setContent(null);
            OperateLogResp<Boolean> uploadRes = receiveLog.receive(uploadLogRecordReq);
            Assertions.assertTrue(uploadRes.isHasAbnormal());
            Assertions.assertEquals(HandleError.PARAM_MISS, uploadRes.getFailResult());
        });
    }

    @DisplayName("receive log with empty operator")
    @Test
    public void testReceiveLogWithEmptyOperator(){
        receiveLogs.forEach(receiveLog -> {
            uploadLogRecordReq.setOperator(null);
            OperateLogResp<Boolean> uploadRes = receiveLog.receive(uploadLogRecordReq);
            Assertions.assertTrue(uploadRes.isHasAbnormal());
            Assertions.assertEquals(HandleError.PARAM_MISS, uploadRes.getFailResult());
        });
    }

    @DisplayName("receive log with empty sort")
    @Test
    public void testReceiveLogWithEmptySort(){
        receiveLogs.forEach(receiveLog -> {
            uploadLogRecordReq.setLogRecordSort(null);
            OperateLogResp<Boolean> uploadRes = receiveLog.receive(uploadLogRecordReq);
            Assertions.assertTrue(uploadRes.isHasAbnormal());
            Assertions.assertEquals(HandleError.PARAM_MISS, uploadRes.getFailResult());
        });
    }

    @DisplayName("receive log list with empty list")
    @Test
    public void testReceiveLogListWithEmptyList(){
        receiveLogs.forEach(receiveLog -> {
            OperateLogResp<Boolean> uploadRes = receiveLog.receive(ImmutableList.of());
            Assertions.assertTrue(uploadRes.isHasAbnormal());
            Assertions.assertEquals(HandleError.PARAM_MISS, uploadRes.getFailResult());
        });
    }

    @BeforeEach
    public void init(){
        uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        uploadLogRecordReq.setUploadTime(LocalDateTime.now());
    }
}
