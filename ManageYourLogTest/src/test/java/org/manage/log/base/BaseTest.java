package org.manage.log.base;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.manage.log.common.util.GsonUtil;
import org.manage.log.ManageYourLogTestApplication;
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
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;


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
public class BaseTest implements ApplicationContextAware, EnvironmentAware {

    protected static ApplicationContext context;

    protected static Environment environment;

    @Autowired
    protected MockMvc mockMvc;

    protected String get(String urlTemplate, List<ImmutablePair<String, String>> paramToDataList) throws Exception{
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(urlTemplate).accept(MediaType.APPLICATION_JSON_VALUE);
        paramToDataList.forEach((param) -> requestBuilder.queryParam(param.getLeft(), param.getRight()));
        return mockMvc
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
    }

    protected String get(String urlTemplate, ImmutablePair<String, String>... paramToDataList) throws Exception{
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(urlTemplate).accept(MediaType.APPLICATION_JSON_VALUE);
        Arrays.stream(paramToDataList).forEach(param -> {
            requestBuilder.queryParam(param.getLeft(), param.getRight());
        });
        return mockMvc
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
    }

    protected <T> String post(String urlTemplate, T data) throws Exception {
        return mockMvc
                .perform(
                        MockMvcRequestBuilders.post(urlTemplate)
                                .content(GsonUtil.getInstance().writeJson(data).getBytes(StandardCharsets.UTF_8))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString(StandardCharsets.UTF_8);
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

