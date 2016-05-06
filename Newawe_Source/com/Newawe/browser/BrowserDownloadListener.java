package com.Newawe.browser;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Build.VERSION;
import android.util.Log;
import android.webkit.DownloadListener;
import android.webkit.MimeTypeMap;
import com.Newawe.Factory;
import com.Newawe.MainNavigationActivity;
import com.Newawe.configuration.WebWidgetConfiguration.DownloadActions;
import com.Newawe.controllers.WebContentController;
import com.Newawe.ui.dialog.SimpleDialogs;
import com.Newawe.utils.FileManager;

public class BrowserDownloadListener implements DownloadListener {
    private WebContentController _webController;

    /* renamed from: com.Newawe.browser.BrowserDownloadListener.1 */
    class C02131 implements OnClickListener {
        final /* synthetic */ String val$finalMimeType;
        final /* synthetic */ MainNavigationActivity val$mainActivity;
        final /* synthetic */ String val$url;

        C02131(String str, String str2, MainNavigationActivity mainNavigationActivity) {
            this.val$url = str;
            this.val$finalMimeType = str2;
            this.val$mainActivity = mainNavigationActivity;
        }

        public void onClick(DialogInterface dialog, int which) {
            FileManager.fireOpenFileIntent(this.val$url, this.val$finalMimeType, this.val$mainActivity);
        }
    }

    /* renamed from: com.Newawe.browser.BrowserDownloadListener.2 */
    class C02142 implements OnClickListener {
        final /* synthetic */ String val$contentDisposition;
        final /* synthetic */ String val$finalMimeType;
        final /* synthetic */ MainNavigationActivity val$mainActivity;
        final /* synthetic */ String val$url;

        C02142(String str, String str2, String str3, MainNavigationActivity mainNavigationActivity) {
            this.val$url = str;
            this.val$finalMimeType = str2;
            this.val$contentDisposition = str3;
            this.val$mainActivity = mainNavigationActivity;
        }

        public void onClick(DialogInterface dialog, int which) {
            FileManager.downloadFile(this.val$url, this.val$finalMimeType, this.val$contentDisposition, this.val$mainActivity);
        }
    }

    public BrowserDownloadListener(WebContentController webController) {
        this._webController = webController;
    }

    public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
        try {
            MainNavigationActivity mainActivity = Factory.getInstance().getMainNavigationActivity();
            String guessedMimeType = null;
            String extension = MimeTypeMap.getFileExtensionFromUrl(url);
            if (extension != null) {
                guessedMimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
            }
            if (guessedMimeType == null) {
                guessedMimeType = mimetype;
            }
            String finalMimeType = guessedMimeType;
            DownloadActions action = mainActivity.getConfig().getDownloadAction();
            if (VERSION.SDK_INT < 9) {
                action = DownloadActions.OPEN;
            }
            if (action == DownloadActions.OPEN) {
                FileManager.fireOpenFileIntent(url, finalMimeType, mainActivity);
            } else if (action == DownloadActions.SAVE) {
                try {
                    FileManager.downloadFile(url, finalMimeType, mainActivity);
                } catch (Exception e) {
                    FileManager.fireOpenFileIntent(url, finalMimeType, mainActivity);
                }
            } else if (action == DownloadActions.DIALOG) {
                try {
                    SimpleDialogs.showOpenOrSaveDialog(mainActivity, new C02131(url, finalMimeType, mainActivity), new C02142(url, finalMimeType, contentDisposition, mainActivity));
                } catch (Exception e2) {
                    FileManager.fireOpenFileIntent(url, mimetype, mainActivity);
                }
            }
        } catch (ActivityNotFoundException e3) {
            Log.e("ANFE", "onDownloadStart :" + e3.getMessage());
        }
    }
}
