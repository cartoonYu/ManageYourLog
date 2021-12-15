package org.manageyourlog.test.facade.http;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.manageyourlog.facade.http.HttpServiceImpl;
import org.manageyourlog.test.base.BaseTest;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

/**
 * @author cartoon
 * @date 2021/12/7 11:35
 */
@DisplayName("http service test")
public class HttpServiceTest extends BaseTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    @Autowired
    private HttpServiceImpl httpService;

    @DisplayName("test http service with post method normally")
    @Test
    public void testPostNormal(){
        String mockResult = "";
        Mockito.when(restTemplate.postForObject(ArgumentMatchers.anyString(), ArgumentMatchers.any(), ArgumentMatchers.eq(String.class))).thenReturn(mockResult);
        String result = httpService.post("http://127.0.0.1", new JSONObject(), String.class);
        Assertions.assertEquals(mockResult, result);
    }
}
