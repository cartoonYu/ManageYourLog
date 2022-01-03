package org.manageyourlog.server.dao;

import org.manageyourlog.server.config.ApplicationConfigKey;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Arrays;
import java.util.List;

/**
 * @author cartoon
 * @date 2022/1/2 18:42
 */
public abstract class StoreConditionImpl implements StoreLoadCondition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return matches(context, StoreLoadSortEnum.All) || matches(context, loadSort());
    }

    private boolean matches(ConditionContext context, StoreLoadSortEnum storeLoadSortEnum){
        Environment environment = context.getEnvironment();
        String storeLoad = environment.getProperty(ApplicationConfigKey.storeLoadDao.getKey());
        List<String> storeLoadList = Arrays.stream(storeLoad.split(",")).toList();
        return storeLoadList.contains(storeLoadSortEnum.getInfo());
    }

}
