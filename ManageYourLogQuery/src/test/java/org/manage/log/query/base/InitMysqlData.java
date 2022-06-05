package org.manage.log.query.base;

import org.manage.log.base.test.InitMysqlDataUtil;
import org.manage.log.query.repository.factory.QueryRepositoryLoadCondition;
import org.manage.log.query.repository.factory.QueryRepositoryMode;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author cartoon
 * @date 2021/11/21 21:46
 */
@Component
@QueryRepositoryLoadCondition(mode = QueryRepositoryMode.Mysql)
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
