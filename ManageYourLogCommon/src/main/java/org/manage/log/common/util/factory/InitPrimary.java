package org.manage.log.common.util.factory;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface InitPrimary {

    String configKey();

    String mode();

    Class<?> defaultClass();

    Class<?> implementClass();


}
