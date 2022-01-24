package org.manageyourlog.common.util;

import java.util.Collection;

import static java.util.Optional.ofNullable;

/**
 * @author cartoon
 * @date 2021/10/23 18:36
 */
public class CollectionUtil {

    private static final CollectionUtil INSTANCE = new CollectionUtil();

    public static CollectionUtil getInstance(){
        return INSTANCE;
    }

    public boolean judgeIsEmpty(Collection<?> collection){
        return ofNullable(collection).map(Collection::isEmpty).orElse(true);
    }

    public boolean judgeIsNotEmpty(Collection<?> collection){
        return ofNullable(collection).map(source -> !source.isEmpty()).orElse(false);
    }
}
