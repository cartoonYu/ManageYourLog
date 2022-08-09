package org.manage.log.common.util.factory;


import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 *
 * @author cartoon
 * @since 2022/8/9 22:18
 * @version v1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Conditional(LoadCondition.class)
public @interface LoadBean {

    String primaryConfigKey() default "";

    String mode();

    Class<?> defaultClass();

    Class<?> implementClass();

    boolean needPrimary() default true;

    String loadConfigKey();
}
