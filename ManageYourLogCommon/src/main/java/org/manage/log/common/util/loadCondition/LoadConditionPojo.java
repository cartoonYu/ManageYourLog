package org.manage.log.common.util.loadCondition;

/**
 * @author cartoon
 * @date 2022/2/6 22:30
 */
public class LoadConditionPojo {

    private String configKey;

    private Class<?> loadConditionClass;

    public LoadConditionPojo(String configKey, Class<?> loadConditionClass) {
        this.configKey = configKey;
        this.loadConditionClass = loadConditionClass;
    }

    public String getConfigKey() {
        return configKey;
    }

    public Class<?> getLoadConditionClass() {
        return loadConditionClass;
    }
}
