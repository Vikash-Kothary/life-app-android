package com.vikashkothary.life.services;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.vikashkothary.life.LifeApplication;

import static com.vikashkothary.life.services.NotificationService.showNotification;
import static com.vikashkothary.life.util.NotificationFactory.createSimpleNotification;

/**
 * Created by vikash on 10/07/17.
 */

public class OnBootReceiver extends BroadcastReceiver {

//    @Inject DataManager mDataManager;

    @Override
    public void onReceive(Context context, Intent intent) {
        LifeApplication.get(context).getComponent().inject(this);
//        if (mDataManager.getPreferencesHelper().getAccessToken() != null) {
//            context.startService(AutoCheckInService.getStartIntent(context));
//        }
        // TODO: Start background services
        switch (intent.getAction()) {
            case "android.intent.action.BOOT_COMPLETED":
                Notification n = createSimpleNotification(context, "Life", "Boot");
                showNotification(context, 0, n);
                break;
        }
    }
}
