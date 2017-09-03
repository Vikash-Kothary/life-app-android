package com.vikashkothary.life.ui.creation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vikashkothary.life.R;
import com.vikashkothary.life.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreationActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static void startCreation(Context context) {
        Intent intent = new Intent(context, CreationActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComponent().inject(this);
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_white_24dp);

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_creation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                return false;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
