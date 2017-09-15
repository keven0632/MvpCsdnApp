package keven.cihon.mvpcsdnapp.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by zhengjian on 2017/9/15.
 */

public class DoubanManager {
    private static DouBanApiInterface sDoubanService;

    public synchronized static DouBanApiInterface createMoviesService() {
        if (sDoubanService  == null) {
            Retrofit retrofit = createRetrofit();
            sDoubanService = retrofit.create(DouBanApiInterface.class);
        }

        return sDoubanService;
    }

    private static Retrofit createRetrofit() {
        OkHttpClient httpClient;
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder().addInterceptor(logging).build();

        return new Retrofit.Builder()
                .baseUrl(DouBanApiInterface.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                //.client(httpClient)
                .build();
    }
}
