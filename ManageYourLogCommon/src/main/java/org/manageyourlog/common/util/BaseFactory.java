package org.manageyourlog.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * base factory<br/>
 * 1. check select mode if have corresponding implementation class<br />
 * 2. provide ApplicationContext object for subclass use
 * @author cartoon
 * @version 1.0
 * @since 2021/11/16 00:02
 */
public abstract class BaseFactory implements ApplicationContextAware, ApplicationRunner {

    protected final Logger log = LoggerFactory.getLogger(BaseFactory.class);

    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(ApplicationArguments args) {
        checkModeIllegal();
    }

    protected abstract void checkModeIllegal() throws IllegalArgumentException;
}
