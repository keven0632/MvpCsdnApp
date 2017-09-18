package keven.cihon.mvpcsdnapp.movie.mvp;

import keven.cihon.mvpcsdnapp.Base.BaseModelCallBack;
import keven.cihon.mvpcsdnapp.Base.BasePresenter;

/**
 * Created by zhengjian on 2017/9/14.
 */

public class MoviewPresenter extends BasePresenter<MovieView> implements BaseModelCallBack {

    private MovieModel mMovieModel;

    public MoviewPresenter() {
        if (mMovieModel == null) {
            mMovieModel = new MovieModel();
        }
    }

    @Override
    public void onSuccess(Object result, int which) {
        if (getView() != null) {
            getView().onSuccess(result, which);
        }
    }

    public void getHotMovie() {
        if (mMovieModel != null) {
            mMovieModel.getHotMoviewData(this);
        }
    }

    @Override
    public void onError(String result, int which) {
        if (getView() != null) {
            getView().onError(result, which);
        }
    }
}
