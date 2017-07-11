package com.vikashkothary.life.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.vikashkothary.life.LifeApplication;
import com.vikashkothary.life.data.DataManager;

import javax.inject.Inject;

/**
 * Created by vikash on 10/07/17.
 */

public class OnBootReceiver extends BroadcastReceiver {

    @Inject DataManager mDataManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        LifeApplication.get(context).getComponent().inject(this);
//        if (mDataManager.getPreferencesHelper().getAccessToken() != null) {
//            context.startService(AutoCheckInService.getStartIntent(context));
//        }
        // TODO: Start background services
    }
}
