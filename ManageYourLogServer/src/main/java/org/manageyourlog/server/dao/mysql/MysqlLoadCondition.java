package org.manageyourlog.server.dao.mysql;

import org.manageyourlog.server.repository.RepositoryMode;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author cartoon
 * @date 2021/12/31 20:16
 */
public class MysqlLoadCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Environment environment = context.getEnvironment();
        String storeMethod = environment.getProperty("store.method");
        return RepositoryMode.mysql.getMode().equals(storeMethod);
    }
}
