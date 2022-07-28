package org.manage.log.receive.repository.factory;

import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.factory.BaseFactory;
import org.manage.log.common.util.loadCondition.BaseLoadMode;
import org.manage.log.receive.repository.LogRecordRepository;
import org.manage.log.receive.repository.config.ReceiveApplicationConfigKey;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/15 23:56
 */
@Component
public class ReceiveRepositoryFactory extends BaseFactory implements InitializingBean {

    @Autowired
    private ApplicationConfigUtil applicationConfigUtil;

    /*@Bean
    @Primary
    public LogRecordRepository initPrimaryRepository(){
        return initPrimaryBean(ReceiveApplicationConfigKey.storeMode.getKey(), ReceiveRepositoryMode.Mysql, ReceiveRepositoryMode.values(), LogRecordRepository.class);
    }*/

    @Override
    public void afterPropertiesSet() throws Exception {
        DefaultListableBeanFactory app = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
        BeanDefinition beanDefinition = app.getBeanDefinition("logRecordMysqlRepository");
        beanDefinition.setPrimary(true);
    }
}
