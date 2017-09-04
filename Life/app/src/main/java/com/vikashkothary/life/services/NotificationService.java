package com.vikashkothary.life.services;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;

import com.vikashkothary.life.util.AndroidComponentUtil;


public class NotificationService extends IntentService {
    private static final String ACTION_SHOW = "com.vikashkothary.life.services.action.SHOW";
    private static final String ACTION_HIDE = "com.vikashkothary.life.services.action.HIDE";
    private static final String ACTION_SCHEDULE = "com.vikashkothary.life.services.action.SCHEDULE";
    private static final String ACTION_SCHEDULE_CANCEL = "com.vikashkothary.life.services.action.SCHEDULE_CANCEL";

    private static final String EXTRA_ID = "com.vikashkothary.life.services.extra.ID";
    private static final String EXTRA_NOTIFICATION = "com.vikashkothary.life.services.extra.NOTIFICATION";

    public NotificationService() {
        super("NotificationService");
    }

    public static void showNotification(Context context, int id, Notification notification) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_SHOW);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(EXTRA_NOTIFICATION, notification);
        context.startService(intent);
    }

    public static void hideNotification(Context context, int id, Notification notification) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_HIDE);
        intent.putExtra(EXTRA_ID, id);
        intent.putExtra(EXTRA_NOTIFICATION, notification);
        context.startService(intent);
    }

    public static boolean isRunning(Context context) {
        return AndroidComponentUtil.isServiceRunning(context, NotificationService.class);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        LifeApplication.get(this).getComponent().inject(this);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            final int id = intent.getIntExtra(EXTRA_ID, 0);
            final Notification notification = intent.getParcelableExtra(EXTRA_NOTIFICATION);
            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
            if (ACTION_SHOW.equals(action)) {
                managerCompat.notify(id, notification);
            } else if (ACTION_HIDE.equals(action)) {
                managerCompat.cancel(id);
            }
        }
    }

    public static class NotificationScheduler extends BroadcastReceiver {

        public static void scheduleNotification(Context context, int id, Notification notification, long datetime) {
            Intent intent = new Intent(context, NotificationScheduler.class);
            intent.setAction(ACTION_SCHEDULE);
            intent.putExtra(EXTRA_ID, id);
            intent.putExtra(EXTRA_NOTIFICATION, notification);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP, datetime, pendingIntent);
        }

        public static void cancelScheduledNotification(Context context, long id, Notification notification) {
            Intent intent = new Intent(context, NotificationScheduler.class);
            intent.setAction(ACTION_SCHEDULE_CANCEL);
            intent.putExtra(EXTRA_ID, id);
            intent.putExtra(EXTRA_NOTIFICATION, notification);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.cancel(pendingIntent);
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (ACTION_SCHEDULE.equals(intent.getAction())) {
                final int id = intent.getIntExtra(EXTRA_ID, 0);
                final Notification notification = intent.getParcelableExtra(EXTRA_NOTIFICATION);
                NotificationService.showNotification(context, id, notification);
            }
        }

    }

}
