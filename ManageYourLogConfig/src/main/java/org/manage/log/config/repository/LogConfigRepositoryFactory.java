package org.manage.log.config.repository;

import com.google.common.collect.ImmutableList;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author cartoon.yu
 * @since 2022/8/9 20:44
 */
@Component
public class LogConfigRepositoryFactory implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    private final List<String> cacheRepositoryName = ImmutableList.of("cacheLogConfigRepository");

    @Bean("actualConfigRepository")
    public LogConfigRepository initActualRepository(){
        Map<String, LogConfigRepository> beansOfTypeList = applicationContext.getBeansOfType(LogConfigRepository.class);
        for(String beanName : beansOfTypeList.keySet()){
            if(!cacheRepositoryName.contains(beanName)){
                return beansOfTypeList.get(beanName);
            }
        }
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
