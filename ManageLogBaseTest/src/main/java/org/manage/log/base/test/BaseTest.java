package org.manage.log.base.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author cartoon
 * @since 2022/11/24 21:07
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BaseTest implements EnvironmentAware {

    protected static ApplicationContext context;

    protected static Environment environment;

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void setEnvironment(Environment environment) {
        BaseTest.environment = environment;
    }

    protected <T> String post(String urlTemplate, T data){
        try {
            return mockMvc
                    .perform(
                            MockMvcRequestBuilders.post(urlTemplate)
                                    .content(objectMapper.writeValueAsString(data).getBytes(StandardCharsets.UTF_8))
                                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    )
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString(StandardCharsets.UTF_8);
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            return null;
        }
    }

    protected <T> T get(String urlTemplate, Class<T> classType){
        try {
            String sourceResponse = get(urlTemplate, ImmutableList.of());
            return objectMapper.readValue(sourceResponse, classType);
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            return null;
        }
    }

    protected <T> T get(String urlTemplate, List<ImmutablePair<String, String>> paramToDataList, Class<T> classType){
        try {
            String sourceResponse = get(urlTemplate, paramToDataList);
            return objectMapper.readValue(sourceResponse, classType);
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            return null;
        }
    }

    protected <T> List<T> getList(String urlTemplate, Class<T> classType){
        try {
            String sourceResponse = get(urlTemplate, ImmutableList.of());
            return objectMapper.readValue(sourceResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, classType));
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            return null;
        }
    }

    protected <T> List<T> getList(String urlTemplate, List<ImmutablePair<String, String>> paramToDataList, Class<T> classType){
        try {
            String sourceResponse = get(urlTemplate, paramToDataList);
            return objectMapper.readValue(sourceResponse, objectMapper.getTypeFactory().constructCollectionType(List.class, classType));
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            return null;
        }
    }

    private String get(String urlTemplate, List<ImmutablePair<String, String>> paramToDataList){
        try {
            MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get(urlTemplate).contentType(MediaType.APPLICATION_JSON_VALUE);
            paramToDataList.forEach((param) -> requestBuilder.queryParam(param.getLeft(), param.getRight()));
            MvcResult mvcResult =  mockMvc
                    .perform(requestBuilder)
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andReturn();
            return mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8);
        } catch (Exception e){
            Assertions.fail(e.getMessage());
            return null;
        }
    }
}
