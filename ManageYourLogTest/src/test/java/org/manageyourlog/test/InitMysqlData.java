package org.manageyourlog.test;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import org.manageyourlog.server.dao.DatasourceEnum;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @date 2021/11/21 21:46
 */
@Component
@ConditionalOnBean(name = DatasourceEnum.mysql)
public class InitMysqlData implements InitializingBean, DisposableBean {

    @Value("${mariadb.baseDir}")
    private String baseDir;

    @Value("${mariadb.dataDir}")
    private String dataDir;

    @Value("${mariadb.port}")
    private Integer port;

    private static AtomicBoolean mariaDbIsStart = new AtomicBoolean(false);

    private DB db;

    @Override
    public void afterPropertiesSet() throws Exception {
        if(!mariaDbIsStart.get()){
            DBConfigurationBuilder configBuilder = DBConfigurationBuilder.newBuilder();
            configBuilder.setPort(port); // OR, default: setPort(0); => autom. detect free port
            configBuilder.setDataDir(dataDir); // just an example
            configBuilder.setBaseDir(baseDir);
            db = DB.newEmbeddedDB(configBuilder.build());
            db.source("script/MysqlSchema.sql");
            db.source("script/MysqlData.sql");
            db.start();
            mariaDbIsStart.set(true);
        }

    }

    @Override
    public void destroy(){
        ofNullable(db).ifPresent(obj -> {
            try {
                obj.stop();
            } catch (ManagedProcessException e) {
                e.printStackTrace();
            }
        });
    }
}
