package com.google.android.gms.gcm;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.iid.InstanceID;
import java.io.IOException;
import java.util.regex.Pattern;

public class GcmPubSub {
    private static GcmPubSub zzaLE;
    private static final Pattern zzaLG;
    private InstanceID zzaLF;

    static {
        zzaLG = Pattern.compile("/topics/[a-zA-Z0-9-_.~%]{1,900}");
    }

    private GcmPubSub(Context context) {
        this.zzaLF = InstanceID.getInstance(context);
    }

    public static synchronized GcmPubSub getInstance(Context context) {
        GcmPubSub gcmPubSub;
        synchronized (GcmPubSub.class) {
            if (zzaLE == null) {
                zzaLE = new GcmPubSub(context);
            }
            gcmPubSub = zzaLE;
        }
        return gcmPubSub;
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void subscribe(String registrationToken, String topic, Bundle extras) throws IOException {
        if (registrationToken == null || registrationToken.isEmpty()) {
            throw new IllegalArgumentException("Invalid appInstanceToken: " + registrationToken);
        } else if (topic == null || !zzaLG.matcher(topic).matches()) {
            throw new IllegalArgumentException("Invalid topic name: " + topic);
        } else {
            if (extras == null) {
                extras = new Bundle();
            }
            extras.putString("gcm.topic", topic);
            this.zzaLF.getToken(registrationToken, topic, extras);
        }
    }

    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void unsubscribe(String registrationToken, String topic) throws IOException {
        Bundle bundle = new Bundle();
        bundle.putString("gcm.topic", topic);
        this.zzaLF.zzb(registrationToken, topic, bundle);
    }
}
