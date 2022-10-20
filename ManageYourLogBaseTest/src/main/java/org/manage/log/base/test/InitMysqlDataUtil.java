package org.manage.log.base.test;

import ch.vorburger.exec.ManagedProcessException;
import ch.vorburger.mariadb4j.DB;
import ch.vorburger.mariadb4j.DBConfigurationBuilder;
import com.google.common.collect.ImmutableList;

import java.util.concurrent.atomic.AtomicBoolean;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @date 2022/4/5 16:38
 */
public abstract class InitMysqlDataUtil {

    /**
     * start mariadb util
     *
     * <p>it will read config from {@link InitMysqlDataUtil#getConfig()}, use config to start mariadb and execute DDL script and DML script by config determine
     * @throws Exception
     */
    protected void start() throws Exception{
        MariaDBConfig config = getConfig();
        if(!config.getMariaDbIsStart().get()){
            DBConfigurationBuilder configBuilder = DBConfigurationBuilder.newBuilder();
            configBuilder
                    .setPort(config.getPort())
                    .setDataDir(config.getDataDir())
                    .setBaseDir(config.getBaseDir());
            DB db = DB.newEmbeddedDB(configBuilder.build());
            db.start();
            for(String schema : config.getSchemaList()){
                db.source(schema);
            }
            for(String data : config.getDataList()){
                db.source(data);
            }
            config.setDb(db);
            config.setMariaDbIsStart(new AtomicBoolean(true));
        }
    }

    /**
     * stop mariadb when program exit
     */
    protected void stop(){
        ofNullable(getConfig().getDb()).ifPresent(obj -> {
            try {
                obj.stop();
            } catch (ManagedProcessException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * determine start mariadb config
     * <p>sub class can overwrite this to determine specify config</p>
     * @return mariadb config
     */
    protected MariaDBConfig getConfig(){
        MariaDBConfig mariaDBConfig = new MariaDBConfig();
        mariaDBConfig.setBaseDir("../local/base")
                .setDataDir("../local/data")
                .setPort(13306)
                .addSchemaScript("script/MysqlSchema.sql")
                .addDataScript("script/MysqlData.sql");
        return mariaDBConfig;
    }
}
