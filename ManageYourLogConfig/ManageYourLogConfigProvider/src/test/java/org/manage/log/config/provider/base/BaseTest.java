package org.manage.log.config.provider.base;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.manage.log.common.util.GsonUtil;
import org.manage.log.config.provider.ManageYourLogConfigProviderApplication;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;


/**
 * base test which divide web container
 * @author cartoon
 * @version 1.0
 * @since 2021/10/05 17:52
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = ManageYourLogConfigProviderApplication.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BaseTest implements EnvironmentAware {

    protected static ApplicationContext context;

    protected static Environment environment;

    @Autowired
    protected MockMvc mockMvc;

    @Override
    public void setEnvironment(Environment environment) {
        BaseTest.environment = environment;
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
}

