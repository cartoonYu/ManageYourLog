package org.manageyourlog.facade.service.http;

import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.facade.model.resp.UploadLogResp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import java.util.List;

/**
 * upload log by http define
 * @author cartoon
 * @date 2021/12/19 17:01
 */
public interface UploadLogInterface {

    @POST("/receiveSingleLog")
    Call<UploadLogResp<Boolean>> uploadSingleLog(@Body UploadLogRecordReq log);

    @POST("/receiveLogList")
    Call<UploadLogResp<Boolean>> uploadLogList(@Body List<UploadLogRecordReq> logList);

}
