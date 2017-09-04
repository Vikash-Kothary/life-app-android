package com.vikashkothary.life.util;

import android.app.Notification;
import android.content.Context;
import android.support.annotation.StringRes;

import com.vikashkothary.life.R;

public final class NotificationFactory {

    public static Notification createSimpleNotification(Context context, String title, String message) {
        Notification.Builder notification = new Notification.Builder(context)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.mipmap.ic_launcher);
        return notification.build();
    }

    public static Notification createSimpleNotification(Context context,
                                                @StringRes int titleResource,
                                                @StringRes int messageResource) {

        return createSimpleNotification(context,
                context.getString(titleResource),
                context.getString(messageResource));
    }
}
