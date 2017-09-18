package keven.cihon.mvpcsdnapp.Base;

/**
 * Created by zhengjian on 2017/9/14.
 */

public interface BaseModelCallBack {
    void onSuccess(Object result,int which);

    void onError(String result,int which);
}
