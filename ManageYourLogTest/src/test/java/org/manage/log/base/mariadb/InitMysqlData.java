package org.manage.log.base.mariadb;

import com.google.common.collect.ImmutableList;
import org.manage.log.repository.factory.StoreRepositoryLoadCondition;
import org.manage.log.repository.factory.StoreRepositoryMode;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author cartoon
 * @date 2021/11/21 21:46
 */
@Component
@StoreRepositoryLoadCondition(mode = StoreRepositoryMode.Mysql)
public class InitMysqlData extends InitMysqlDataUtil implements InitializingBean, DisposableBean {

    @Value("${mariadb.baseDir}")
    private String baseDir;

    @Value("${mariadb.dataDir}")
    private String dataDir;

    @Value("${mariadb.port}")
    private Integer port;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.start();
    }

    @Override
    public void destroy(){
        super.stop();
    }

    @Override
    public MariaDBConfig getConfig() {
        MariaDBConfig mariaDBConfig = new MariaDBConfig();
        mariaDBConfig.setBaseDir(baseDir)
                .setDataDir(dataDir)
                .setPort(port)
                .setSchemaList(ImmutableList.of("script/MysqlSchema.sql"))
                .setDataList(ImmutableList.of("script/MysqlData.sql"));
        return mariaDBConfig;
    }
}
