package com.vikashkothary.life.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseActivity;
import com.vikashkothary.life.ui.login.login.LogInFragment;
import com.vikashkothary.life.ui.login.register.RegisterFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    private LoginActivity.TabPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_pager_container)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    public static void startLogin(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_tabbed);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

        mSectionsPagerAdapter = new LoginActivity.TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(mSectionsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Fragment currentFragment = mSectionsPagerAdapter != null ? mSectionsPagerAdapter.getItem(mViewPager.getCurrentItem()) : null;
        if (currentFragment instanceof RegisterFragment) {
            ((RegisterFragment) currentFragment).handleOptionsItem(item.getItemId());
            return true;
        } else if (currentFragment instanceof LogInFragment) {
            ((LogInFragment) currentFragment).handleOptionsItem(item.getItemId());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class TabPagerAdapter extends FragmentPagerAdapter implements ViewPager.OnPageChangeListener {

        private final int TAB_LOGIN = 0;
        private final int TAB_REGISTER = 1;

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // Set fragments
            switch (position) {
                case TAB_LOGIN:
                    return LogInFragment.newInstance();
                case TAB_REGISTER:
                    return RegisterFragment.newInstance();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Set Tab titles
            switch (position) {
                case TAB_LOGIN:
                    return getString(R.string.tab_login);
                case TAB_REGISTER:
                    return getString(R.string.tab_register);
                default:
                    return null;
            }
        }

        /***** ViewPager.OnPageChangeListener implementation *****/

        @Override
        public void onPageSelected(int position) {
            // Set Toolbar title
            switch (position) {
                case TAB_LOGIN:
                    setTitle(R.string.title_fragment_login);
                    break;
                case TAB_REGISTER:
                    setTitle(R.string.title_fragment_register);
                    break;
                default:
                    setTitle(R.string.app_name);
                    break;
            }

        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
}

