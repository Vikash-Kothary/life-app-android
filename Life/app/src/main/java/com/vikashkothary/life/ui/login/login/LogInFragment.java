package com.vikashkothary.life.ui.login.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;

import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseActivity;
import com.vikashkothary.life.util.DialogFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogInFragment extends Fragment {

    @BindView(R.id.email)
    AutoCompleteTextView mEmailView;
    @BindView(R.id.password)
    EditText mPasswordView;
    @BindView(R.id.login_progress)
    View mProgressView;
    @BindView(R.id.login_form)
    View mLoginFormView;

    public static LogInFragment newInstance() {
        LogInFragment fragment = new LogInFragment();
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
        View fragmentView = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, fragmentView);

        return fragmentView;
    }

    public void handleOptionsItem(int itemId) {
        switch (itemId) {
            case R.id.action_info:
                DialogFactory.createInfoDialog(getActivity(), getString(R.string.info_login)).show();
                break;
        }
    }

}
