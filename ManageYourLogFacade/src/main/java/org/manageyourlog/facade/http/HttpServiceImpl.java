package org.manageyourlog.facade.http;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author cartoon
 * @date 2021/11/6 16:02
 */
@Component
public class HttpServiceImpl implements HttpService{

    private RestTemplate restTemplate;

    @Override
    public <T> T post(String url, Object data, Class<T> returnClassType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> requestData = new HttpEntity<>(data, headers);
        return restTemplate.postForObject(url, requestData, returnClassType);
    }

    public HttpServiceImpl() {
        this.restTemplate = new RestTemplate();
    }
}
