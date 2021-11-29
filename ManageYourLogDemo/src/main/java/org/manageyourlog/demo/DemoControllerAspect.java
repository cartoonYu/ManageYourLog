package org.manageyourlog.demo;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.manageyourlog.common.constants.LogRecordIndexSort;
import org.manageyourlog.common.constants.LogRecordSort;
import org.manageyourlog.facade.TransferLog;
import org.manageyourlog.facade.model.req.UploadLogRecordIndexReq;
import org.manageyourlog.facade.model.req.UploadLogRecordReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author cartoon
 * @date 2021/11/14 22:24
 */
@Aspect
@Component
public class DemoControllerAspect {

    @Autowired
    private TransferLog uploadLog;

    @Around("execution(* org.manageyourlog.demo.DemoController.query(..))")
    public Object queryAspect(ProceedingJoinPoint pj) {
        try {
            QueryReq queryReq = JSONObject.parseObject(JSONObject.toJSONString(pj.getArgs()[0]), QueryReq.class);
            String data = (String) pj.proceed();

            String content = String.format("%s operate %s", queryReq.getUserId(), queryReq.getOrderId());

            UploadLogRecordIndexReq uploadLogRecordIndexReq = new UploadLogRecordIndexReq();
            uploadLogRecordIndexReq.setLogRecordIndexSort(LogRecordIndexSort.Id)
                    .setIndexValue(queryReq.getOrderId());

            UploadLogRecordReq uploadLogRecordReq = new UploadLogRecordReq();
            uploadLogRecordReq.setContent(content)
                            .setOperatorSort("user")
                                    .setOperator(queryReq.getOrderId())
                                            .setLogRecordSort(LogRecordSort.Operate)
                                                    .setIndexList(ImmutableList.of(uploadLogRecordIndexReq));
            uploadLog.upload(uploadLogRecordReq);
            return data;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
