package org.manage.log.util;

import okhttp3.Request;
import okio.Timeout;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author cartoon
 * @date 2022/4/5 17:51
 */
public class DefineHttpResponseUtil {

    public static <T> Call<T> defineHttpResponse(T data){
        return new Call<T>() {
            @Override
            public Response<T> execute() {
                return Response.success(data);
            }

            @Override
            public void enqueue(Callback<T> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<T> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }

            @Override
            public Timeout timeout() {
                return null;
            }
        };
    }
}
