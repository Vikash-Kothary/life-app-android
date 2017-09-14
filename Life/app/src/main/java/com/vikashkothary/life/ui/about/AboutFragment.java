package com.vikashkothary.life.ui.about;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by vikash on 14/09/17.
 */

public class AboutFragment extends BaseFragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    public AboutFragment() {
    }

    public static AboutFragment newInstance(int sectionNumber) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ((BaseActivity) getActivity()).activityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, fragmentView);

        TextView textView = (TextView) fragmentView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));

        return fragmentView;
    }
}