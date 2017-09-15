package keven.cihon.mvpcsdnapp.movie.mvp;

import keven.cihon.mvpcsdnapp.Base.BaseModelCallBack;
import keven.cihon.mvpcsdnapp.api.DouBanApiInterface;
import keven.cihon.mvpcsdnapp.api.DoubanManager;
import keven.cihon.mvpcsdnapp.movie.bean.HotMoviesInfo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhengjian on 2017/9/14.
 */

public class MovieModel {

    public void getHotMoviewData(final BaseModelCallBack callBack) {

        DouBanApiInterface douBanApiInterface = DoubanManager.createMoviesService();
        Call<HotMoviesInfo> list = douBanApiInterface.getHotList();
        list.enqueue(new Callback<HotMoviesInfo>() {

            @Override
            public void onResponse(Call<HotMoviesInfo> call, Response<HotMoviesInfo> response) {
                HotMoviesInfo body = response.body();
                callBack.onSuccess(body);
            }

            @Override
            public void onFailure(Call<HotMoviesInfo> call, Throwable t) {
                callBack.onError("网络请求错误");
            }
        });

    }
}
