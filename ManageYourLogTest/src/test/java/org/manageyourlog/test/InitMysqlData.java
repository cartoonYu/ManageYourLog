package org.manageyourlog.test;

import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author cartoon
 * @date 2021/11/21 21:46
 */
@Component
public class InitMysqlData implements InitializingBean, DisposableBean {

    @Value("${mariadb.baseDir}")
    private String baseDir;

    @Value("${mariadb.dataDir}")
    private String dataDir;

    @Value("${mariadb.port}")
    private Integer port;

    private MariaDB4jSpringService mariaDB4jSpringService = new MariaDB4jSpringService();

    private static AtomicBoolean mariaDbIsStart = new AtomicBoolean(false);

    @Override
    public void afterPropertiesSet() throws Exception {
        if(!mariaDbIsStart.get()){
            mariaDB4jSpringService.setDefaultBaseDir(baseDir);
            mariaDB4jSpringService.setDefaultDataDir(dataDir);
            mariaDB4jSpringService.setDefaultPort(port);
            mariaDB4jSpringService.start();
            mariaDB4jSpringService.getDB().source("script/MysqlSchema.sql");
            mariaDB4jSpringService.getDB().source("script/MysqlData.sql");
            mariaDbIsStart.set(true);
        }

    }

    @Override
    public void destroy(){
        mariaDB4jSpringService.stop();
    }
}
