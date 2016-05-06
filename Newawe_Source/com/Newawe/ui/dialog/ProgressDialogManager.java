package com.Newawe.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import com.Newawe.C0186R;

public class ProgressDialogManager {
    private static Context _context;
    private static ProgressDialogManager instance;
    private ProgressDialog progressDialog;

    public static ProgressDialogManager getInstance(Context context) {
        if (instance == null || !_context.equals(context)) {
            instance = new ProgressDialogManager(context);
            _context = context;
        }
        return instance;
    }

    private ProgressDialogManager(Context context) {
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setCancelable(false);
        this.progressDialog.setMessage(context.getText(C0186R.string.startupScreenTitle));
    }

    public void showProgressDialog() {
        try {
            this.progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissProgressDialog() {
        try {
            this.progressDialog.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
