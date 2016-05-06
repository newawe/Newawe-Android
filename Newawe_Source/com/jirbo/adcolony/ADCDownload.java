package com.jirbo.adcolony;

import android.os.Build.VERSION;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;

class ADCDownload extends C0775j implements Runnable {
    C0762d f3914a;
    Listener f3915b;
    String f3916c;
    File f3917d;
    Object f3918e;
    String f3919f;
    String f3920g;
    boolean f3921h;
    boolean f3922i;
    boolean f3923j;
    Map<String, List<String>> f3924k;
    SSLContext f3925l;
    int f3926m;
    String f3927n;

    public interface Listener {
        void on_download_finished(ADCDownload aDCDownload);
    }

    ADCDownload(C0762d controller, String url, Listener listener) {
        this(controller, url, listener, null);
    }

    ADCDownload(C0762d controller, String url, Listener listener, String filepath) {
        super(controller, false);
        this.f3916c = StringUtils.EMPTY;
        this.f3916c = url;
        if (url == null) {
            this.f3916c = StringUtils.EMPTY;
        }
        this.f3915b = listener;
        if (filepath != null) {
            this.f3917d = new File(filepath);
        }
    }

    ADCDownload m4673a(Object obj) {
        this.f3918e = obj;
        return this;
    }

    ADCDownload m4674a(String str, String str2) {
        this.f3919f = str;
        this.f3920g = str2;
        return this;
    }

    public void m4676b() {
        C0817z.m2512a(this);
    }

