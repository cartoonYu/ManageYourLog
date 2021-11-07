package org.manageyourlog.facade.http;
/**
 * @author cartoon
 * @date 2021/11/6 15:57
 */
public interface HttpService {

    <T>T post(String url, Object data, Class<T> returnClassType);
}
