package keven.cihon.mvpcsdnapp.movie.mvp;

import keven.cihon.mvpcsdnapp.Base.BaseMvpView;

/**
 * Created by zhengjian on 2017/9/14.
 */

public interface MovieView extends BaseMvpView {
    void onSuccess(Object str);
    void onError(String error);
}
