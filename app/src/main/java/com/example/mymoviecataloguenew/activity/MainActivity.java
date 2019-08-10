package com.example.mymoviecataloguenew.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mymoviecataloguenew.R;
import com.example.mymoviecataloguenew.adap.PagerAdapter;
import com.example.mymoviecataloguenew.base.BaseAppCompatActivity;
import com.example.mymoviecataloguenew.fragment.MovieFragment;

public class MainActivity extends BaseAppCompatActivity {
    Toolbar toolbar;
    TabLayout tabLayout;
    TabItem tabMovies;
    TabItem tabTvShow;
    ViewPager viewPager;
    private Fragment pageContent = new MovieFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tab_layout);
        tabMovies = findViewById(R.id.tabs_movies);
        tabTvShow = findViewById(R.id.tabs_tv_show);
        viewPager = findViewById(R.id.viewPager);

        //for scrolling tab and click
        PagerAdapter pageAdapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle(getString(R.string.app_name));


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.viewPager, pageContent).commit();
        } else {
            pageContent = getSupportFragmentManager().getFragment(savedInstanceState, KEY_FRAGMENT);

            getSupportFragmentManager().beginTransaction().replace(R.id.viewPager, pageContent).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_settings) {
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getSupportFragmentManager().putFragment(outState, KEY_FRAGMENT, pageContent);
        super.onSaveInstanceState(outState);
    }
}
