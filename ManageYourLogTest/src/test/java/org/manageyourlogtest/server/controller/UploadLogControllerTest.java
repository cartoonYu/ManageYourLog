package org.manageyourlogtest.server.controller;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.manageyourlogserver.controller.UploadLogController;
import org.manageyourlogtest.ManageYourLogTestApplication;
import org.manageyourlogtest.base.BaseMVCTest;
import org.manageyourlogtest.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/01 22:55
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = UploadLogController.class)
public class UploadLogControllerTest extends BaseTest {

    @Autowired
    protected MockMvc mockMvc;

    @Test
    public void testUploadSingleLog() throws Exception{
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/uploadSingleLog");
        post.content("{\"content\": \"1111\"}".getBytes(StandardCharsets.UTF_8));
        String string = mockMvc.perform(post).andReturn().getResponse().getContentAsString();
        System.out.println(string);
    }
}
