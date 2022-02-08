package org.manage.log.common.util.http;

import org.manage.log.common.util.GsonUtil;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * register http service by retrofit
 * @author cartoon
 * @date 2022/1/27 17:09
 */
public class HttpRegisterUtil {

    private static final HttpRegisterUtil INSTANCE = new HttpRegisterUtil();

    public static HttpRegisterUtil getInstance(){
        return INSTANCE;
    }

    /**
     * register by class type with gson convert factory
     * @param httpService to be register class
     * @param baseUrl http base url
     * @param <T> o be register class type
     * @return proxy class
     */
    public <T> T register(Class<T> httpService, String baseUrl) {
        return register(httpService, baseUrl, GsonConverterFactory.create(GsonUtil.getInstance().formGsonObject()));
    }

    /**
     * register by class type
     * @param httpService to be register class
     * @param baseUrl http base url
     * @param converterFactory data convert factory
     * @param <T> o be register class type
     * @return proxy class
     */
    public <T> T register(Class<T> httpService, String baseUrl, Converter.Factory converterFactory) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(converterFactory)
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(httpService);
    }

    private HttpRegisterUtil(){}
}
