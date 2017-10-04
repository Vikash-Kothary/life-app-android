package com.vikashkothary.life.ui.login.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseActivity;
import com.vikashkothary.life.util.DialogFactory;

import butterknife.ButterKnife;

public class RegisterFragment extends Fragment {

    public static RegisterFragment newInstance() {
        RegisterFragment fragment = new RegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        ((BaseActivity) getActivity()).activityComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }

    public void handleOptionsItem(int itemId) {
        switch (itemId) {
            case R.id.action_info:
                DialogFactory.createInfoDialog(getActivity(), getString(R.string.info_register)).show();
                break;
        }
    }

}
