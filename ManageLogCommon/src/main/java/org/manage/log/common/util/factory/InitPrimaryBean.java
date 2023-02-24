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
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author cartoon
 * @version 1.0
 * @since 2022/07/31 17:54
 */
@Component
public class InitPrimaryBean implements BeanDefinitionRegistryPostProcessor, EnvironmentAware {

    protected final Logger log = LoggerFactory.getLogger(InitPrimaryBean.class);

    private static final String SCAN_PACKAGE_NAME = "org.manage.log";

    private Environment environment;

    private final Set<String> hasLoadPrimaryClass = new HashSet<>();

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        Reflections reflections = new Reflections(SCAN_PACKAGE_NAME);
        reflections.getTypesAnnotatedWith(LoadBean.class).forEach(annotatedWithLoanBeanClass -> {
            LoadBean loadBean = annotatedWithLoanBeanClass.getDeclaredAnnotation(LoadBean.class);
            // if client asks init primary, then operate, otherwise skip
            if(loadBean.needPrimary()){
                String configValue = environment.getProperty(loadBean.primaryConfigKey());
                if(Objects.nonNull(configValue) && configValue.equals(loadBean.mode())){
                    setPrimary(annotatedWithLoanBeanClass, beanDefinitionRegistry);
                }
            }
        });
    }

    private void setPrimary(Class<?> selectClass, BeanDefinitionRegistry beanDefinitionRegistry){
        //judge current class if hava init primary before
        boolean havePrimaryBefore = judgeHavePrimaryBefore(selectClass);
        if(havePrimaryBefore){
            return;
        }
        //init primary class
        setPrimary(beanDefinitionRegistry, selectClass);
        //add init primary class to set to prevent repeated init
        tagHaveInitPrimary(selectClass);
    }

    /**
     * judge select class have init primary before
     * @param selectClass class
     * @return whether init primary before
     */
    private boolean judgeHavePrimaryBefore(Class<?> selectClass){
        // select class has implement interface
        boolean havePrimaryBefore = Stream.of(selectClass.getGenericInterfaces()).map(Type::getTypeName).anyMatch(hasLoadPrimaryClass::contains);
        if(havePrimaryBefore){
            log.info("init primary, has load primary class, class name: {}", selectClass.getSimpleName());
            return true;
        }
        // select class hasn't implemented interface
        if(hasLoadPrimaryClass.contains(selectClass.getTypeName())){
            log.info("init primary, has load primary class, class name: {}", selectClass.getSimpleName());
            return true;
        }
        return false;
    }

    /**
     * set primary to given class
     * @param beanDefinitionRegistry
     * @param selectClass
     */
    private void setPrimary(BeanDefinitionRegistry beanDefinitionRegistry, Class<?> selectClass){
        String className = selectClass.getSimpleName();
        BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(Character.toLowerCase(className.charAt(0)) + className.substring(1));
        beanDefinition.setPrimary(true);
    }

    /**
     * tag have init primary after init class
     * @param selectClass init primary class type
     */
    private void tagHaveInitPrimary(Class<?> selectClass){
        // select class has implement interface

        Stream.of(selectClass.getGenericInterfaces()).forEach(genericInterface -> {
            log.info("init primary, load primary class, class name: {}, interface: {}", selectClass.getSimpleName(), genericInterface.getTypeName());
            hasLoadPrimaryClass.add(genericInterface.getTypeName());
        });
        // select class hasn't implemented interface
        if(!hasLoadPrimaryClass.contains(selectClass.getTypeName())){
            log.info("init primary, load primary class, class name: {}", selectClass.getTypeName());
            hasLoadPrimaryClass.add(selectClass.getTypeName());
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
