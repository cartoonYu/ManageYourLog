package org.manage.log.config.base;

import org.manage.log.base.test.InitMysqlDataUtil;
import org.manage.log.config.repository.factory.StoreRepositoryLoadCondition;
import org.manage.log.config.repository.factory.StoreRepositoryMode;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author cartoon
 * @date 2021/11/21 21:46
 */
@Component
@StoreRepositoryLoadCondition(mode = StoreRepositoryMode.Mysql)
public class InitMysqlData extends InitMysqlDataUtil implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        super.start();
    }

    @Override
    public void destroy(){
        super.stop();
    }
}
