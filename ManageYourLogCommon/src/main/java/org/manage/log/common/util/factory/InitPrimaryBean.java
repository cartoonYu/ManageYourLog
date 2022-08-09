package org.manage.log.common.util.factory;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/07/31 17:54
 */
@Component
public class InitPrimaryBean implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    protected final Logger log = LoggerFactory.getLogger(InitPrimaryBean.class);

    private Environment environment;

    private final Set<Class<?>> hasLoadPrimaryClass = new HashSet<>();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        String packageName = "org.manage.log";
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(LoadBean.class);
        for (Class<?> c : classes) {
            if(!c.isAnnotationPresent(LoadBean.class)){
                continue;
            }
            LoadBean loadBean = c.getDeclaredAnnotation(LoadBean.class);
            if(!loadBean.needPrimary()){
                continue;
            }
            String configValue = environment.getProperty(loadBean.primaryConfigKey());
            if(Objects.isNull(configValue)){
                log.warn("init primary bean, init class type: {}, have not determine mode, back to default class type: {}", loadBean.implementClass().getSimpleName(), loadBean.defaultClass().getSimpleName());
                setPrimary(loadBean.defaultClass(), loadBean.implementClass(), beanDefinitionRegistry);
            } else {
                if(configValue.equals(loadBean.mode())){
                    setPrimary(c, loadBean.implementClass(), beanDefinitionRegistry);
                }
            }
        }
    }

    private void setPrimary(Class<?> selectClass, Class<?> implementClass, BeanDefinitionRegistry beanDefinitionRegistry){
        if(hasLoadPrimaryClass.contains(implementClass)){
            log.info("has load primary class, class name: {}", implementClass.getSimpleName());
            return;
        }
        String className = selectClass.getSimpleName();
        BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(Character.toLowerCase(className.charAt(0)) + className.substring(1));
        beanDefinition.setPrimary(true);
        hasLoadPrimaryClass.add(implementClass);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
