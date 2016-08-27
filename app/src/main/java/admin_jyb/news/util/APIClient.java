package admin_jyb.news.util;



import java.io.IOException;

import admin_jyb.news.data.News;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Admin-JYB on 2016/8/27.
 */

public class ApiClient {

    private static final String BASE_URL = "http://apis.baidu.com/txapi/";

    private static Retrofit retrofit = null;

    public interface ApiService {

        @GET("social/social")
        Observable<News> getSocialResponse(@Query("num")int num);
    }

    private static Retrofit getClient(){
        if (retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(headerclient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient headerclient() {

        OkHttpClient okHttpClient =new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {

                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("apikey","f96a5387e56715f185af118dc0a44d4f")
                                .build();
                        return chain.proceed(request);
                    }
                })
                .build();

        return okHttpClient;
    }

    public static ApiService getService(){
        return getClient().create(ApiService.class);
    }
}

