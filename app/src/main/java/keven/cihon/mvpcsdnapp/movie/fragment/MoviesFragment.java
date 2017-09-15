package keven.cihon.mvpcsdnapp.movie.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import keven.cihon.mvpcsdnapp.Base.BaseFragment;
import keven.cihon.mvpcsdnapp.R;
import keven.cihon.mvpcsdnapp.movie.bean.HotMoviesInfo;
import keven.cihon.mvpcsdnapp.movie.mvp.MovieView;
import keven.cihon.mvpcsdnapp.movie.mvp.MoviewPresenter;
import keven.cihon.mvpcsdnapp.utils.LogUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviesFragment extends BaseFragment<MoviewPresenter, MovieView> implements MovieView {


    @BindView(R.id.recycleview)
    RecyclerView mRecycleview;
    Unbinder unbinder;

    public MoviesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getPresenter().getHotMovie();
    }

    @Override
    public MoviewPresenter createPresenter() {
        return new MoviewPresenter();
    }

    @Override
    public MovieView createView() {
        return this;
    }


    @Override
    public void onSuccess(Object str) {
        HotMoviesInfo info = (HotMoviesInfo) str;
        for (int i = 0; i < info.getSubjects().size(); i++) {
            LogUtils.e(info.getSubjects().get(i).getTitle());
        }
    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
