package keven.cihon.mvpcsdnapp.movie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import keven.cihon.mvpcsdnapp.R;
import keven.cihon.mvpcsdnapp.movie.bean.Movie;
import keven.cihon.mvpcsdnapp.movie.fragment.MovieDetailFragment;

public class MovieDetailActivity extends AppCompatActivity {

    @BindView(R.id.movie_image)
    ImageView mMovieImage;
    @BindView(R.id.movie_toolbar)
    Toolbar mMovieToolbar;
    @BindView(R.id.movie_collapsing_toolbar)
    CollapsingToolbarLayout mMovieCollapsingToolbar;
    @BindView(R.id.movie_sliding_tabs)
    TabLayout mMovieSlidingTabs;
    @BindView(R.id.movie_viewpager)
    ViewPager mMovieViewpager;
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mMovie = (Movie) intent.getSerializableExtra("movie");
        initView();
    }
    //初始化控件
    private void initView() {
        Picasso.with(this).load(mMovie.getImages().getLarge())
                .placeholder(getResources().getDrawable(R.mipmap.ic_launcher))
                .into(mMovieImage);

        mMovieCollapsingToolbar.setTitle(mMovie.getTitle());

        setSupportActionBar(mMovieToolbar);
        ActionBar actionBar =  getSupportActionBar();
        if(actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }

        setupViewPager(mMovieViewpager);



    }

    /**
     * 初始化viewpager
     * @param viewPager
     */
    public void setupViewPager(ViewPager viewPager){
       String d= mMovie.getDirectors().get(0).getName();//导演
       String m= mMovie.getCasts().get(0).getName();//主演
        String year = mMovie.getYear();//年限
        String original_title = mMovie.getTitle();//名称
        MovieDetailFragment f1=new MovieDetailFragment();
        f1.setMessage("导演："+d+"\n"+"主演："+m);
        MovieDetailFragment f2=new MovieDetailFragment();
        f2.setMessage(original_title+"\n上映于"+year);
        MovieDetailPagerAdapter douBanPagerAdapter = new MovieDetailPagerAdapter(getSupportFragmentManager());
        douBanPagerAdapter.addFragment(f1,"影片信息");
        douBanPagerAdapter.addFragment(f2,"简介");
        viewPager.setAdapter(douBanPagerAdapter);


        if(mMovieSlidingTabs!=null){
            mMovieSlidingTabs.addTab(mMovieSlidingTabs.newTab());
            mMovieSlidingTabs.addTab(mMovieSlidingTabs.newTab());
            mMovieSlidingTabs.setupWithViewPager(viewPager);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    static class MovieDetailPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mTitles = new ArrayList<>();

        public MovieDetailPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fm, String title) {

            mFragmentList.add(fm);
            mTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }
    }
}
