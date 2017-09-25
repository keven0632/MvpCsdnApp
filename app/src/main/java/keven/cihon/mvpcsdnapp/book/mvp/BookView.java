package keven.cihon.mvpcsdnapp.book.mvp;

import keven.cihon.mvpcsdnapp.Base.BaseMvpView;

/**
 * Created by zhengjian on 2017/9/25.
 */

public interface BookView extends BaseMvpView {
    void onSuccess(Object str,int which);
    void onError(String error,int which);
}
