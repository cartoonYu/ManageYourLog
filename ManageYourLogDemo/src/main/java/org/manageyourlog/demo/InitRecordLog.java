package org.manageyourlog.demo;

import org.manageyourlog.facade.ScanRecordLogAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/08 00:00
 */
@Component
public class InitRecordLog implements ApplicationRunner {

    private String packageName = "org.manageyourlog.demo";

    @Autowired
    private ScanRecordLogAnnotation scanRecordLogAnnotation;

    @Override
    public void run(ApplicationArguments args){
        scanRecordLogAnnotation.scan(packageName);
    }
}
