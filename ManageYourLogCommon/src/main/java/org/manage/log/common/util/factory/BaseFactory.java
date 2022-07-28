package org.manage.log.common.util.factory;

import org.manage.log.common.util.config.ApplicationConfigUtil;
import org.manage.log.common.util.loadCondition.BaseLoadMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Optional;

/**
 * base factory<br/>
 * 1. check select mode if project have corresponding implementation class<br />
 * 2. provide ApplicationContext object for subclass use<br />
 * 3. define a Logger object for subclass to use<br />
 * @author cartoon
 * @version 1.0
 * @since 2021/11/16 00:02
 */
public abstract class BaseFactory{

    protected final Logger log = LoggerFactory.getLogger(BaseFactory.class);

    @Autowired
    protected ApplicationContext applicationContext;

    @Autowired
    private ApplicationConfigUtil applicationConfigUtil;

    protected <T> T initPrimaryBean(String configKey, BaseLoadMode<T> defaultMode, BaseLoadMode<T>[] matchModeList, Class<T> initClassType){
        Optional<String> configValue = applicationConfigUtil.get(configKey);
        Class<? extends T> defaultClass = defaultMode.classType();
        if(configValue.isEmpty()){
            log.warn("init primary bean, init class type: {}, have not determine mode, back to default class type: {}", initClassType.getSimpleName(), defaultClass.getSimpleName());
            return applicationContext.getBean(defaultClass);
        }
        Optional<BaseLoadMode<T>> selectMode = Arrays.stream(matchModeList).filter(mode -> mode.getMode().equals(configValue.get())).findAny();
        if(selectMode.isEmpty()){
            log.warn("init primary bean, init class type: {}, determine mode is not exist, back to default class type: {}", initClassType.getSimpleName(), defaultClass.getSimpleName());
            return applicationContext.getBean(defaultClass);
        }

        Class<? extends T> selectClass = selectMode.get().classType();
        log.info("init primary bean, init class type: {}, load class type: {}", initClassType.getSimpleName(), selectClass.getSimpleName());
        return applicationContext.getBean(selectClass);
    }

}
