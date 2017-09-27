package keven.cihon.mvpcsdnapp.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import keven.cihon.mvpcsdnapp.R;
import keven.cihon.mvpcsdnapp.activity.LoginActivity;
import keven.cihon.mvpcsdnapp.activity.Movie2PlayerActivity;
import keven.cihon.mvpcsdnapp.book.fragment.BooksFragment;
import keven.cihon.mvpcsdnapp.movie.fragment.MoviesFragment;
import keven.cihon.mvpcsdnapp.utils.PrefUtils;

import static keven.cihon.mvpcsdnapp.R.id.viewPager;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.douban_sliding_tabs)
    TabLayout mDoubanSlidingTabs;
    @BindView(viewPager)
    ViewPager mViewPager;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Hello Keven", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        mViewPager.setOffscreenPageLimit(2);
        //初始化viewpager
        setupViewPager(mViewPager);
    }

    public void setupViewPager(ViewPager viewPager) {
        DouBanPagerAdapter douBanPagerAdapter = new DouBanPagerAdapter(getSupportFragmentManager());
        douBanPagerAdapter.addFragment(new MoviesFragment(), getApplicationContext().getResources().getString(R.string.movies));
        douBanPagerAdapter.addFragment(new BooksFragment(), getApplicationContext().getResources().getString(R.string.books));
        viewPager.setAdapter(douBanPagerAdapter);


        if (mDoubanSlidingTabs != null) {
            mDoubanSlidingTabs.addTab(mDoubanSlidingTabs.newTab());
            mDoubanSlidingTabs.addTab(mDoubanSlidingTabs.newTab());
            mDoubanSlidingTabs.setupWithViewPager(viewPager);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action


        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(MainActivity.this, Movie2PlayerActivity.class));
        } else if (id == R.id.nav_slideshow) {
//            startActivity(new Intent(MainActivity.this, LEDActivity.class));
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mNavigationView.setCheckedItem(R.id.nav_camera);
        View headerView = mNavigationView.getHeaderView(0);
        ImageView iv = (ImageView) headerView.findViewById(R.id.iv_header);
        TextView tv = (TextView) headerView.findViewById(R.id.tv_name);
        String name=PrefUtils.getString(this,"nickname","csdn");
        String img=PrefUtils.getString(this,"icon","https://ss0.baidu.com/73t1bjeh1BF3odCf/it/u=1564313258,1856489102&fm=85&s=CD0034727CD4C49A4C54F4DA0200E0B4");
        Picasso.with(this).load(img)
                .placeholder(getResources().getDrawable(R.mipmap.ic_launcher))
                .into(iv);
        tv.setText(name);
    }

    static class DouBanPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mTitles = new ArrayList<>();

        public DouBanPagerAdapter(FragmentManager fm) {
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
