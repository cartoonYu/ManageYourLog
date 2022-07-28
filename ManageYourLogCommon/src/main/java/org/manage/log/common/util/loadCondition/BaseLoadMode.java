package org.manage.log.common.util.loadCondition;

/**
 * @author cartoon
 * @date 2022/2/6 15:10
 */
public interface BaseLoadMode<T> {

    String getMode();

    default Class<? extends T> classType(){
        return null;
    }
}
