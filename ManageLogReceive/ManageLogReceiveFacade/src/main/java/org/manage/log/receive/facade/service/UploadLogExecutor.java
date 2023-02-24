package org.manage.log.receive.facade.service;

import jakarta.annotation.PreDestroy;
import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.receive.facade.config.ApplicationConfigKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * @author cartoon
 * @version 1.0
 * @since 2023/2/19 15:43
 */
@Component
public class UploadLogExecutor {

    private static final Logger log = LoggerFactory.getLogger(UploadLogExecutor.class);

    private Integer corePoolSize = 2;

    private Integer maxPoolSize = 5;

    private Integer workQueueSize = 10000;

    private final long aliveTime = 10000L;

    private final TimeUnit timeUnit = TimeUnit.MICROSECONDS;

    private final ThreadFactory threadFactory = new CustomizableThreadFactory("upload log");

    private final ExecutorService EXECUTOR;

    public UploadLogExecutor(ApplicationConfigUtil applicationConfigUtil) {
        applicationConfigUtil.get(ApplicationConfigKey.uploadLogThreadPoolCoreSize.getKey(), (value) -> corePoolSize = Integer.parseInt(value));
        applicationConfigUtil.get(ApplicationConfigKey.uploadLogThreadPoolMaxSize.getKey(), (value) -> maxPoolSize = Integer.parseInt(value));
        applicationConfigUtil.get(ApplicationConfigKey.uploadLogThreadPoolQueueSize.getKey(), (value) -> workQueueSize = Integer.parseInt(value));
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(workQueueSize);
        RejectedExecutionHandler rejectedExecutionHandler = new ThreadPoolExecutor.CallerRunsPolicy();
        EXECUTOR = new ThreadPoolExecutor(corePoolSize, maxPoolSize, aliveTime, timeUnit, workQueue, threadFactory, rejectedExecutionHandler);
    }

    public Boolean execute(Callable<Boolean> task){
        try {
            return EXECUTOR.submit(task).get();
        } catch (Exception e) {
            log.error("upload log error", e);
            return false;
        }
    }

    @PreDestroy
    private void preDestroy(){
        //guarantee to execute all task
        ThreadPoolExecutor executor = (ThreadPoolExecutor) EXECUTOR;
        while (executor.getQueue().size() > 0 || executor.getActiveCount() > 0){
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
