package org.manageyourlog.demo;

import com.google.common.collect.ImmutableList;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.manageyourlog.common.constants.LogRecordIndexSort;
import org.manageyourlog.common.constants.LogRecordSort;
import org.manageyourlog.facade.UploadLog;
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
    private UploadLog sendLog;

    @Around("execution(* org.manageyourlog.demo.DemoController.query(..))")
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
                                                    .setIndexList(ImmutableList.of(uploadLogRecordIndexReq));
            sendLog.upload(uploadLogRecordReq);
            return data;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
