package org.manage.log.common.util.factory;


import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Conditional(LoadCondition.class)
public @interface LoadBean {

    String primaryConfigKey() default "";

    String loadConfigKey();

    String mode();

    Class<?> defaultClass();

    Class<?> implementClass();

    boolean needPrimary() default true;
}
