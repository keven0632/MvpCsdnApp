package keven.cihon.mvpcsdnapp.book.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import keven.cihon.mvpcsdnapp.Base.BaseFragment;
import keven.cihon.mvpcsdnapp.R;
import keven.cihon.mvpcsdnapp.adapter.HotBooksAdapter;
import keven.cihon.mvpcsdnapp.book.bean.Book;
import keven.cihon.mvpcsdnapp.book.bean.BooksInfo;
import keven.cihon.mvpcsdnapp.book.mvp.BookPresenter;
import keven.cihon.mvpcsdnapp.book.mvp.BookView;
import keven.cihon.mvpcsdnapp.utils.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class BooksFragment extends BaseFragment<BookPresenter,BookView> implements BookView {


    @BindView(R.id.recycleview_book)
    RecyclerView mRecycleviewBook;
    Unbinder unbinder;
    private BookPresenter mPresenter;


    private HotBooksAdapter mHotBooksAdapter;
    private LinearLayoutManager mLayoutManager;



    public BooksFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_books, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter.getHotMovie("黑客与画家",0);
        //set recycle view
        mRecycleviewBook.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecycleviewBook.setLayoutManager(mLayoutManager);
        mHotBooksAdapter = new HotBooksAdapter(getActivity(),  R.layout.recyclerview_book_item);
        mRecycleviewBook.setAdapter(mHotBooksAdapter);
    }


    @Override
    public BookPresenter createPresenter() {
        mPresenter = new BookPresenter();
        return mPresenter;
    }

    @Override
    public BookView createView() {
        return this;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSuccess(Object str, int which) {

        switch (which){
            case 2:
                BooksInfo info= (BooksInfo) str;
                List<Book> books = info.getBooks();
//                for(int i=0;i<books.size();i++){
//                    LogUtils.e(books.get(i).getTitle());
//                }
                mHotBooksAdapter.setData(books);

                break;
        }
    }

    @Override
    public void onError(String error, int which) {
        ToastUtils.showToast(getActivity(),"网络异常，请稍后再试！");
    }
}
