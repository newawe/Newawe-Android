package com.Newawe.push;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.Newawe.configuration.ConfigResolver;
import com.Newawe.server.PushServerClient;
import com.google.android.gcm.GCMBaseIntentService;

public class PushGCMIntentService extends GCMBaseIntentService {
    private static final String TAG = "GCMIntentService";

    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
        new PushServerClient(context, ConfigResolver.resolveConfig(context)).sendRegisteredId(registrationId);
    }

    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
    }

    protected void onMessage(Context context, Intent intent) {
        Log.i(TAG, "Received message");
        PushNotificationsExecutor.executeNotification(context, intent);
    }

    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted messages notification");
    }

    protected void onError(Context context, String errorId) {
        Log.e(TAG, "Messaging registration error: " + errorId);
    }

    protected boolean onRecoverableError(Context context, String errorId) {
        Log.i(TAG, "Received recoverable error: " + errorId);
        return super.onRecoverableError(context, errorId);
    }
}
