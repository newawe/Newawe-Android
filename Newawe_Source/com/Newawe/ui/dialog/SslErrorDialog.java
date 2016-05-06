package com.Newawe.ui.dialog;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import com.Newawe.C0186R;

public class SslErrorDialog {
    private Context _activity;

    /* renamed from: com.Newawe.ui.dialog.SslErrorDialog.1 */
    class C02611 implements OnClickListener {
        final /* synthetic */ SslErrorHandler val$handler;

        C02611(SslErrorHandler sslErrorHandler) {
            this.val$handler = sslErrorHandler;
        }

        public void onClick(DialogInterface dialog, int id) {
            this.val$handler.cancel();
        }
    }

    /* renamed from: com.Newawe.ui.dialog.SslErrorDialog.2 */
    class C02622 implements OnClickListener {
        final /* synthetic */ SslErrorHandler val$handler;

        C02622(SslErrorHandler sslErrorHandler) {
            this.val$handler = sslErrorHandler;
        }

        public void onClick(DialogInterface dialog, int id) {
            this.val$handler.proceed();
        }
    }

    public SslErrorDialog(Context activity) {
        this._activity = activity;
    }

    public void execute(WebView view, SslErrorHandler handler, SslError error) {
        if (VERSION.SDK_INT < 14 || error.getUrl().equals(view.getUrl())) {
            Builder builder = new Builder(this._activity);
            builder.setMessage(C0186R.string.ssl_error_message).setTitle(C0186R.string.ssl_error_title).setPositiveButton(C0186R.string.ssl_error_positive, new C02622(handler)).setNegativeButton(C0186R.string.ssl_error_negative, new C02611(handler));
            builder.create().show();
            return;
        }
        handler.proceed();
    }
}
