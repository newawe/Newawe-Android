package com.inmobi.rendering.mraid;

import android.os.AsyncTask;
import android.os.Environment;
import android.webkit.URLUtil;
import com.Newawe.storage.DatabaseOpenHelper;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.NetworkUtils;
import com.inmobi.rendering.RenderView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.rendering.mraid.c */
public final class DownloadTask extends AsyncTask<Void, Void, String> {
    private static final String f1589a;
    private int f1590b;
    private File f1591c;
    private String f1592d;
    private String f1593e;
    private String f1594f;
    private WeakReference<RenderView> f1595g;
    private DownloadTask f1596h;
    private ArrayList<String> f1597i;
    private long f1598j;
    private String f1599k;

    /* renamed from: com.inmobi.rendering.mraid.c.a */
    public interface DownloadTask {
        void m1767a();

        void m1768a(int i);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m1770a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m1771a((String) obj);
    }

    static {
        f1589a = DownloadTask.class.getSimpleName();
    }

    public DownloadTask(String str, File file, String str2, String str3, RenderView renderView) {
        this.f1599k = str;
        this.f1591c = file;
        this.f1592d = str2;
        this.f1593e = str3;
        this.f1597i = renderView.getRenderingConfig().m1181h();
        this.f1598j = renderView.getRenderingConfig().m1180g();
        this.f1595g = new WeakReference(renderView);
    }

    protected String m1770a(Void... voidArr) {
        if (!NetworkUtils.m1479a()) {
            this.f1590b = 8;
            return "fail";
        } else if (!this.f1593e.matches("[A-Za-z0-9]+") || this.f1593e.equals(StringUtils.EMPTY)) {
            this.f1590b = 2;
            return "fail";
        } else if (this.f1592d.equals(StringUtils.EMPTY) || !URLUtil.isValidUrl(this.f1592d)) {
            this.f1590b = 3;
            return "fail";
        } else if (Environment.getExternalStorageState().equals("mounted")) {
            String[] strArr = (String[]) this.f1597i.toArray(new String[this.f1597i.size()]);
            try {
                long currentTimeMillis = System.currentTimeMillis();
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f1592d).openConnection();
                httpURLConnection.setRequestMethod(HttpGet.METHOD_NAME);
                httpURLConnection.setConnectTimeout(5000);
                int responseCode = httpURLConnection.getResponseCode();
                Logger.m1440a(InternalLogLevel.INTERNAL, f1589a, "Response code: " + responseCode);
                if (responseCode < HttpStatus.SC_BAD_REQUEST) {
                    Object obj;
                    String contentType = httpURLConnection.getContentType();
                    Logger.m1440a(InternalLogLevel.INTERNAL, f1589a, "Content Type: " + contentType);
                    for (String str : strArr) {
                        Logger.m1440a(InternalLogLevel.INTERNAL, f1589a, "Allowed Type: " + str);
                        if (str.toLowerCase(Locale.ENGLISH).equals(contentType.toLowerCase(Locale.ENGLISH))) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    if (obj == null) {
                        this.f1590b = 6;
                        return "fail";
                    }
                }
                long contentLength = (long) httpURLConnection.getContentLength();
                if (contentLength >= 0) {
                    Logger.m1440a(InternalLogLevel.INTERNAL, f1589a, "ContentSize: " + contentLength + " max size: " + this.f1598j);
                    if (contentLength > this.f1598j) {
                        this.f1590b = 7;
                        return "fail";
                    }
                }
                httpURLConnection.connect();
                FileOutputStream fileOutputStream = new FileOutputStream(this.f1591c);
                InputStream inputStream = httpURLConnection.getInputStream();
                byte[] bArr = new byte[NodeFilter.SHOW_DOCUMENT_FRAGMENT];
                long j = 0;
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read > 0) {
                        j += (long) read;
                        if (j > this.f1598j) {
                            this.f1590b = 7;
                            return "fail";
                        }
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileOutputStream.close();
                        j = System.currentTimeMillis();
                        String str2 = "file://" + this.f1591c.getAbsolutePath();
                        Logger.m1440a(InternalLogLevel.INTERNAL, f1589a, "file path of video: " + str2);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put(DatabaseOpenHelper.HISTORY_ROW_URL, this.f1592d);
                        jSONObject.put("saved_url", str2);
                        jSONObject.put("size_in_bytes", this.f1591c.length());
                        jSONObject.put("download_started_at", currentTimeMillis);
                        jSONObject.put("download_ended_at", j);
                        this.f1594f = jSONObject.toString().replace("\"", "\\\"");
                        return "success";
                    }
                }
            } catch (SocketTimeoutException e) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1589a, "SocketTimeoutException");
                this.f1590b = 4;
                return "fail";
            } catch (FileNotFoundException e2) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1589a, "FileNotFoundException");
                this.f1590b = 4;
                return "fail";
            } catch (MalformedURLException e3) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1589a, "MalformedURLException");
                this.f1590b = 3;
                return "fail";
            } catch (ProtocolException e4) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1589a, "ProtocolException");
                this.f1590b = 8;
                return "fail";
            } catch (IOException e5) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1589a, "IOException");
                this.f1590b = 8;
                return "fail";
            } catch (JSONException e6) {
                Logger.m1440a(InternalLogLevel.INTERNAL, f1589a, "JSONException");
                this.f1590b = 0;
                return "fail";
            }
        } else {
            this.f1590b = 10;
            return "fail";
        }
    }

    protected void onCancelled() {
        super.onCancelled();
    }

    protected void m1771a(String str) {
        if (str.equals("success")) {
            if (this.f1595g.get() != null) {
                ((RenderView) this.f1595g.get()).m1642a(this.f1599k, "sendSaveContentResult(\"saveContent_" + this.f1593e + "\", 'success', \"" + this.f1594f + "\");");
            }
            if (this.f1596h != null) {
                this.f1596h.m1767a();
            }
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(DatabaseOpenHelper.HISTORY_ROW_URL, this.f1592d);
                jSONObject.put("reason", this.f1590b);
                String replace = jSONObject.toString().replace("\"", "\\\"");
                if (this.f1595g.get() != null) {
                    ((RenderView) this.f1595g.get()).m1642a(this.f1599k, "sendSaveContentResult(\"saveContent_" + this.f1593e + "\", 'failure', \"" + replace + "\");");
                }
                if (this.f1596h != null) {
                    this.f1596h.m1768a(this.f1590b);
                }
            } catch (JSONException e) {
                if (this.f1595g.get() != null) {
                    ((RenderView) this.f1595g.get()).m1642a(this.f1599k, "sendSaveContentResult(\"saveContent_" + this.f1593e + "\", 'failure', \"JSONException\");");
                }
            }
        }
        super.onPostExecute(str);
    }

    public String m1769a() {
        return this.f1593e;
    }
}
