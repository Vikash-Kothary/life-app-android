package com.vikashkothary.life.ui.creation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseActivity;
import com.vikashkothary.life.ui.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class CreationFragment extends BaseFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseActivity) getActivity()).activityComponent().inject(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_creation, container, false);
        ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }
}
