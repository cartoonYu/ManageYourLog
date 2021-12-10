package org.manageyourlog.test.server.controller;

import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.test.base.BaseTestWithMvc;
import org.manageyourlog.test.util.DefineModelUtil;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/01 22:55
 */
public class UploadLogControllerTest extends BaseTestWithMvc {

    @Test
    public void testUploadSingleLog() throws Exception{
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        String result = post("/uploadSingleLog", uploadLogRecordReq);
        Assertions.assertNotNull(result);
    }

    @Test
    public void testUploadLogList() throws Exception{
        UploadLogRecordReq uploadLogRecordReq = DefineModelUtil.defineLogRecordReq();
        String result = post("/uploadLogList", ImmutableList.of(uploadLogRecordReq));
        Assertions.assertNotNull(result);
    }
}
