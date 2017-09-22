package keven.cihon.mvpcsdnapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import keven.cihon.mvpcsdnapp.R;
import keven.cihon.mvpcsdnapp.movie.MovieDetailActivity;
import keven.cihon.mvpcsdnapp.movie.bean.Movie;
import keven.cihon.mvpcsdnapp.utils.LogUtils;

/**
 * Created by zhengjian on 2017/9/15.
 */

public class HotMoviesAdapter extends RecyclerView.Adapter<HotMoviesAdapter.HotMoviesViewHodler> {
    private List<Movie> mMovieList=new ArrayList<>();
    private Context mContext;
    @LayoutRes
    private int layoutResId;

    public HotMoviesAdapter(Context context, @LayoutRes int layoutResId) {
        this.mContext = context;
        this.layoutResId = layoutResId;
    }
    public HotMoviesAdapter(Context context, @NonNull List<Movie> movieList, @LayoutRes int layoutResId) {
        this.mContext = context;
        this.mMovieList = movieList;
        this.layoutResId = layoutResId;
    }

    @Override
    public HotMoviesViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(layoutResId, parent, false);

        return new HotMoviesViewHodler(itemView);
    }

    @Override
    public void onBindViewHolder(HotMoviesViewHodler holder, int position) {
        if (holder == null) return;
        holder.updateMovie(mMovieList.get(position));
    }

    public void setData(List<Movie> list) {
        this.mMovieList = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    class HotMoviesViewHodler extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView mTv_name;
        private final ImageView mIv;
        private final RatingBar mRatingBar;
        private final TextView mTv_score;
        private Movie mMovie;

        public HotMoviesViewHodler(View itemView) {
            super(itemView);
            mTv_name = (TextView) itemView.findViewById(R.id.tv_movie);
            mIv = (ImageView) itemView.findViewById(R.id.iv_movie);
            mRatingBar = (RatingBar) itemView.findViewById(R.id.ratingbar);
            mTv_score = (TextView) itemView.findViewById(R.id.tv_score);

            itemView.setOnClickListener(this);
        }

        public void updateMovie(Movie movie) {
            if (movie == null) return;
            this.mMovie = movie;
            Context context = itemView.getContext();

            Picasso.with(context).load(movie.getImages().getLarge())
                    .placeholder(context.getResources().getDrawable(R.mipmap.ic_launcher))
                    .into(mIv);

            mTv_name.setText(movie.getTitle());
            double average = movie.getRating().getAverage();
            if (average == 0.0) {
                mRatingBar.setVisibility(View.GONE);
                mTv_score.setText("暂无评分");
            } else {
                mRatingBar.setVisibility(View.VISIBLE);
                mTv_score.setText(String.valueOf(average));
                mRatingBar.setStepSize(0.5f);
                mRatingBar.setRating((float) (average / 2));
            }
        }

        @Override
        public void onClick(View v) {
            LogUtils.e("点击item");
            if (mMovie == null) return;
            if (itemView == null) return;
            Context context = itemView.getContext();
            if (context == null) return;
            Intent intent = new Intent(context, MovieDetailActivity.class);
            intent.putExtra("movie",mMovie);
            if(context instanceof AppCompatActivity){
                AppCompatActivity activity= (AppCompatActivity) context;
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, mIv, "cover").toBundle();
                ActivityCompat.startActivity(activity, intent, bundle);
            }

        }
    }
}
