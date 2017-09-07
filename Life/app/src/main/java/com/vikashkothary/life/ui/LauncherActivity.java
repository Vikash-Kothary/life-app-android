package com.vikashkothary.life.ui;

import android.os.Bundle;

import com.vikashkothary.life.data.DataManager;
import com.vikashkothary.life.ui.base.BaseActivity;

import javax.inject.Inject;

import static com.vikashkothary.life.ui.login.LoginActivity.startLogin;
import static com.vikashkothary.life.ui.main.MainActivity.startMain;

/**
 * Created by vikash on 10/07/17.
 */

public class LauncherActivity extends BaseActivity {

    @Inject
    DataManager mDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);

        startMain(this);
        if (! mDataManager.getPreferencesHelper().getUserSignedIn()) {
            // if not logged in, show login activity
            startLogin(this);
        }
        finish();
    }
}
