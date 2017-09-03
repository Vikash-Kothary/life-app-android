package com.vikashkothary.life.ui;

import android.os.Bundle;

import com.vikashkothary.life.ui.base.BaseActivity;

import static com.vikashkothary.life.ui.main.MainActivity.startMain;

/**
 * Created by vikash on 10/07/17.
 */

public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        // if signed in
        startMain(this);
        // else show signin activity
        finish();
    }
}
