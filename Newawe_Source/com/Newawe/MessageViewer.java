package com.Newawe;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.Newawe.pull.PullServerController;
import com.Newawe.push.PushWebViewClient;
import com.Newawe.storage.DatabaseOpenHelper;

public class MessageViewer extends Activity {
    Activity _activity;
    WebView _browser;

    /* renamed from: com.Newawe.MessageViewer.1 */
    class C01841 extends WebChromeClient {
        C01841() {
        }
    }

    /* renamed from: com.Newawe.MessageViewer.2 */
    class C01852 implements OnClickListener {
        C01852() {
        }

        public void onClick(DialogInterface dialog, int which) {
        }
    }

    public MessageViewer() {
        this._activity = null;
        this._browser = null;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this._activity = this;
        setContentView(C0186R.layout.message_viewer);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            setTitle(extras.getString(DatabaseOpenHelper.HISTORY_ROW_TITLE));
            String url = extras.getString(DatabaseOpenHelper.HISTORY_ROW_URL);
            if (url != null) {
                this._browser = (WebView) findViewById(C0186R.id.messageWebView);
                this._browser.loadUrl(url);
                this._browser.setWebViewClient(new PushWebViewClient(url, this));
                this._browser.setWebChromeClient(new C01841());
                WebSettings settings = this._browser.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setAllowFileAccess(true);
                settings.setGeolocationEnabled(true);
                settings.setAppCacheMaxSize(5242880);
                this._browser.setVerticalScrollBarEnabled(false);
                this._browser.setHorizontalScrollBarEnabled(false);
                settings.setLoadWithOverviewMode(true);
                settings.setUseWideViewPort(true);
                this._browser.setInitialScale(0);
            }
        }
        new PullServerController().reScheduleNotification(this);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        this._activity.finish();
        return true;
    }

    protected void onStart() {
        super.onStart();
    }

    protected void onPause() {
        super.onPause();
        this._activity.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        destroyActivity();
    }

    private void destroyActivity() {
        if (this._browser != null) {
            this._browser.destroy();
            this._browser = null;
        }
    }

    public void showMessage(String text) {
        if (this._activity != null) {
            Builder builder = new Builder(this._activity);
            builder.setMessage(text);
            builder.setPositiveButton("ok", new C01852());
            builder.create().show();
        }
    }
}
