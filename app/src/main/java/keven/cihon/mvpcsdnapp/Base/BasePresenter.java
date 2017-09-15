package keven.cihon.mvpcsdnapp.Base;

/**
 * Created by zhengjian on 2017/9/14.
 */

public class BasePresenter<V extends BaseMvpView> {
    private V view;

    public void attachView(V loginView) {
        this.view = loginView;
    }


    public void detachView() {
        this.view = null;
    }

    public V getView() {
        return this.view;
    }
}
