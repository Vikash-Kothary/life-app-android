package com.vikashkothary.life.ui.creation;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreationActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
