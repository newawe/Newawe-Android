package com.Newawe.browser;

import android.content.Context;
import android.content.Intent;
import android.net.MailTo;
import android.net.Uri;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.Newawe.ui.dialog.SslErrorDialog;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;

public class SimpleWebViewClient extends WebViewClient {
    protected Context _context;

    public SimpleWebViewClient(Context context) {
        this._context = context;
    }

    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        try {
            if (MailTo.isMailTo(url)) {
                _handleMailTo(url);
                return true;
            } else if (url.startsWith("tel:")) {
                this._context.startActivity(new Intent("android.intent.action.DIAL", Uri.parse(url)));
                return true;
            } else if (url.startsWith("market:") || url.startsWith("geo:")) {
                this._context.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                return true;
            } else if (url.startsWith("smsto:")) {
                _handleSmsTo(url);
                return true;
            } else {
                if (!(url.startsWith("http:") || url.startsWith("https:") || url.startsWith("file:") || url.equals("about:blank"))) {
                    Intent internetIntent = new Intent("android.intent.action.VIEW", Uri.parse(url));
                    if (this._context.getPackageManager().resolveActivity(internetIntent, 0) != null) {
                        this._context.startActivity(internetIntent);
                        return true;
                    }
                }
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void _handleSmsTo(String url) {
        String[] urlParts = url.split(":");
        String phone = urlParts[1];
        String body = StringUtils.EMPTY;
        if (urlParts.length > 1) {
            body = urlParts[2];
        }
        Intent smsIntent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + phone));
        smsIntent.putExtra("address", phone);
        smsIntent.putExtra("sms_body", body);
        this._context.startActivity(smsIntent);
    }

    private void _handleMailTo(String url) {
        MailTo mt = MailTo.parse(url);
        if (mt.getTo().length() > 0) {
            Map<String, String> headers = mt.getHeaders();
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType(HTTP.PLAIN_TEXT_TYPE);
            intent.putExtra("android.intent.extra.EMAIL", new String[]{mt.getTo()});
            intent.putExtra("android.intent.extra.SUBJECT", mt.getSubject());
            intent.putExtra("android.intent.extra.CC", mt.getCc());
            if (headers.containsKey("bcc")) {
                intent.putExtra("android.intent.extra.BCC", (String) headers.get("bcc"));
            }
            intent.putExtra("android.intent.extra.TEXT", mt.getBody());
            this._context.startActivity(intent);
        }
    }

    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
        new SslErrorDialog(this._context).execute(view, handler, error);
    }
}
