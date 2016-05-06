package com.Newawe.ui.dialog;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import com.Newawe.C0186R;

public class SimpleDialogs {

    /* renamed from: com.Newawe.ui.dialog.SimpleDialogs.1 */
    static class C02571 implements OnClickListener {
        final /* synthetic */ OnClickListener val$onYesListener;

        C02571(OnClickListener onClickListener) {
            this.val$onYesListener = onClickListener;
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            if (this.val$onYesListener != null) {
                this.val$onYesListener.onClick(dialog, which);
            }
        }
    }

    /* renamed from: com.Newawe.ui.dialog.SimpleDialogs.2 */
    static class C02582 implements OnClickListener {
        final /* synthetic */ OnClickListener val$onNoListener;

        C02582(OnClickListener onClickListener) {
            this.val$onNoListener = onClickListener;
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
            if (this.val$onNoListener != null) {
                this.val$onNoListener.onClick(dialog, which);
            }
        }
    }

    /* renamed from: com.Newawe.ui.dialog.SimpleDialogs.3 */
    static class C02593 implements OnCancelListener {
        final /* synthetic */ OnClickListener val$onNoListener;

        C02593(OnClickListener onClickListener) {
            this.val$onNoListener = onClickListener;
        }

        public void onCancel(DialogInterface dialog) {
            dialog.dismiss();
            if (this.val$onNoListener != null) {
                this.val$onNoListener.onClick(dialog, 0);
            }
        }
    }

    /* renamed from: com.Newawe.ui.dialog.SimpleDialogs.4 */
    static class C02604 implements OnClickListener {
        C02604() {
        }

        public void onClick(DialogInterface dialog, int which) {
            dialog.cancel();
        }
    }

    public static AlertDialog createConfirmDialog(String title, String message, Context context, OnClickListener onYesListener, OnClickListener onNoListener) {
        Builder builder = new Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton(context.getString(C0186R.string.yes), new C02571(onYesListener));
        builder.setNegativeButton(context.getString(C0186R.string.no), new C02582(onNoListener));
        builder.setOnCancelListener(new C02593(onNoListener));
        return builder.create();
    }

    public static void showOpenOrSaveDialog(Context context, OnClickListener open, OnClickListener save) {
        AlertDialog dialog = new Builder(context).create();
        dialog.setTitle(context.getString(C0186R.string.dialog_select_action));
        dialog.setButton(-1, context.getString(C0186R.string.open_file), open);
        dialog.setButton(-3, context.getString(C0186R.string.save_file), save);
        dialog.setButton(-2, context.getString(C0186R.string.cancel), new C02604());
        dialog.setCancelable(true);
        dialog.show();
    }
}
