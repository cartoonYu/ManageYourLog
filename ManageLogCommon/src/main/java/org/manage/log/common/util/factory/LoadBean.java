package org.manage.log.common.util.factory;


import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * determine load and init primary bean
 * @author cartoon
 * @since 2022/8/9 22:18
 * @version v1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Conditional(LoadCondition.class)
public @interface LoadBean {

    /**
     * config key for load, process will load value with this key to use
     * @return config key for load
     */
    String loadConfigKey();

    /**
     * primary config key for load, process will load value with this key to use
     * @return config key for determine load
     */
    String primaryConfigKey() default "";

    /**
     * bean's mode for
     * 1. compare with value with primary key
     * 2. compare with value with load config key
     * @return bean's mode
     */
    String mode();

    boolean needPrimary() default true;

}
