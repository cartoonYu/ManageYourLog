package org.manage.log.demo;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.manage.log.common.constants.LogRecordIndexSort;
import org.manage.log.common.constants.LogRecordSort;
import org.manage.log.upload.UploadLog;
import org.manage.log.upload.model.req.UploadLogRecordIndexReq;
import org.manage.log.upload.model.req.UploadLogRecordReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author cartoon
 * @date 2021/11/14 22:24
 */
@Aspect
@Component
public class DemoControllerAspect {

    @Autowired
    private UploadLog sendLog;

    @Around("execution(* org.manage.log.demo.DemoController.query(..))")
    public Object queryAspect(ProceedingJoinPoint pj) {
        try {

            QueryReq queryReq = (QueryReq)pj.getArgs()[0];
            String data = (String) pj.proceed();

            String content = String.format("%s operate %s", queryReq.getUserId(), queryReq.getOrderId());

            UploadLogRecordIndexReq uploadLogRecordIndexReq = new UploadLogRecordIndexReq();
            uploadLogRecordIndexReq.setLogRecordIndexSort(LogRecordIndexSort.ID)
                    .setIndexValue(queryReq.getOrderId());

            UploadLogRecordReq uploadLogRecordReq = new UploadLogRecordReq();
            uploadLogRecordReq.setContent(content)
                            .setOperatorSort("user")
                                    .setOperator(queryReq.getOrderId())
                                            .setLogRecordSort(LogRecordSort.OPERATE)
                                                    .setIndexList(Collections.singletonList(uploadLogRecordIndexReq));
            sendLog.upload(uploadLogRecordReq);
            return data;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
