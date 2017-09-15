package keven.cihon.mvpcsdnapp.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import keven.cihon.mvpcsdnapp.movie.bean.Movie;

/**
 * Created by zhengjian on 2017/9/15.
 */

public class HotMoviesAdapter extends RecyclerView.Adapter<HotMoviesAdapter.HotMoviesViewHodler> {
    private List<Movie> mMovieList;
    private Context mContext;
    @LayoutRes
    private int layoutResId;

    public HotMoviesAdapter(Context context, @NonNull List<Movie> movieList, @LayoutRes int layoutResId) {
        this.mContext = context;
        this.mMovieList = movieList;
        this.layoutResId = layoutResId;
    }

    @Override
    public HotMoviesViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(HotMoviesViewHodler holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class HotMoviesViewHodler extends RecyclerView.ViewHolder {

        public HotMoviesViewHodler(View itemView) {
            super(itemView);
        }
    }
}
