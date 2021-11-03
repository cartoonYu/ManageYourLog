package org.manageyourlogtest.server.controller;

import org.junit.jupiter.api.Test;
import org.manageyourlogtest.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

/**
 * @author cartoon
 * @version 1.0
 * @since 2021/11/01 22:55
 */
public class UploadLogControllerTest extends BaseTest {

   /* @Autowired
    protected MockMvc mockMvc;

    @Test
    public void testUploadSingleLog() throws Exception{
        MockHttpServletRequestBuilder post = MockMvcRequestBuilders.post("/uploadSingleLog");
        post.content("{\"content\": \"1111\"}".getBytes(StandardCharsets.UTF_8));
        String string = mockMvc.perform(post).andReturn().getResponse().getContentAsString();
        System.out.println(string);
    }*/
}
