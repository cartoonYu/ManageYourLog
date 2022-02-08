package org.manage.log.facade.service.factory;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
 * determine upload log mode load
 * @author cartoon
 * @date 2022/1/23 22:43
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Conditional(UploadLogLoadConfig.class)
public @interface UploadLogLoadCondition {

    UploadLogMode mode();
}
