package keven.cihon.mvpcsdnapp.book.mvp;

import keven.cihon.mvpcsdnapp.Base.BaseModelCallBack;
import keven.cihon.mvpcsdnapp.api.DouBanApiInterface;
import keven.cihon.mvpcsdnapp.api.DoubanManager;
import keven.cihon.mvpcsdnapp.book.bean.BooksInfo;
import keven.cihon.mvpcsdnapp.utils.Constant;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengjian on 2017/9/25.
 */

public class BookModel {
    public void getHotBookData(final BaseModelCallBack callBack,String  name,int start) {

        DouBanApiInterface douBanApiInterface = DoubanManager.createMoviesService();
        Call<BooksInfo> list = douBanApiInterface.searchBooks(name,start);
        list.enqueue(new Callback<BooksInfo>() {

            @Override
            public void onResponse(Call<BooksInfo> call, Response<BooksInfo> response) {
                BooksInfo body = response.body();
                callBack.onSuccess(body, Constant.HOT_BOOK_REQUEST);
            }

            @Override
            public void onFailure(Call<BooksInfo> call, Throwable t) {
                callBack.onError("网络请求错误",Constant.HOT_BOOK_REQUEST);
            }
        });

    }
}
