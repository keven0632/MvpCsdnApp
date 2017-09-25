package keven.cihon.mvpcsdnapp.book.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import keven.cihon.mvpcsdnapp.R;

/**
 * Created by zhengjian on 2017/9/25.
 */

public class BookDetailFragment extends Fragment {

    @BindView(R.id.tv_content)
    TextView mTvContent;
    Unbinder unbinder;
    private String message;
    public BookDetailFragment() {

    }
    public void setMessage(String s){
        this.message=s;

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.book_detail_fragment, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }
    @Override
    public void onStart() {
        super.onStart();
        mTvContent.setText(message);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
