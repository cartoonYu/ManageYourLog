package org.manageyourlogtest;

import ch.vorburger.mariadb4j.springframework.MariaDB4jSpringService;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/10/07 14:57
 */
@Component
public class DatabaseInit implements InitializingBean, DisposableBean {

    @Value("${mariaDB4j.dataDir}")
    private String mariadbDataDir;

    @Value("${mariaDB4j.baseDir}")
    private String mariadbBaseDir;

    @Value("${mariaDB4j.port}")
    private Integer mariadbPort;

    private MariaDB4jSpringService mariaDB4jService;

    @Override
    public void afterPropertiesSet() throws Exception {
        mariaDB4jService.setDefaultBaseDir(mariadbBaseDir);
        mariaDB4jService.setDefaultDataDir(mariadbDataDir);
        mariaDB4jService.setDefaultPort(mariadbPort);
        mariaDB4jService.start();
        mariaDB4jService.getDB().source("script/ManageLogSchema.sql");
        mariaDB4jService.getDB().source("script/ManageLogData.sql");
    }

    @Override
    public void destroy() throws Exception {
        mariaDB4jService.stop();
    }

    public DatabaseInit() {
        mariaDB4jService = new MariaDB4jSpringService();
    }
}
