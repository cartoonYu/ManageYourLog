package org.manage.log.query.base;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.manage.log.query.ManageYourLogQueryApplication;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;


/**
 * base test which divide web container
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 17:52
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = ManageYourLogQueryApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BaseTest implements EnvironmentAware {

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

    @Override
    public void setEnvironment(Environment environment) {
        BaseTest.environment = environment;
    }
}

