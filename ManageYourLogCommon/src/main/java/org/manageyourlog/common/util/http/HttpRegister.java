package org.manageyourlog.common.util.http;

/**
 * @author cartoon
 * @date 2021/12/19 16:51
 */
public interface HttpRegister {

    <T> T register(Class<T> httpService, String baseUrl);
}
