package org.manageyourlog.test.util;

import com.google.common.collect.ImmutableList;
import okhttp3.Request;
import okio.Timeout;
import org.manageyourlog.common.constants.LogRecordIndexSort;
import org.manageyourlog.common.constants.LogRecordSort;
import org.manageyourlog.common.util.IdGenerateUtil;
import org.manageyourlog.facade.model.req.UploadLogRecordIndexReq;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.manageyourlog.server.model.LogRecord;
import org.manageyourlog.server.model.LogRecordIndex;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * define model util
 * @author cartoon
 * @version 1.0
 * @since 2021/10/07 19:43
 */
public class DefineModelUtil {

    public static <T> Call<T> defineHttpResponse(T data){
        return new Call<T>() {
            @Override
            public Response<T> execute() throws IOException {
                return Response.success(data);
            }

            @Override
            public void enqueue(Callback<T> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<T> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }

            @Override
            public Timeout timeout() {
                return null;
            }
        };
    }

    public static UploadLogRecordReq defineLogRecordReq(){
        UploadLogRecordIndexReq uploadLogRecordIndexReq = new UploadLogRecordIndexReq();
        uploadLogRecordIndexReq.setLogRecordIndexSort(LogRecordIndexSort.Id)
                .setIndexValue("111");
        UploadLogRecordReq uploadLogRecordReq = new UploadLogRecordReq();
        uploadLogRecordReq.setContent("111")
                .setOperatorSort("user")
                .setOperator("cartoon")
                .setLogRecordSort(LogRecordSort.Operate)
                .setIndexList(ImmutableList.of(uploadLogRecordIndexReq));
        return uploadLogRecordReq;
    }

    public static LogRecord defineLogRecord(){
        LogRecord record = new LogRecord();
        String recordId = IdGenerateUtil.generate(13);
        record.setRecordId(recordId)
                .setContent("111")
                .setOperatorSort("user")
                .setOperator("cartoon")
                .setLogRecordSort(LogRecordSort.Operate)
                .setIndexList(ImmutableList.of(defineLogRecordIndex(recordId)))
                .setVersion(1)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return record;
    }

    private static LogRecordIndex defineLogRecordIndex(String recordId){
        LogRecordIndex logRecordIndex = new LogRecordIndex();
        logRecordIndex.setIndexId(IdGenerateUtil.generate(13))
                .setLogRecordId(recordId)
                .setLogRecordIndexSort(LogRecordIndexSort.Id)
                .setIndexValue("111")
                .setVersion(1)
                .setCreateTime(LocalDateTime.now())
                .setModifyTime(LocalDateTime.now());
        return logRecordIndex;
    }
}
