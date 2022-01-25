package org.manageyourlog.test.base;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.manageyourlog.common.util.GsonUtil;
import org.manageyourlog.test.ManageYourLogTestApplication;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.lang.NonNull;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * base test which divide web container
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 17:52
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = ManageYourLogTestApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public abstract class BaseTest implements ApplicationContextAware, EnvironmentAware {

    protected static ApplicationContext context;

    protected static Environment environment;

    @Autowired
    protected MockMvc mockMvc;

    protected <T> String post(String urlTemplate, T data) throws Exception {
        return mockMvc
                .perform(
                        MockMvcRequestBuilders.post(urlTemplate)
                                .content(GsonUtil.getInstance().writeJson(data).getBytes(StandardCharsets.UTF_8))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    /**
     * get all incoming class type's implement class object by spring
     * @param classType specify class type
     * @param <T>
     * @return all implement by specify class
     */
    protected <T> List<T> getAllImplement(Class<T> classType){
        Map<String, T> allImplement = context.getBeansOfType(classType);
        List<T> res = new ArrayList<>();
        boolean alreadyAdd = false;
        for(T implement : allImplement.values()){
            for(T addedImplement : res){
                if(implement.getClass().isAssignableFrom(addedImplement.getClass())){
                    alreadyAdd = true;
                    break;
                }
            }
            if(!alreadyAdd){
                res.add(implement);
            }
        }
        return res;
    }

    @Override
    public void setApplicationContext(@NonNull ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    @Override
    public void setEnvironment(@NonNull Environment environment) {
        BaseTest.environment = environment;
    }
}