    public void run() {
        int i = 1;
        while (i <= 3) {
            HttpsURLConnection httpsURLConnection = null;
            try {
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f3916c).openConnection();
                InputStream inputStream;
                int read;
                int i2;
                if (this.f3919f != null) {
                    HttpsURLConnection httpsURLConnection2;
                    C0777l.f2239a.m2353b((Object) "Performing POST");
                    if (!this.f3916c.startsWith("https://") || VERSION.SDK_INT < 10) {
                        httpsURLConnection2 = null;
                    } else {
                        httpsURLConnection = (HttpsURLConnection) new URL(this.f3916c).openConnection();
                        this.f3923j = true;
                        httpsURLConnection2 = httpsURLConnection;
                    }
                    if (this.f3923j) {
                        httpsURLConnection2.setRequestMethod(HttpPost.METHOD_NAME);
                    } else {
                        httpURLConnection.setRequestMethod(HttpPost.METHOD_NAME);
                    }
                    if (this.f3923j) {
                        httpsURLConnection2.setDoOutput(true);
                    } else {
                        httpURLConnection.setDoOutput(true);
                    }
                    (this.f3923j ? new PrintStream(httpsURLConnection2.getOutputStream()) : new PrintStream(httpURLConnection.getOutputStream())).println(this.f3920g);
                    C0777l.f2239a.m2349a("Post data: ").m2353b(this.f3920g);
                    if (this.f3923j) {
                        httpsURLConnection2.connect();
                    } else {
                        httpURLConnection.connect();
                    }
                    if ((this.f3923j && httpsURLConnection2.getResponseCode() == HttpStatus.SC_OK) || (!this.f3923j && httpURLConnection.getResponseCode() == HttpStatus.SC_OK)) {
                        inputStream = this.f3923j ? httpsURLConnection2.getInputStream() : httpURLConnection.getInputStream();
                        StringBuilder stringBuilder = new StringBuilder();
                        this.f3924k = this.f3923j ? httpsURLConnection2.getHeaderFields() : httpURLConnection.getHeaderFields();
                        byte[] bArr = new byte[NodeFilter.SHOW_DOCUMENT_FRAGMENT];
                        for (read = inputStream.read(bArr, 0, NodeFilter.SHOW_DOCUMENT_FRAGMENT); read != -1; read = inputStream.read(bArr, 0, NodeFilter.SHOW_DOCUMENT_FRAGMENT)) {
                            i2 = -1;
                            while (true) {
                                i2++;
                                if (i2 >= read) {
                                    break;
                                }
                                stringBuilder.append((char) bArr[i2]);
                            }
                        }
                        inputStream.close();
                        try {
                            this.f3927n = stringBuilder.toString();
                            this.f3926m = this.f3927n.length();
                            if (this.f3916c.contains("androidads23")) {
                                C0745a.al = System.currentTimeMillis();
                            }
                            this.f3922i = true;
                            C0745a.m2141a((C0775j) this);
                            return;
                        } catch (OutOfMemoryError e) {
                            C0777l.f2242d.m2353b((Object) "Out of memory, disabling AdColony");
                            AdColony.disable();
                            return;
                        }
                    }
                    if (i == 3) {
                        break;
                    }
                    try {
                        Thread.sleep((long) (((i + 1) * 10) * DateUtils.MILLIS_IN_SECOND));
                    } catch (InterruptedException e2) {
                    }
                    C0777l.f2240b.m2349a("Trying again (").m2347a(i + 1).m2353b((Object) "/3)");
                    i++;
                } else {
                    httpURLConnection.setReadTimeout(30000);
                    if (this.f3921h) {
                        httpURLConnection.setInstanceFollowRedirects(false);
                    }
                    if (this.f3917d != null) {
                        if (!(this.f3914a == null || this.f3914a.f2146f == null)) {
                            this.f3914a.f2146f.m2110b();
                        }
                        Object absolutePath = this.f3917d.getAbsolutePath();
                        OutputStream fileOutputStream = new FileOutputStream(absolutePath);
                        InputStream inputStream2 = httpURLConnection.getInputStream();
                        read = httpURLConnection.getContentLength();
                        this.f3926m = 0;
                        byte[] bArr2 = new byte[NodeFilter.SHOW_DOCUMENT_FRAGMENT];
                        i2 = inputStream2.read(bArr2, 0, NodeFilter.SHOW_DOCUMENT_FRAGMENT);
                        while (i2 != -1) {
                            if (read > 0) {
                                if (i2 > read) {
                                    i2 = read;
                                }
                                read -= i2;
                            }
                            this.f3926m += i2;
                            fileOutputStream.write(bArr2, 0, i2);
                            i2 = inputStream2.read(bArr2, 0, NodeFilter.SHOW_DOCUMENT_FRAGMENT);
                            if (read == 0) {
                                break;
                            }
                        }
                        inputStream2.close();
                        fileOutputStream.flush();
                        fileOutputStream.close();
                        C0777l.f2240b.m2349a("Downloaded ").m2349a(this.f3916c).m2349a(" to ").m2353b(absolutePath);
                    } else {
                        if (this.f3921h) {
                            if (this.f3916c.startsWith("https://") && VERSION.SDK_INT >= 10) {
                                httpsURLConnection = (HttpsURLConnection) new URL(this.f3916c).openConnection();
                                this.f3923j = true;
                            }
                            int responseCode = this.f3923j ? httpsURLConnection.getResponseCode() : httpURLConnection.getResponseCode();
                            if (responseCode > 0) {
                                C0777l.f2239a.m2349a("Got HTTP response ").m2347a(responseCode).m2353b((Object) " - counting as completed submission for 3rd party tracking.");
                                C0777l.f2240b.m2349a("Downloaded ").m2353b(this.f3916c);
                                this.f3927n = StringUtils.EMPTY;
                                this.f3926m = 0;
                                this.f3922i = true;
                                C0745a.m2141a((C0775j) this);
                                return;
                            }
                        }
                        if (!this.f3916c.startsWith("https://") || VERSION.SDK_INT < 10) {
                            this.f3923j = false;
                        } else {
                            httpsURLConnection = (HttpsURLConnection) new URL(this.f3916c).openConnection();
                            this.f3923j = true;
                            C0777l.f2239a.m2353b((Object) "ADCDownload - use ssl!");
                        }
                        C0777l.f2239a.m2353b((Object) "ADCDownload - before pause");
                        try {
                            Thread.sleep(3000);
                        } catch (Exception e3) {
                        }
                        C0777l.f2239a.m2353b((Object) "ADCDownload - getInputStream");
                        if (this.f3923j) {
                            inputStream = httpsURLConnection.getInputStream();
                        } else {
                            inputStream = httpURLConnection.getInputStream();
                        }
                        StringBuilder stringBuilder2 = new StringBuilder();
                        byte[] bArr3 = new byte[NodeFilter.SHOW_DOCUMENT_FRAGMENT];
                        for (read = inputStream.read(bArr3, 0, NodeFilter.SHOW_DOCUMENT_FRAGMENT); read != -1; read = inputStream.read(bArr3, 0, NodeFilter.SHOW_DOCUMENT_FRAGMENT)) {
                            i2 = -1;
                            while (true) {
                                i2++;
                                if (i2 >= read) {
                                    break;
                                }
                                try {
                                    stringBuilder2.append((char) bArr3[i2]);
                                } catch (OutOfMemoryError e4) {
                                    C0777l.f2242d.m2353b((Object) "Out of memory, disabling AdColony");
                                    AdColony.disable();
                                    return;
                                }
                            }
                        }
                        inputStream.close();
                        try {
                            this.f3927n = stringBuilder2.toString();
                            this.f3926m = this.f3927n.length();
                            C0777l.f2240b.m2349a("Downloaded ").m2353b(this.f3916c);
                        } catch (OutOfMemoryError e5) {
                            C0777l.f2242d.m2353b((Object) "Out of memory, disabling AdColony");
                            AdColony.disable();
                            return;
                        }
                    }
                    this.f3922i = true;
                    C0745a.m2141a((C0775j) this);
                    return;
                }
            } catch (IOException e6) {
                C0745a.m2152c("Download of " + this.f3916c + " failed:\n" + e6.toString());
            }
        }
        if (this.f3916c.contains("androidads23")) {
            C0745a.f2005p = false;
        }
        this.f3922i = false;
        C0745a.m2141a((C0775j) this);
    }

    void m4675a() {
        this.f3915b.on_download_finished(this);
    }
}
