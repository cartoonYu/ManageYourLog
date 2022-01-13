package org.manageyourlog.common.util.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;

/**
 * base factory<br/>
 * 1. check select mode if project have corresponding implementation class<br />
 * 2. provide ApplicationContext object for subclass use<br />
 * 3. define a Logger object for subclass to use<br />
 * @author cartoon
 * @version 1.0
 * @since 2021/11/16 00:02
 */
public abstract class BaseFactory implements ApplicationContextAware {

    protected final Logger log = LoggerFactory.getLogger(BaseFactory.class);

    protected ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
