package com.Newawe.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationCheckerBootReciever extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        new NotificationChecker().rescheduleLaunch(context);
    }
}
