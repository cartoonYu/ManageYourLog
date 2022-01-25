package org.manageyourlog.facade.service.factory;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

/**
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
