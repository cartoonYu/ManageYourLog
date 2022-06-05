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

    protected void start() throws Exception{
        MariaDBConfig config = getConfig();
        if(!config.getMariaDbIsStart().get()){
            DBConfigurationBuilder configBuilder = DBConfigurationBuilder.newBuilder();
            configBuilder.setPort(config.getPort());
            configBuilder.setDataDir(config.getDataDir());
            configBuilder.setBaseDir(config.getBaseDir());
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

    protected void stop(){
        ofNullable(getConfig().getDb()).ifPresent(obj -> {
            try {
                obj.stop();
            } catch (ManagedProcessException e) {
                e.printStackTrace();
            }
        });
    }

    public MariaDBConfig getConfig(){
        MariaDBConfig mariaDBConfig = new MariaDBConfig();
        mariaDBConfig.setBaseDir("../local/base")
                .setDataDir("../local/data")
                .setPort(3306)
                .setSchemaList(ImmutableList.of("script/MysqlSchema.sql"))
                .setDataList(ImmutableList.of("script/MysqlData.sql"));
        return mariaDBConfig;
    }
}
