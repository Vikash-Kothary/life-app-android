package com.vikashkothary.life.ui.base;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

import com.vikashkothary.life.R;

/**
 * Created by vikash on 19/07/17.
 */

public class BaseFragment extends Fragment {

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
