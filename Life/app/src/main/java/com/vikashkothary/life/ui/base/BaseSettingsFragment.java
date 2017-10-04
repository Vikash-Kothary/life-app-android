package com.vikashkothary.life.ui.base;

import android.support.annotation.IdRes;
import android.support.v7.preference.PreferenceFragmentCompat;

import com.vikashkothary.life.R;

/**
 * Created by vikash on 19/07/17.
 */

public abstract class BaseSettingsFragment extends PreferenceFragmentCompat {

    public void attachFragment(BaseActivity activity) {
        attachFragment(activity, R.id.fragment_container);
    }

    public void attachFragment(BaseActivity activity, @IdRes int layoutId) {
        activity.getSupportFragmentManager().beginTransaction()
                .replace(layoutId, this)
                .addToBackStack(getTag())
                .commit();
    }



}
