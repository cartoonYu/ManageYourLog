package org.manage.log.receive.repository.factory;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author cartoon
 * @date 2022/1/24 21:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Conditional(ReceiveRepositoryLoadConfig.class)
public @interface ReceiveRepositoryLoadCondition {

    ReceiveRepositoryMode mode();
}
