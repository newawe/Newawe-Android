package com.Newawe.ads.behavior.activityBehaviors;

import android.app.Activity;
import android.content.SharedPreferences.Editor;
import com.Newawe.MainNavigationActivity;
import java.util.Date;

public class AdsSleepBehavior extends ActivityVisitor {
    private final long ONE_HUNDRED_YEARS;
    private float _timeOut;

    public AdsSleepBehavior(float timeout) {
        this.ONE_HUNDRED_YEARS = 1094004736;
        this._timeOut = timeout;
    }

    public float getTimeout() {
        return this._timeOut;
    }

    public void visit(Activity activity) {
        _saveSleepDateToSettigns(activity);
    }

    private void _saveSleepDateToSettigns(Activity activity) {
        Editor editor = activity.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).edit();
        Date now = new Date();
        long timeout = 1094004736;
        if (this._timeOut != Float.POSITIVE_INFINITY) {
            timeout = 1000 * ((long) this._timeOut);
        }
        editor.putLong(MainNavigationActivity.ADS_SLEEP_PARAM, new Date(now.getTime() + timeout).getTime());
        editor.commit();
    }
}
