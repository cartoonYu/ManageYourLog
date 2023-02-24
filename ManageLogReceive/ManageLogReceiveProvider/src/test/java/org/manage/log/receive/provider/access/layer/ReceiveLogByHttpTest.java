package org.manage.log.receive.provider.access.layer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manage.log.base.test.BaseTest;
import org.manage.log.receive.facade.dto.UploadLogRecordReq;
import org.manage.log.receive.provider.util.DefineModelUtil;

import java.util.Collections;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/12/6 20:31
 */
@DisplayName("receive log controller test")
public class ReceiveLogByHttpTest extends BaseTest {

    @DisplayName("receive single log test")
    @Test
    public void testReceiveSingleLog(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        String result = post("/receive/singleLog", uploadLogRecordReq);
        Assertions.assertNotNull(result);
    }

    @DisplayName("receive log list test")
    @Test
    public void testReceiveLogList(){
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        String result = post("/receive/logList", Collections.singletonList(uploadLogRecordReq));
        Assertions.assertNotNull(result);
    }
}
