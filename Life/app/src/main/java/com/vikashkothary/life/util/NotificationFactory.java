package com.vikashkothary.life.util;

import android.app.Notification;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationManagerCompat;

import com.vikashkothary.life.R;

public final class NotificationFactory {

    private static final int NOTIFICATION_ID = 0;

    public static void createSimpleNotification(Context context, String title, String message) {
        Notification.Builder notification = new Notification.Builder(context)
                .setContentText(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher);
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        managerCompat.notify(NOTIFICATION_ID, notification.build());
    }

    public static void createSimpleNotification(Context context,
                                                @StringRes int titleResource,
                                                @StringRes int messageResource) {

        createSimpleNotification(context,
                context.getString(titleResource),
                context.getString(messageResource));
    }
}
