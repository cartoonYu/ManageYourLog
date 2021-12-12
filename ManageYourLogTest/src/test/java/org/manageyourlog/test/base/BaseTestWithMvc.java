package org.manageyourlog.test.base;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

/**
 * base test which contains web container
 * @author cartoon
 * @version 1.0
 * @since 2021/11/20 23:03
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BaseTestWithMvc {

    @Autowired
    protected MockMvc mockMvc;

    protected <T> String post(String urlTemplate, T data) throws Exception {
        return mockMvc
                .perform(
                        MockMvcRequestBuilders.post(urlTemplate)
                                .content(JSONObject.toJSONString(data).getBytes(StandardCharsets.UTF_8))
                                .contentType(MediaType.APPLICATION_JSON_VALUE)
                )
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}
