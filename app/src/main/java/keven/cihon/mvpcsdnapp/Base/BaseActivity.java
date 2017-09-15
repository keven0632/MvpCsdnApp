package keven.cihon.mvpcsdnapp.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by zhengjian on 2017/9/14.
 */

public abstract class BaseActivity<V extends BaseMvpView, P extends BasePresenter<V>> extends AppCompatActivity {
    public P getPresenter() {
        return presenter;
    }

    private P presenter;
    private V view;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.presenter == null) {
            //创建P层
            this.presenter = createPresenter();
        }
        if (this.view == null) {
            //创建V层
            this.view = createView();
        }
        //绑定
        if (this.presenter == null) {
            throw new NullPointerException("Presenter不能为空");
        }
        if (this.view == null) {
            throw new NullPointerException("View不能为空");
        }
        this.presenter.attachView(this.view);
    }

    //并不知道具体P是哪一个实现类，此方法由子类自己调用
    public abstract P createPresenter();

    //并不知道具体V是哪一个实现类，此方法由子类自己调用
    public abstract V createView();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (this.presenter != null) {
            this.presenter.detachView();
        }
    }
}
