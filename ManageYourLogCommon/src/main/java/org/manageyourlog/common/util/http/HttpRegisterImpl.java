package org.manageyourlog.common.util.http;

import org.manageyourlog.common.util.GsonUtil;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author cartoon
 * @date 2021/12/19 16:52
 */
@Service
public class HttpRegisterImpl implements HttpRegister{

    @Override
    public <T> T register(Class<T> httpService, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(GsonUtil.getInstance().formGsonObject()))
                .baseUrl(baseUrl)
                .build();
        return retrofit.create(httpService);
    }

}
