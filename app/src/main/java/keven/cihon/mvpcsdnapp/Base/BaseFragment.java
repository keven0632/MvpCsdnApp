package keven.cihon.mvpcsdnapp.Base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import keven.cihon.mvpcsdnapp.R;
import keven.cihon.mvpcsdnapp.myview.CustomProgressDialog;

/**
 * Created by zhengjian on 2017/9/14.
 */

public abstract class BaseFragment<P extends BasePresenter<V>, V extends BaseMvpView> extends Fragment {


    private CustomProgressDialog customProgressDialog;

    public P getPresenter() {
        return presenter;
    }

    private P presenter;
    private V view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);

        return view;
    }

    public void showLoadingDialog() {
        if (customProgressDialog == null) {
            customProgressDialog = new CustomProgressDialog(getActivity());
            customProgressDialog.setCancelable(false);
        }
        if (!getActivity().isFinishing()) {
            try {
                customProgressDialog.show("加载中...", true, null).show();
            } catch (Exception e) {
            }
        }

    }

    public void dismissLoadingDialog() {
        if (customProgressDialog != null) {
            customProgressDialog.disMiss();
        }
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
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
    public void onDestroyView() {
        super.onDestroyView();
        if (this.presenter != null) {
            this.presenter.detachView();
        }

    }
}
