package keven.cihon.mvpcsdnapp.api;

import keven.cihon.mvpcsdnapp.movie.bean.HotMoviesInfo;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by zhengjian on 2017/9/14.
 */

public interface DouBanApiInterface {
     String BASE_URL="https://api.douban.com/v2/";
    @GET("movie/in_theaters")
    Call<HotMoviesInfo> getHotList();
}
