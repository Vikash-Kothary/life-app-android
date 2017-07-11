package com.vikashkothary.life.ui;

import android.content.Intent;
import android.os.Bundle;

import com.vikashkothary.life.ui.base.BaseActivity;
import com.vikashkothary.life.ui.main.MainActivity;

/**
 * Created by vikash on 10/07/17.
 */

public class LauncherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        Intent intent;
        // if signed in
        intent = MainActivity.getStartIntent(this, false);
        // else show signin activity
        startActivity(intent);
        finish();
    }
}
