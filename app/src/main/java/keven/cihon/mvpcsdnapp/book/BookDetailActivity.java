package keven.cihon.mvpcsdnapp.book;

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
import keven.cihon.mvpcsdnapp.book.bean.Book;
import keven.cihon.mvpcsdnapp.book.fragment.BookDetailFragment;

public class BookDetailActivity extends AppCompatActivity {

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
    private Book mBook;

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
        setContentView(R.layout.activity_book_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        mBook = (Book) intent.getSerializableExtra("book");
        initView();
    }

    private void initView() {
        Picasso.with(this).load(mBook.getImages().getLarge())
                .placeholder(getResources().getDrawable(R.mipmap.ic_launcher))
                .into(mMovieImage);

        mMovieCollapsingToolbar.setTitle(mBook.getTitle());

        setSupportActionBar(mMovieToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
        }
        setupViewPager(mMovieViewpager);
    }

    private void setupViewPager(ViewPager movieViewpager) {
        String content_info = mBook.getSummary();
        String author_intro = mBook.getAuthor_intro();
        String catalog = mBook.getCatalog();
        BookDetailFragment f1=new BookDetailFragment();
        BookDetailFragment f2=new BookDetailFragment();
        BookDetailFragment f3=new BookDetailFragment();
        f1.setMessage(content_info);
        f2.setMessage(author_intro);
        f3.setMessage(catalog);

        BookDetailPagerAdapter pagerAdapter=new BookDetailPagerAdapter(getSupportFragmentManager());
        pagerAdapter.addFragment(f1,"内容简介");
        pagerAdapter.addFragment(f2,"作者简介");
        pagerAdapter.addFragment(f3,"目录");
        movieViewpager.setAdapter(pagerAdapter);

        if(mMovieSlidingTabs!=null){
            mMovieSlidingTabs.addTab(mMovieSlidingTabs.newTab());
            mMovieSlidingTabs.addTab(mMovieSlidingTabs.newTab());
            mMovieSlidingTabs.setupWithViewPager(mMovieViewpager);
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

    static class BookDetailPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mTitles = new ArrayList<>();

        public BookDetailPagerAdapter(FragmentManager fm) {
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
