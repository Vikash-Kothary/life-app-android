package com.vikashkothary.life.ui.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseActivity;
import com.vikashkothary.life.ui.base.BaseSettingsFragment;
import com.vikashkothary.life.ui.settings.general.GeneralFragment;
import com.vikashkothary.life.ui.settings.permissions.PermissionsFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by vikash on 20/07/17.
 */

public class SettingsActivity extends BaseActivity {

    private BaseSettingsFragment mFragment;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startSettings(Context context) {
        Intent intent = new Intent(context, SettingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            mFragment = GeneralFragment.newInstance();
            mFragment.attachFragment(this);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_settings, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mFragment instanceof GeneralFragment) {
            ((GeneralFragment) mFragment).handleOptionsItem(item.getItemId());
            return true;
        } else if (mFragment instanceof PermissionsFragment) {
            ((PermissionsFragment) mFragment).handleOptionsItem(item.getItemId());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
