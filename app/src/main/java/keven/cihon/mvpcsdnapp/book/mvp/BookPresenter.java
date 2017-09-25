package keven.cihon.mvpcsdnapp.book.mvp;

import keven.cihon.mvpcsdnapp.Base.BaseModelCallBack;
import keven.cihon.mvpcsdnapp.Base.BasePresenter;

/**
 * Created by zhengjian on 2017/9/25.
 */

public class BookPresenter extends BasePresenter<BookView> implements BaseModelCallBack {

    private BookModel mBookModel;

    public BookPresenter() {
        if (mBookModel == null) {
            mBookModel = new BookModel();
        }
    }

    @Override
    public void onSuccess(Object result, int which) {
        if (getView() != null) {
            getView().onSuccess(result, which);
        }
    }

    public void getHotMovie(String name,int start) {
        if (mBookModel != null) {
            mBookModel.getHotBookData(this,name,start);
        }
    }

    @Override
    public void onError(String result, int which) {
        if (getView() != null) {
            getView().onError(result, which);
        }
    }
}
