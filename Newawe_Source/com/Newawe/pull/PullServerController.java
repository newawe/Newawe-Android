package com.Newawe.pull;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.Newawe.MainNavigationActivity;
import com.Newawe.notification.AppNotificationManager;
import com.Newawe.pull.PullServerClient.Response;
import com.Newawe.pull.PullServerClient.onMessageReceivedListener;

public class PullServerController extends BroadcastReceiver implements onMessageReceivedListener {
    private final long ALARM_PERIOD;
    private final String LAST_ALARM_TIME_KEY;
    private Context _context;

    public PullServerController() {
        this.LAST_ALARM_TIME_KEY = "last_alarm_time";
        this.ALARM_PERIOD = 14400000;
        this._context = null;
    }

    public void onReceive(Context context, Intent intent) {
        this._context = context;
        long lastAlarmTime = _getLastAlarmTime(context);
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastAlarmTime >= 14400000) {
            _setLastAlarmTime(context, currentTime);
            if (lastAlarmTime != 0) {
                _pullMessage(context);
            }
        }
        reScheduleNotification(context);
    }

    public void reScheduleNotification(Context context) {
        this._context = context;
        long lastAlarmTime = _getLastAlarmTime(context);
        if (lastAlarmTime < System.currentTimeMillis() - 14400000) {
            lastAlarmTime = System.currentTimeMillis();
        }
        Intent intent = new Intent(context, PullServerController.class);
        PendingIntent pendingIntent0 = PendingIntent.getBroadcast(context, 0, intent, 268435456);
        PendingIntent pendingIntent1 = PendingIntent.getBroadcast(context, 1, intent, 268435456);
        PendingIntent pendingIntent2 = PendingIntent.getBroadcast(context, 2, intent, 268435456);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompatApi21.CATEGORY_ALARM);
        alarmManager.cancel(pendingIntent0);
        alarmManager.cancel(pendingIntent1);
        alarmManager.cancel(pendingIntent2);
        alarmManager.set(0, lastAlarmTime + 14400000, pendingIntent0);
        alarmManager.set(0, 28800000 + lastAlarmTime, pendingIntent1);
        alarmManager.set(0, 172800000 + lastAlarmTime, pendingIntent2);
    }

    private void _pullMessage(Context context) {
        new PullServerClient(context, this).tryLoadMessageAsync();
    }

    private long _getLastAlarmTime(Context context) {
        return context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).getLong("last_alarm_time", 0);
    }

    private void _setLastAlarmTime(Context context, long newTime) {
        context.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).edit().putLong("last_alarm_time", newTime).commit();
    }

    public void onMessage(Response[] responses) {
        for (Response r : responses) {
            String url = r.url;
            String message = r.message;
            String title = r.title;
            AppNotificationManager.generateNotification(this._context, message, title, AppNotificationManager.getLaunchIntent(this._context, title, url, r.launchMain));
            new StatServerClient(this._context).sendMessageAcceptedAsync(url);
        }
    }
}
