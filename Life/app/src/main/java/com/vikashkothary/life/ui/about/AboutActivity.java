package com.vikashkothary.life.ui.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseActivity;
import com.vikashkothary.life.util.DialogFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AboutActivity extends BaseActivity {

    private TabPagerAdapter mSectionsPagerAdapter;

    @BindView(R.id.toolbar) Toolbar mToolbar;
    @BindView(R.id.view_pager_container) ViewPager mViewPager;
    @BindView(R.id.tabs) TabLayout mTabLayout;

    public static void startAbout(Context context) {
        Intent intent = new Intent(context, AboutActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_tabbed);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionsPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @OnClick(R.id.fab)
    public void onClick(View view) {
        Snackbar.make(view, "Share", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.options_about, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_info:
                DialogFactory.createInfoDialog(this, R.string.info_about);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public class TabPagerAdapter extends FragmentPagerAdapter {

        private final int TAB_ABOUT = 0;
        private final int TAB_LIBARIES = 1;

        public TabPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case TAB_ABOUT:
                    return AboutFragment.newInstance(TAB_ABOUT);
                case TAB_LIBARIES:
                    return AboutFragment.newInstance(TAB_LIBARIES);
//                    return new LibsBuilder()
//                            //Pass the fields of your application to the lib so it can find all external lib information
//                            .withFields(R.string.class.getFields())
//                            .withActivityStyle(Libs.ActivityStyle.LIGHT_DARK_TOOLBAR)
//                            .withAboutAppName(getString(R.string.app_name))
//                            .withAboutDescription("<h2>Used Libraries</h2>")
//                            .withAboutIconShown(true)
//                            .withAboutVersionShown(true)
//                            .withAboutVersionShownCode(true)
//                            .withAboutVersionShownName(true)
//                            .supportFragment();
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
            switch (position) {
                case TAB_ABOUT:
                    return getString(R.string.tab_about);
                case TAB_LIBARIES:
                    return getString(R.string.tab_libraries);
                default:
                    return null;
            }
        }
    }
}
