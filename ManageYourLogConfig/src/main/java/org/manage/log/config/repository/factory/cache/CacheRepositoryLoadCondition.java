package org.manage.log.config.repository.factory.cache;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * @author cartoon
 * @date 2022/1/24 21:16
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Conditional(CacheStoreRepositoryLoadConfig.class)
public @interface CacheRepositoryLoadCondition {

    CacheStoreRepositoryMode mode();
}
