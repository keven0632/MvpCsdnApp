package keven.cihon.mvpcsdnapp.api;

import keven.cihon.mvpcsdnapp.book.bean.BooksInfo;
import keven.cihon.mvpcsdnapp.movie.bean.HotMoviesInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by zhengjian on 2017/9/14.
 */

public interface DouBanApiInterface {
     String BASE_URL="https://api.douban.com/v2/";
    @GET("movie/in_theaters")
    Call<HotMoviesInfo> getHotList();

//    @GET("movie/in_theaters")
//    Observable<HotMoviesInfo> searchHotMoviesWithRxJava(@Query("start") int startIndex);
//
    @GET("book/search")
    Call<BooksInfo> searchBooks(@Query("q") String name, @Query("start") int index);
//
//    @GET("book/search")
//    Observable<BooksInfo> searchBooksWithRxJava(@Query("q") String name, @Query("start") int index);
}
