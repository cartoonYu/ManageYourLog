package org.manage.log.query.provider.base;

import org.manage.log.base.test.mariadb.InitMysqlDataUtil;
import org.manage.log.common.util.factory.LoadBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author cartoon
 * @date 2021/11/21 21:46
 */
@Component
@LoadBean(loadConfigKey = "store.load.mode", mode = "mysql", needPrimary = false)
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
