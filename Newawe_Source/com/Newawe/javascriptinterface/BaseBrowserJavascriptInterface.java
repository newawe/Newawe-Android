package com.Newawe.javascriptinterface;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;
import com.Newawe.server.BaseServerClient;
import com.Newawe.server.BaseServerClient.OnRequestDoneListener;
import com.Newawe.utils.FileManager;
import com.Newawe.utils.ImageReader;
import com.Newawe.utils.UrlConverter;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import mf.org.apache.xml.serialize.LineSeparator;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;

public class BaseBrowserJavascriptInterface {
    public static final String JS_PREFERENCE_NAME = "JS-PREFERENCE";
    public static final String JS_PREFERENCE_PREFIX = "JS-Preference-";
    private Context _context;
    private WebView _view;
    private Handler _webViewThreadHandler;

    /* renamed from: com.Newawe.javascriptinterface.BaseBrowserJavascriptInterface.1 */
    class C09921 implements OnRequestDoneListener {
        final /* synthetic */ String val$resultCallback;

        /* renamed from: com.Newawe.javascriptinterface.BaseBrowserJavascriptInterface.1.1 */
        class C02291 implements Runnable {
            final /* synthetic */ String val$callback;

            C02291(String str) {
                this.val$callback = str;
            }

            public void run() {
                BaseBrowserJavascriptInterface.this._view.loadUrl(this.val$callback);
            }
        }

        C09921(String str) {
            this.val$resultCallback = str;
        }

        public void onRequestDone(String requestUrl, int tag, HttpResponse response) {
            String stringResponse;
            try {
                InputStream is = response.getEntity().getContent();
                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while (true) {
                    String line = br.readLine();
                    if (line == null) {
                        break;
                    }
                    stringBuilder.append(line);
                    stringBuilder.append('\n');
                }
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                stringResponse = stringBuilder.toString();
            } catch (IOException e) {
                e.printStackTrace();
                stringResponse = StringUtils.EMPTY;
            }
            BaseBrowserJavascriptInterface.this._webViewThreadHandler.post(new C02291("javascript:window." + this.val$resultCallback + "('" + StringEscapeUtils.escapeJavaScript(stringResponse) + "');"));
        }
    }

    /* renamed from: com.Newawe.javascriptinterface.BaseBrowserJavascriptInterface.2 */
    class C09932 implements OnRequestDoneListener {
        final /* synthetic */ String val$resultCallback;

        /* renamed from: com.Newawe.javascriptinterface.BaseBrowserJavascriptInterface.2.1 */
        class C02301 implements Runnable {
            final /* synthetic */ String val$result;

            C02301(String str) {
                this.val$result = str;
            }

            public void run() {
                BaseBrowserJavascriptInterface.this._view.loadUrl("javascript:" + C09932.this.val$resultCallback + "('" + this.val$result + "');");
            }
        }

        C09932(String str) {
            this.val$resultCallback = str;
        }

        public void onRequestDone(String requestUrl, int tag, HttpResponse response) {
            String stringResponse;
            try {
                InputStream is = response.getEntity().getContent();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                while (true) {
                    int c = is.read();
                    if (c == -1) {
                        break;
                    }
                    out.write(c);
                }
                out.flush();
                byte[] imageRaw = out.toByteArray();
                is.close();
                out.close();
                stringResponse = "data:image/png;base64," + Base64.encodeToString(imageRaw, 0);
            } catch (IOException e) {
                e.printStackTrace();
                stringResponse = StringUtils.EMPTY;
            }
            BaseBrowserJavascriptInterface.this._webViewThreadHandler.post(new C02301(stringResponse.replace(LineSeparator.Web, StringUtils.EMPTY).replace("\\", "\\\\").replace("'", "\\'")));
        }
    }

    public BaseBrowserJavascriptInterface(Context context, WebView webView, Handler webViewThreadHandler) {
        this._context = context;
        this._view = webView;
        this._webViewThreadHandler = webViewThreadHandler;
    }

    @JavascriptInterface
    public String sendXMLHTTPRequestSync(String url) {
        String strRes = StringUtils.EMPTY;
        if (this._context != null) {
            return new BaseServerClient(this._context, null).sendRequestSync(url);
        }
        return strRes;
    }

    @JavascriptInterface
    public void sendXMLHTTPRequest(String url, String resultCallback) {
        new BaseServerClient(this._context, null).sendRequestAsync(url, 0, new C09921(resultCallback));
    }

    @JavascriptInterface
    public void showInfo(String message) {
        if (this._context != null) {
            Toast.makeText(this._context, message, 0).show();
        }
    }

    @JavascriptInterface
    public void downloadFile(String url) {
        FileManager.downloadFile(new UrlConverter(this._view).toAbsolute(url), StringUtils.EMPTY, this._context);
    }

    @JavascriptInterface
    public String saveImageFromBase64(String base64, String prefix) {
        if (prefix == null) {
            prefix = "IMG";
        }
        Bitmap image = ImageReader.createBitmapFromBase64(base64);
        if (image != null) {
            File imageFile = FileManager.saveBitmapToGallery(prefix, image);
            if (imageFile != null) {
                showInfo("Image saved to gallery...");
                return imageFile.toString();
            }
        }
        return null;
    }

    @JavascriptInterface
    public String getFileContents(String fileName) {
        String strRes = StringUtils.EMPTY;
        return FileManager.getStringFromAssetsFileWithFileName(fileName, this._context);
    }

    @JavascriptInterface
    public String getItem(String key) {
        return this._context.getSharedPreferences(JS_PREFERENCE_NAME, 0).getString(JS_PREFERENCE_PREFIX + key, null);
    }

    @JavascriptInterface
    public void setItem(String key, String value) {
        this._context.getSharedPreferences(JS_PREFERENCE_NAME, 0).edit().putString(JS_PREFERENCE_PREFIX + key, value).commit();
    }

    @JavascriptInterface
    public void getBase64FromImageUrl(String url, String resultCallback) {
        new BaseServerClient(this._context, null).sendRequestAsync(url, 0, new C09932(resultCallback));
    }
}
