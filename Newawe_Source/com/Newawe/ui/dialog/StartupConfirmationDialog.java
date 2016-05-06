package com.Newawe.ui.dialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import com.Newawe.C0186R;
import com.Newawe.MainNavigationActivity;
import java.util.HashMap;

public class StartupConfirmationDialog {
    private static final String PREFERENCES_PREFIX;
    private static volatile boolean _onScreen;
    private MainNavigationActivity _activity;
    private AlertDialog _alertDialog;
    private HashMap<String, StartupConfirmationDialogActionListener> listeners;

    /* renamed from: com.Newawe.ui.dialog.StartupConfirmationDialog.1 */
    class C02631 implements OnClickListener {
        C02631() {
        }

        public void onClick(DialogInterface dialog, int id) {
            StartupConfirmationDialog._onScreen = false;
            StartupConfirmationDialog.this._notifyAllListeners(ListenersMethods.ON_NEGATIVE_BUTTON_CLICK);
            StartupConfirmationDialog.this._activity.finish();
        }
    }

    /* renamed from: com.Newawe.ui.dialog.StartupConfirmationDialog.2 */
    class C02642 implements OnClickListener {
        C02642() {
        }

        public void onClick(DialogInterface dialog, int id) {
            StartupConfirmationDialog._onScreen = false;
            StartupConfirmationDialog.this._markAsAlreadyShown();
            StartupConfirmationDialog.this._notifyAllListeners(ListenersMethods.ON_POSITIVE_BUTTON_CLICK);
            dialog.dismiss();
        }
    }

    private enum ListenersMethods {
        ON_POSITIVE_BUTTON_CLICK,
        ON_NEGATIVE_BUTTON_CLICK
    }

    static {
        _onScreen = false;
        PREFERENCES_PREFIX = StartupConfirmationDialog.class.getSimpleName() + "AlreadyShown";
    }

    public StartupConfirmationDialog(MainNavigationActivity mainActivity) {
        this.listeners = null;
        this._activity = mainActivity;
        Builder alertDialogBuilder = new Builder(this._activity);
        alertDialogBuilder.setTitle(C0186R.string.startupConfirmationDialogTitle).setMessage(C0186R.string.startupConfirmationDialogMessage).setCancelable(false).setPositiveButton(C0186R.string.startupConfirmationDialogAcceptButtonCaption, new C02642()).setNegativeButton(C0186R.string.startupConfirmationDialogRefuseButtonCaption, new C02631()).setInverseBackgroundForced(true);
        this._alertDialog = alertDialogBuilder.create();
    }

    public void show() {
        if (this._alertDialog != null) {
            _onScreen = true;
            this._alertDialog.show();
        }
    }

    public static boolean isOnScreen() {
        return _onScreen;
    }

    public void addActionListener(String key, StartupConfirmationDialogActionListener listener) {
        if (this.listeners == null) {
            this.listeners = new HashMap();
        }
        this.listeners.put(key, listener);
    }

    private void _markAsAlreadyShown() {
        Editor editor = this._activity.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).edit();
        editor.putBoolean(PREFERENCES_PREFIX, true);
        editor.commit();
    }

    public static boolean isAlreadyShown(MainNavigationActivity _activity) {
        if (_activity == null) {
            return false;
        }
        boolean alreadyShown = false;
        try {
            return _activity.getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).getBoolean(PREFERENCES_PREFIX, false);
        } catch (Exception e) {
            e.printStackTrace();
            return alreadyShown;
        }
    }

    private void _notifyAllListeners(ListenersMethods method) {
        if (this.listeners != null) {
            for (String key : this.listeners.keySet()) {
                StartupConfirmationDialogActionListener listener = (StartupConfirmationDialogActionListener) this.listeners.get(key);
                if (method.equals(ListenersMethods.ON_POSITIVE_BUTTON_CLICK)) {
                    listener.onPositiveButtonClick();
                } else if (method.equals(ListenersMethods.ON_NEGATIVE_BUTTON_CLICK)) {
                    listener.onNegativeButtonClick();
                }
            }
        }
    }
}
