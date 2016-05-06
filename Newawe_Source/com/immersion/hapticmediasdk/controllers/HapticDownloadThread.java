package com.immersion.hapticmediasdk.controllers;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import com.immersion.hapticmediasdk.models.HttpUnsuccessfulException;
import com.immersion.hapticmediasdk.utils.FileManager;
import com.immersion.hapticmediasdk.utils.Log;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;

public class HapticDownloadThread extends Thread {
    private static final String f895a = "HapticDownloadThread";
    private static final int f896b = 4096;
    public static int f897b04150415041504150415 = 1;
    public static int f898b0415041504150415 = 39;
    public static int f899b0415041504150415 = 0;
    public static int f900b0415041504150415 = 2;
    private String f901c;
    private Handler f902d;
    private boolean f903e;
    private Thread f904f;
    private FileManager f905g;
    private volatile boolean f906h;
    private volatile boolean f907i;
    private volatile boolean f908j;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public HapticDownloadThread(java.lang.String r4, android.os.Handler r5, boolean r6, com.immersion.hapticmediasdk.utils.FileManager r7) {
        /*
        r3 = this;
        r2 = 1;
        r1 = 0;
        r0 = "HapticDownloadThread";
        r3.<init>(r0);
        r3.f906h = r1;
        r3.f907i = r1;
        r3.f908j = r1;
        r3.f901c = r4;
    L_0x000f:
        switch(r2) {
            case 0: goto L_0x000f;
            case 1: goto L_0x0016;
            default: goto L_0x0012;
        };
    L_0x0012:
        switch(r2) {
            case 0: goto L_0x000f;
            case 1: goto L_0x0016;
            default: goto L_0x0015;
        };
    L_0x0015:
        goto L_0x0012;
    L_0x0016:
        r3.f902d = r5;
        r3.f903e = r6;
        r3.f905g = r7;
        r0 = r3.f902d;
        r0 = r0.getLooper();
        r1 = f898b0415041504150415;
        r2 = f897b04150415041504150415;
        r1 = r1 + r2;
        r2 = f898b0415041504150415;
        r1 = r1 * r2;
        r2 = f900b0415041504150415;
        r1 = r1 % r2;
        r2 = f899b0415041504150415;
        if (r1 == r2) goto L_0x003b;
    L_0x0031:
        r1 = 70;
        f898b0415041504150415 = r1;
        r1 = m1019b04150415041504150415();
        f899b0415041504150415 = r1;
    L_0x003b:
        r0 = r0.getThread();
        r3.f904f = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticDownloadThread.<init>(java.lang.String, android.os.Handler, boolean, com.immersion.hapticmediasdk.utils.FileManager):void");
    }

    public static int m1019b04150415041504150415() {
        return 19;
    }

    public static int m1020b0427() {
        return 1;
    }

    public static int m1021b04150415041504150415() {
        return 0;
    }

    public synchronized boolean isFirstPacketReady() {
        boolean z;
        try {
            z = this.f906h || this.f907i;
        } catch (Exception e) {
            throw e;
        }
        return z;
    }

    public void run() {
        if (this.f903e) {
            Process.setThreadPriority(10);
            try {
                HttpResponse executeGet = ImmersionHttpClient.getHttpClient().executeGet(this.f901c, null, DateUtils.MILLIS_IN_MINUTE);
                int statusCode = executeGet.getStatusLine().getStatusCode();
                if (statusCode == HttpStatus.SC_OK) {
                    writeToFile(executeGet.getEntity().getContent(), Integer.parseInt(executeGet.getFirstHeader(HTTP.CONTENT_LEN).getValue()));
                    return;
                }
                StringBuilder stringBuilder = new StringBuilder("HTTP STATUS CODE: ");
                stringBuilder.append(statusCode);
                switch (statusCode) {
                    case HttpStatus.SC_BAD_REQUEST /*400*/:
                        stringBuilder.append(" Bad Request");
                        break;
                    case HttpStatus.SC_FORBIDDEN /*403*/:
                        stringBuilder.append(" Forbidden");
                        break;
                    case HttpStatus.SC_NOT_FOUND /*404*/:
                        stringBuilder.append(" Not Found");
                        break;
                    case HttpStatus.SC_INTERNAL_SERVER_ERROR /*500*/:
                        stringBuilder.append(" Internal Server Error");
                        break;
                    case HttpStatus.SC_BAD_GATEWAY /*502*/:
                        stringBuilder.append(" Bad Gateway");
                        break;
                    case HttpStatus.SC_SERVICE_UNAVAILABLE /*503*/:
                        stringBuilder.append(" Service Unavailable");
                        break;
                    default:
                        break;
                }
                throw new HttpUnsuccessfulException(statusCode, stringBuilder.toString());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            } catch (Object e2) {
                Message obtainMessage = this.f902d.obtainMessage(8);
                Bundle bundle = new Bundle();
                bundle.putSerializable(HapticPlaybackThread.HAPTIC_DOWNLOAD_EXCEPTION_KEY, e2);
                obtainMessage.setData(bundle);
                if (this.f904f.isAlive() && !this.f908j) {
                    Handler handler = this.f902d;
                    if (((f898b0415041504150415 + f897b04150415041504150415) * f898b0415041504150415) % f900b0415041504150415 != f899b0415041504150415) {
                        f898b0415041504150415 = m1019b04150415041504150415();
                        f899b0415041504150415 = m1019b04150415041504150415();
                    }
                    handler.sendMessage(obtainMessage);
                }
                Log.m1114e(f895a, e2.getMessage());
                return;
            }
        }
        InputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(this.f901c);
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
            fileInputStream = null;
        }
        if (fileInputStream != null) {
            try {
                writeToFile(fileInputStream, fileInputStream.available());
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void terminate() {
        /*
        r3 = this;
        r2 = 1;
        r0 = f898b0415041504150415;
        r1 = f897b04150415041504150415;
        r0 = r0 + r1;
        r1 = f898b0415041504150415;
        r0 = r0 * r1;
        r1 = f900b0415041504150415;
        r0 = r0 % r1;
        r1 = m1021b04150415041504150415();
        if (r0 == r1) goto L_0x001c;
    L_0x0012:
        r0 = 53;
        f898b0415041504150415 = r0;
        r0 = m1019b04150415041504150415();
        f899b0415041504150415 = r0;
    L_0x001c:
        r0 = 0;
        switch(r0) {
            case 0: goto L_0x0024;
            case 1: goto L_0x001c;
            default: goto L_0x0020;
        };
    L_0x0020:
        switch(r2) {
            case 0: goto L_0x001c;
            case 1: goto L_0x0024;
            default: goto L_0x0023;
        };
    L_0x0023:
        goto L_0x0020;
    L_0x0024:
        r3.f908j = r2;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.immersion.hapticmediasdk.controllers.HapticDownloadThread.terminate():void");
    }

    public boolean writeToFile(InputStream inputStream, int i) throws IOException {
        Bundle bundle;
        Closeable closeable;
        Throwable th;
        Closeable closeable2;
        String str;
        Message obtainMessage;
        int i2 = 0;
        try {
            byte[] bArr = new byte[f896b];
            if (inputStream == null || i <= 0) {
                if (!this.f906h) {
                    String str2 = "downloaded an empty file";
                    Message obtainMessage2 = this.f902d.obtainMessage(8);
                    Bundle bundle2 = new Bundle();
                    bundle2.putSerializable(HapticPlaybackThread.HAPTIC_DOWNLOAD_EXCEPTION_KEY, new FileNotFoundException(str2));
                    obtainMessage2.setData(bundle2);
                    if (this.f904f.isAlive() && !this.f908j) {
                        this.f902d.sendMessage(obtainMessage2);
                    }
                    Log.m1114e(f895a, str2);
                }
                this.f905g.closeCloseable(null);
                this.f905g.closeCloseable(null);
                this.f907i = true;
                return false;
            }
            try {
                Closeable bufferedInputStream = new BufferedInputStream(inputStream);
                try {
                    BufferedOutputStream makeOutputStreamForStreaming = this.f903e ? this.f905g.makeOutputStreamForStreaming(this.f901c) : this.f905g.makeOutputStream(this.f901c);
                    Message obtainMessage3;
                    if (makeOutputStreamForStreaming == null) {
                        if (!this.f906h) {
                            String str3 = "downloaded an empty file";
                            obtainMessage3 = this.f902d.obtainMessage(8);
                            bundle = new Bundle();
                            bundle.putSerializable(HapticPlaybackThread.HAPTIC_DOWNLOAD_EXCEPTION_KEY, new FileNotFoundException(str3));
                            try {
                                obtainMessage3.setData(bundle);
                                if (this.f904f.isAlive() && !this.f908j) {
                                    this.f902d.sendMessage(obtainMessage3);
                                }
                                Log.m1114e(f895a, str3);
                            } catch (Exception e) {
                                throw e;
                            }
                        }
                        this.f905g.closeCloseable(bufferedInputStream);
                        this.f905g.closeCloseable(makeOutputStreamForStreaming);
                        this.f907i = true;
                        return false;
                    }
                    try {
                        String str4;
                        if (this.f903e) {
                            while (!isInterrupted() && !this.f908j) {
                                int read = bufferedInputStream.read(bArr, 0, f896b);
                                if (read < 0) {
                                    break;
                                }
                                makeOutputStreamForStreaming.write(bArr, 0, read);
                                i2 += read;
                                if (this.f904f.isAlive()) {
                                    if (!this.f906h) {
                                        this.f906h = true;
                                    }
                                    makeOutputStreamForStreaming.flush();
                                    this.f902d.sendMessage(this.f902d.obtainMessage(3, i2, 0));
                                }
                            }
                        } else {
                            this.f906h = true;
                            if (this.f908j) {
                                if (!this.f906h) {
                                    str4 = "downloaded an empty file";
                                    obtainMessage3 = this.f902d.obtainMessage(8);
                                    bundle = new Bundle();
                                    bundle.putSerializable(HapticPlaybackThread.HAPTIC_DOWNLOAD_EXCEPTION_KEY, new FileNotFoundException(str4));
                                    obtainMessage3.setData(bundle);
                                    if (this.f904f.isAlive() && !this.f908j) {
                                        this.f902d.sendMessage(obtainMessage3);
                                    }
                                    Log.m1114e(f895a, str4);
                                }
                                this.f905g.closeCloseable(bufferedInputStream);
                                this.f905g.closeCloseable(makeOutputStreamForStreaming);
                                this.f907i = true;
                                return true;
                            }
                            this.f902d.sendMessage(this.f902d.obtainMessage(3, i, 0));
                        }
                        Log.m1115i(f895a, "file download completed");
                        if (!this.f906h) {
                            str4 = "downloaded an empty file";
                            obtainMessage3 = this.f902d.obtainMessage(8);
                            bundle = new Bundle();
                            if (((f898b0415041504150415 + f897b04150415041504150415) * f898b0415041504150415) % f900b0415041504150415 != f899b0415041504150415) {
                                f898b0415041504150415 = 2;
                                f899b0415041504150415 = 54;
                            }
                            bundle.putSerializable(HapticPlaybackThread.HAPTIC_DOWNLOAD_EXCEPTION_KEY, new FileNotFoundException(str4));
                            obtainMessage3.setData(bundle);
                            if (this.f904f.isAlive() && !this.f908j) {
                                this.f902d.sendMessage(obtainMessage3);
                            }
                            Log.m1114e(f895a, str4);
                        }
                        this.f905g.closeCloseable(bufferedInputStream);
                        if (((f898b0415041504150415 + m1020b0427()) * f898b0415041504150415) % f900b0415041504150415 != f899b0415041504150415) {
                            f898b0415041504150415 = 47;
                            f899b0415041504150415 = 86;
                        }
                        this.f905g.closeCloseable(makeOutputStreamForStreaming);
                        this.f907i = true;
                        return true;
                    } catch (Throwable th2) {
                        closeable = bufferedInputStream;
                        BufferedOutputStream bufferedOutputStream = makeOutputStreamForStreaming;
                        th = th2;
                        Object obj = bufferedOutputStream;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    closeable2 = null;
                    closeable = bufferedInputStream;
                    if (!this.f906h) {
                        str = "downloaded an empty file";
                        obtainMessage = this.f902d.obtainMessage(8);
                        bundle = new Bundle();
                        bundle.putSerializable(HapticPlaybackThread.HAPTIC_DOWNLOAD_EXCEPTION_KEY, new FileNotFoundException(str));
                        obtainMessage.setData(bundle);
                        if (this.f904f.isAlive() && !this.f908j) {
                            this.f902d.sendMessage(obtainMessage);
                        }
                        Log.m1114e(f895a, str);
                    }
                    this.f905g.closeCloseable(closeable);
                    this.f905g.closeCloseable(closeable2);
                    this.f907i = true;
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                closeable2 = null;
                closeable = null;
                if (this.f906h) {
                    str = "downloaded an empty file";
                    obtainMessage = this.f902d.obtainMessage(8);
                    bundle = new Bundle();
                    bundle.putSerializable(HapticPlaybackThread.HAPTIC_DOWNLOAD_EXCEPTION_KEY, new FileNotFoundException(str));
                    obtainMessage.setData(bundle);
                    this.f902d.sendMessage(obtainMessage);
                    Log.m1114e(f895a, str);
                }
                this.f905g.closeCloseable(closeable);
                this.f905g.closeCloseable(closeable2);
                this.f907i = true;
                throw th;
            }
        } catch (Exception e2) {
            throw e2;
        }
    }
}
