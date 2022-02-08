package org.manage.log.common.util.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

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

}
