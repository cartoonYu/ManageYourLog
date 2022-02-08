package org.manage.log.server.query.access.layer;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author cartoon
 * @date 2022/1/24 21:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Conditional(QueryLogLoadConfig.class)
public @interface QueryLogLoadCondition {

    QueryLogMode mode();
}
