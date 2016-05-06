package com.chartboost.sdk.Libraries;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.impl.bv;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import mf.org.apache.xml.serialize.Method;

/* renamed from: com.chartboost.sdk.Libraries.h */
public class C0330h {
    private static long f140A;
    private static String[] f141B;
    private static long f142C;
    private static String[] f143D;
    private static long f144E;
    private static String[] f145F;
    private static String[] f146G;
    private static long f147H;
    private static String[] f148I;
    private static File f149a;
    private static File f150b;
    private static File f151c;
    private static File f152d;
    private static File f153e;
    private static File f154f;
    private static File f155g;
    private static File f156h;
    private static File f157i;
    private static File f158j;
    private static File f159k;
    private static File f160l;
    private static File f161m;
    private static File f162n;
    private static File f163o;
    private static File f164p;
    private static File f165q;
    private static File f166r;
    private static File f167s;
    private static File f168t;
    private static File f169u;
    private static Context f170v;
    private static File f171w;
    private static File f172x;
    private static File f173y;
    private static File f174z;
    private boolean f175J;

    /* renamed from: com.chartboost.sdk.Libraries.h.a */
    public enum C0329a {
        Media("media"),
        AssetFileLog("asset_log"),
        Videos("videos"),
        Images("images"),
        StyleSheets("css"),
        Javascript("js"),
        Html(Method.HTML),
        VideoCompletion("videoCompletionEvents"),
        Session("session"),
        Track("track"),
        RequestManager("requests");
        
        private final String f139l;

        private C0329a(String str) {
            this.f139l = str;
        }

        public String toString() {
            return this.f139l;
        }
    }

    public C0330h(boolean z) {
        this.f175J = false;
        f170v = C0351c.m378y();
        if (f170v == null) {
            CBLogging.m77b("CBFileCache", "RunTime error: Cannot find context object");
            return;
        }
        this.f175J = z;
        m187a(z);
    }

    private File m187a(boolean z) {
        if (m231v() != null) {
            return f149a;
        }
        File file = new File(f170v.getCacheDir(), ".chartboost");
        f169u = file;
        f151c = file;
        file = new File(Environment.getExternalStorageDirectory(), ".chartboost");
        f168t = file;
        f150b = file;
        if (!f151c.exists()) {
            f151c.mkdirs();
        }
        if (!f150b.exists()) {
            f150b.mkdirs();
        }
        if (C0351c.m341a()) {
            f149a = f151c;
        } else {
            f149a = f150b;
        }
        if (!z) {
            f149a = f151c;
        }
        f161m = new File(f151c, C0329a.AssetFileLog.toString());
        f161m = new File(f161m, C0351c.m355e());
        if (!f161m.exists()) {
            f161m.mkdir();
        }
        f160l = new File(f150b, C0329a.AssetFileLog.toString());
        f160l = new File(f160l, C0351c.m355e());
        if (!f160l.exists()) {
            f160l.mkdir();
        }
        f153e = new File(f151c, C0329a.Videos.toString());
        if (!f153e.exists()) {
            f153e.mkdir();
        }
        f152d = new File(f150b, C0329a.Videos.toString());
        if (!f152d.exists()) {
            f152d.mkdir();
        }
        f157i = new File(f151c, C0329a.StyleSheets.toString());
        if (!f157i.exists()) {
            f157i.mkdir();
        }
        f156h = new File(f150b, C0329a.StyleSheets.toString());
        if (!f156h.exists()) {
            f156h.mkdir();
        }
        f155g = new File(f151c, C0329a.Media.toString());
        if (!f155g.exists()) {
            f155g.mkdir();
        }
        f154f = new File(f150b, C0329a.Media.toString());
        if (!f154f.exists()) {
            f154f.mkdir();
        }
        f159k = new File(f151c, C0329a.Javascript.toString());
        if (!f159k.exists()) {
            f159k.mkdir();
        }
        f158j = new File(f150b, C0329a.Javascript.toString());
        if (!f158j.exists()) {
            f158j.mkdir();
        }
        f163o = new File(f151c, C0329a.Html.toString());
        if (!f163o.exists()) {
            f163o.mkdir();
        }
        f162n = new File(f150b, C0329a.Html.toString());
        if (!f162n.exists()) {
            f162n.mkdir();
        }
        f167s = new File(f151c, C0329a.Images.toString());
        if (!f167s.exists()) {
            f167s.mkdir();
        }
        f166r = new File(f150b, C0329a.Images.toString());
        if (!f166r.exists()) {
            f166r.mkdir();
        }
        f164p = new File(f150b, ".adId");
        f165q = new File(f151c, ".adId");
        f171w = new File(f169u, C0329a.VideoCompletion.toString());
        f172x = new File(f169u, C0329a.RequestManager.toString());
        f174z = new File(f169u, C0329a.Track.toString());
        f173y = new File(f169u, C0329a.Session.toString());
        return f149a;
    }

    public static File m185a() {
        if (C0330h.m199i()) {
            return f168t;
        }
        return f169u;
    }

    public synchronized File m206a(File file, String str, C0321a c0321a) {
        File file2;
        file2 = null;
        if (file != null) {
            if (!TextUtils.isEmpty(str)) {
                file2 = new File(file.getPath(), str);
            }
            file2 = m205a(file, file2, c0321a);
        }
        return file2;
    }

    public synchronized File m205a(File file, File file2, C0321a c0321a) {
        File file3;
        if (file == null) {
            file3 = null;
        } else {
            if (file2 == null) {
                file3 = new File(file.getPath(), Long.toString(System.nanoTime()));
            } else {
                file3 = file2;
            }
            try {
                bv.m762a(file3, c0321a.toString().getBytes());
            } catch (Throwable e) {
                CBLogging.m78b("CBFileCache", "IOException attempting to write cache to disk", e);
            }
        }
        return file3;
    }

    public synchronized void m208a(File file, String str, byte[] bArr) {
        if (file != null) {
            File file2 = null;
            if (!TextUtils.isEmpty(str)) {
                file2 = new File(file.getPath(), str);
            }
            m207a(file, file2, bArr);
        }
    }

    public synchronized void m207a(File file, File file2, byte[] bArr) {
        if (!(file == null || bArr == null)) {
            if (file2 == null) {
                file2 = new File(file.getPath(), Long.toString(System.nanoTime()));
            }
            try {
                bv.m762a(file2, bArr);
            } catch (Throwable e) {
                CBLogging.m78b("CBFileCache", "IOException attempting to write cache to disk", e);
            }
        }
    }

    public synchronized C0321a m204a(File file, String str) {
        C0321a c0321a;
        if (m231v() == null || str == null) {
            c0321a = C0321a.f99a;
        } else {
            File file2 = new File(file, str);
            if (file2.exists()) {
                c0321a = m203a(file2);
            } else {
                c0321a = C0321a.f99a;
            }
        }
        return c0321a;
    }

    public synchronized C0321a m203a(File file) {
        C0321a c0321a;
        if (m231v() == null) {
            c0321a = C0321a.f99a;
        } else {
            String str;
            try {
                str = new String(bv.m764b(file));
            } catch (Throwable e) {
                CBLogging.m78b("CBFileCache", "Error loading cache from disk", e);
                str = null;
            }
            c0321a = C0321a.m123k(str);
        }
        return c0321a;
    }

    public synchronized byte[] m211b(File file) {
        byte[] bArr = null;
        synchronized (this) {
            if (file != null) {
                try {
                    bArr = bv.m764b(file);
                } catch (Throwable e) {
                    CBLogging.m78b("CBFileCache", "Error loading cache from disk", e);
                }
            }
        }
        return bArr;
    }

    public synchronized String[] m214c(File file) {
        String[] strArr;
        if (file == null) {
            strArr = null;
        } else {
            strArr = file.list();
        }
        return strArr;
    }

    public static synchronized HashMap<String, File> m191b() {
        HashMap<String, File> hashMap;
        int i = 0;
        synchronized (C0330h.class) {
            String[] g = C0330h.m198g();
            String[] f = C0330h.m196f();
            String[] e = C0330h.m195e();
            String[] c = C0330h.m192c();
            hashMap = new HashMap();
            if (f149a != null) {
                File file;
                if (g != null) {
                    if (g.length > 0) {
                        if (C0330h.m199i()) {
                            file = f154f;
                        } else {
                            file = f155g;
                        }
                        for (String str : g) {
                            if (!str.equals(".nomedia")) {
                                hashMap.put(str, new File(file, str));
                            }
                        }
                    }
                }
                if (f != null && f.length > 0) {
                    if (C0330h.m199i()) {
                        file = f158j;
                    } else {
                        file = f159k;
                    }
                    for (String str2 : f) {
                        if (!str2.equals(".nomedia")) {
                            hashMap.put(str2, new File(file, str2));
                        }
                    }
                }
                if (e != null && e.length > 0) {
                    if (C0330h.m199i()) {
                        file = f156h;
                    } else {
                        file = f157i;
                    }
                    for (String str3 : e) {
                        if (!str3.equals(".nomedia")) {
                            hashMap.put(str3, new File(file, str3));
                        }
                    }
                }
                if (c != null && c.length > 0) {
                    File file2;
                    if (C0330h.m199i()) {
                        file2 = f162n;
                    } else {
                        file2 = f163o;
                    }
                    int length = c.length;
                    while (i < length) {
                        String str4 = c[i];
                        if (!str4.equals(".nomedia")) {
                            hashMap.put(str4, new File(file2, str4));
                        }
                        i++;
                    }
                }
            }
        }
        return hashMap;
    }

    public static synchronized String[] m192c() {
        String[] strArr;
        synchronized (C0330h.class) {
            if (f149a == null) {
                strArr = null;
            } else {
                File file;
                if (C0330h.m199i()) {
                    file = f162n;
                } else {
                    file = f163o;
                }
                if (file.lastModified() > f147H) {
                    f148I = file.list();
                    f147H = file.lastModified();
                }
                strArr = f148I;
            }
        }
        return strArr;
    }

    public static synchronized String[] m194d() {
        String[] strArr = null;
        synchronized (C0330h.class) {
            if (f149a != null) {
                File file;
                if (C0330h.m199i()) {
                    file = f152d;
                } else {
                    file = f153e;
                }
                if (file != null) {
                    f146G = file.list();
                }
                if (!(f146G == null || f146G.length == 0)) {
                    strArr = f146G;
                }
            }
        }
        return strArr;
    }

    public static synchronized String[] m195e() {
        String[] strArr;
        synchronized (C0330h.class) {
            if (f149a == null) {
                strArr = null;
            } else {
                File file;
                if (C0330h.m199i()) {
                    file = f156h;
                } else {
                    file = f157i;
                }
                if (file.lastModified() > f144E) {
                    f145F = file.list();
                    f144E = file.lastModified();
                }
                strArr = f145F;
            }
        }
        return strArr;
    }

    public static String m188a(String str) {
        if (f149a == null) {
            return null;
        }
        File file;
        if (C0330h.m199i()) {
            file = f152d;
        } else {
            file = f153e;
        }
        File file2 = new File(file, str);
        if (file2.exists()) {
            return file2.getPath();
        }
        return null;
    }

    public static synchronized String[] m196f() {
        String[] strArr;
        synchronized (C0330h.class) {
            if (f149a == null) {
                strArr = null;
            } else {
                File file;
                if (C0330h.m199i()) {
                    file = f158j;
                } else {
                    file = f159k;
                }
                if (file.lastModified() > f142C) {
                    f143D = file.list();
                    f142C = file.lastModified();
                }
                strArr = f143D;
            }
        }
        return strArr;
    }

    public static synchronized String[] m198g() {
        String[] strArr;
        synchronized (C0330h.class) {
            if (f149a == null) {
                strArr = null;
            } else {
                File file;
                if (C0330h.m199i()) {
                    file = f154f;
                } else {
                    file = f155g;
                }
                if (file.lastModified() > f140A) {
                    f141B = file.list();
                    f140A = file.lastModified();
                }
                strArr = f141B;
            }
        }
        return strArr;
    }

    public static synchronized void m189a(ArrayList<String> arrayList, File file, boolean z) {
        ObjectOutputStream objectOutputStream;
        IOException e;
        FileOutputStream fileOutputStream = null;
        synchronized (C0330h.class) {
            if (!(file == null || arrayList == null)) {
                if (z) {
                    ArrayList<String> d = C0330h.m193d(file);
                    if (d.size() > 0) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            String str = (String) it.next();
                            if (!d.contains(str)) {
                                d.add(str);
                            }
                        }
                        arrayList = d;
                    }
                }
                try {
                    OutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        objectOutputStream = new ObjectOutputStream(fileOutputStream2);
                        try {
                            objectOutputStream.writeObject(arrayList);
                            objectOutputStream.close();
                            fileOutputStream2.close();
                        } catch (IOException e2) {
                            e = e2;
                            fileOutputStream = fileOutputStream2;
                            if (objectOutputStream != null) {
                                try {
                                    objectOutputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    e.printStackTrace();
                                }
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            e.printStackTrace();
                        }
                    } catch (IOException e4) {
                        e = e4;
                        objectOutputStream = null;
                        OutputStream outputStream = fileOutputStream2;
                        if (objectOutputStream != null) {
                            objectOutputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        e.printStackTrace();
                    }
                } catch (IOException e5) {
                    e = e5;
                    objectOutputStream = null;
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.util.ArrayList<java.lang.String> m193d(java.io.File r7) {
        /*
        r3 = 0;
        r5 = com.chartboost.sdk.Libraries.C0330h.class;
        monitor-enter(r5);
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x003b }
        r1.<init>();	 Catch:{ all -> 0x003b }
        if (r7 == 0) goto L_0x0011;
    L_0x000b:
        r0 = r7.exists();	 Catch:{ all -> 0x003b }
        if (r0 != 0) goto L_0x0014;
    L_0x0011:
        r0 = r1;
    L_0x0012:
        monitor-exit(r5);
        return r0;
    L_0x0014:
        r4 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x004d, ClassNotFoundException -> 0x0043 }
        r4.<init>(r7);	 Catch:{ IOException -> 0x004d, ClassNotFoundException -> 0x0043 }
        r2 = new java.io.ObjectInputStream;	 Catch:{ IOException -> 0x0053, ClassNotFoundException -> 0x0043 }
        r2.<init>(r4);	 Catch:{ IOException -> 0x0053, ClassNotFoundException -> 0x0043 }
        r0 = r2.readObject();	 Catch:{ IOException -> 0x005a, ClassNotFoundException -> 0x0043 }
        r0 = (java.util.ArrayList) r0;	 Catch:{ IOException -> 0x005a, ClassNotFoundException -> 0x0043 }
        r2.close();	 Catch:{ IOException -> 0x002b, ClassNotFoundException -> 0x004b }
        r4.close();	 Catch:{ IOException -> 0x002b, ClassNotFoundException -> 0x004b }
        goto L_0x0012;
    L_0x002b:
        r1 = move-exception;
        r3 = r4;
    L_0x002d:
        if (r2 == 0) goto L_0x0032;
    L_0x002f:
        r2.close();	 Catch:{ IOException -> 0x003e }
    L_0x0032:
        if (r3 == 0) goto L_0x0037;
    L_0x0034:
        r3.close();	 Catch:{ IOException -> 0x003e }
    L_0x0037:
        r1.printStackTrace();	 Catch:{ all -> 0x003b }
        goto L_0x0012;
    L_0x003b:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x003e:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x003b }
        goto L_0x0037;
    L_0x0043:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0047:
        r1.printStackTrace();	 Catch:{ all -> 0x003b }
        goto L_0x0012;
    L_0x004b:
        r1 = move-exception;
        goto L_0x0047;
    L_0x004d:
        r0 = move-exception;
        r2 = r3;
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x002d;
    L_0x0053:
        r0 = move-exception;
        r2 = r3;
        r3 = r4;
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x002d;
    L_0x005a:
        r0 = move-exception;
        r3 = r4;
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.Libraries.h.d(java.io.File):java.util.ArrayList<java.lang.String>");
    }

    public synchronized void m216e(File file) {
        if (file != null) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public synchronized void m209b(File file, String str) {
        if (file != null) {
            if (!TextUtils.isEmpty(str)) {
                m216e(m212c(file, str));
            }
        }
    }

    public synchronized void m218h() {
        int i = 0;
        synchronized (this) {
            if (f149a != null) {
                try {
                    if (f150b != null) {
                        File[] listFiles = f150b.listFiles();
                        if (listFiles != null) {
                            for (File delete : listFiles) {
                                delete.delete();
                            }
                        }
                    }
                    if (f151c != null) {
                        File[] listFiles2 = f151c.listFiles();
                        if (listFiles2 != null) {
                            int length = listFiles2.length;
                            while (i < length) {
                                listFiles2[i].delete();
                                i++;
                            }
                        }
                    }
                } catch (Exception e) {
                    CBLogging.m77b("CBFileCache", "Error while clearing the file cache");
                }
            }
        }
    }

    public synchronized void m217f(File file) {
        if (!(file == null || file == null)) {
            try {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File delete : listFiles) {
                        delete.delete();
                    }
                }
            } catch (Exception e) {
                CBLogging.m77b("CBFileCache", "Error while clearing the file cache");
            }
        }
    }

    public static boolean m199i() {
        if (Environment.getExternalStorageState().equals("mounted") && !C0351c.m341a()) {
            return true;
        }
        CBLogging.m83e("CBFileCache", "External Storage unavailable");
        return false;
    }

    public boolean m210b(String str) {
        if (m231v() == null || str == null) {
            return false;
        }
        return new File(f149a.getPath(), str).exists();
    }

    public boolean m213c(String str) {
        if (m227r() == null || str == null) {
            return false;
        }
        return new File(m227r(), str).exists();
    }

    public boolean m215d(String str) {
        if (m219j() == null || str == null) {
            return false;
        }
        return new File(m219j(), str).exists();
    }

    public File m212c(File file, String str) {
        if (file == null) {
            return null;
        }
        return new File(file.getPath(), str);
    }

    public static File m186a(C0329a c0329a, String str) {
        if (c0329a == null || TextUtils.isEmpty(str)) {
            CBLogging.m77b("CBFileCache", "Invalid directory or filename passed");
            return null;
        }
        Object obj;
        if (C0330h.m199i()) {
            obj = 1;
        } else {
            obj = null;
        }
        File file;
        if (c0329a == C0329a.StyleSheets) {
            if (obj != null) {
                file = f156h;
            } else {
                file = f157i;
            }
            return new File(file, str);
        } else if (c0329a == C0329a.Javascript) {
            if (obj != null) {
                file = f158j;
            } else {
                file = f159k;
            }
            return new File(file, str);
        } else if (c0329a == C0329a.Media) {
            if (obj != null) {
                file = f154f;
            } else {
                file = f155g;
            }
            return new File(file, str);
        } else if (c0329a == C0329a.Videos) {
            if (obj != null) {
                file = f152d;
            } else {
                file = f153e;
            }
            return new File(file, str);
        } else if (c0329a != C0329a.Html) {
            return null;
        } else {
            if (obj != null) {
                file = f162n;
            } else {
                file = f163o;
            }
            return new File(file, str);
        }
    }

    public File m219j() {
        System.out.println(this.f175J);
        System.out.println(!C0351c.m341a());
        System.out.println(f152d);
        System.out.println(f153e);
        if (!this.f175J || C0351c.m341a()) {
            return f153e;
        }
        return f152d;
    }

    public File m220k() {
        if (!this.f175J || C0351c.m341a()) {
            return f157i;
        }
        return f156h;
    }

    public File m221l() {
        if (!this.f175J || C0351c.m341a()) {
            return f159k;
        }
        return f158j;
    }

    public File m222m() {
        if (!this.f175J || C0351c.m341a()) {
            return f155g;
        }
        return f154f;
    }

    public File m223n() {
        return f172x;
    }

    public File m224o() {
        return f173y;
    }

    public File m225p() {
        return f174z;
    }

    public File m226q() {
        if (!this.f175J || C0351c.m341a()) {
            return f163o;
        }
        return f162n;
    }

    public File m227r() {
        if (!this.f175J || C0351c.m341a()) {
            return f167s;
        }
        return f166r;
    }

    public File m228s() {
        if (!this.f175J || C0351c.m341a()) {
            return f161m;
        }
        return f160l;
    }

    public File m229t() {
        return f165q;
    }

    public File m230u() {
        return f164p;
    }

    public File m231v() {
        if (f149a == null) {
            return null;
        }
        if (!this.f175J || C0351c.m341a()) {
            f149a = f151c;
        } else {
            f149a = f150b;
        }
        return f149a;
    }

    public static long m200w() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null || !C0330h.m199i()) {
            return 0;
        }
        return C0330h.m197g(externalStorageDirectory);
    }

    public static long m201x() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null || !C0330h.m199i()) {
            return 0;
        }
        StatFs statFs = new StatFs(externalStorageDirectory.getAbsolutePath());
        if (VERSION.SDK_INT >= 18) {
            return statFs.getBlockCountLong() * statFs.getBlockSizeLong();
        }
        return ((long) statFs.getBlockCount()) * ((long) statFs.getBlockSize());
    }

    private static long m197g(File file) {
        if (file == null || !file.exists()) {
            return 0;
        }
        StatFs statFs = new StatFs(file.getAbsolutePath());
        if (VERSION.SDK_INT >= 18) {
            return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong();
        }
        return ((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize());
    }

    public static C0321a m202y() {
        Object obj;
        File file;
        C0321a a;
        File file2;
        C0321a a2 = C0321a.m121a();
        if (C0330h.m199i()) {
            obj = 1;
        } else {
            obj = null;
        }
        a2.m128a(".chartboost-total-external-space", Long.valueOf(f168t.getTotalSpace()));
        a2.m128a(".chartboost-total-internal-space", Long.valueOf(f169u.getTotalSpace()));
        a2.m128a("sdCard-total-space", Long.valueOf(Environment.getExternalStorageDirectory().getTotalSpace()));
        a2.m128a("sdCard-free-space", Long.valueOf(Environment.getExternalStorageDirectory().getFreeSpace()));
        if (obj != null) {
            file = f156h;
        } else {
            file = f157i;
        }
        if (file != null) {
            a = C0321a.m121a();
            a.m128a("size", Long.valueOf(file.getTotalSpace()));
            if (file.list() != null) {
                a.m128a("count", Integer.valueOf(file.list().length));
            }
            a2.m128a("css", a);
        }
        if (obj != null) {
            file = f158j;
        } else {
            file = f158j;
        }
        if (file != null) {
            a = C0321a.m121a();
            a.m128a("size", Long.valueOf(file.getTotalSpace()));
            if (file.list() != null) {
                a.m128a("count", Integer.valueOf(file.list().length));
            }
            a2.m128a("js", a);
        }
        if (obj != null) {
            file = f154f;
        } else {
            file = f155g;
        }
        if (file != null) {
            a = C0321a.m121a();
            a.m128a("size", Long.valueOf(file.getTotalSpace()));
            if (file.list() != null) {
                a.m128a("count", Integer.valueOf(file.list().length));
            }
            a2.m128a("media", a);
        }
        if (obj != null) {
            file2 = f162n;
        } else {
            file2 = f163o;
        }
        if (file2 != null) {
            C0321a a3 = C0321a.m121a();
            a3.m128a("size", Long.valueOf(file2.getTotalSpace()));
            if (file2.list() != null) {
                a3.m128a("count", Integer.valueOf(file2.list().length));
            }
            a2.m128a(Method.HTML, a3);
        }
        return a2;
    }

    public static boolean m190a(File file, int i) {
        if (file == null || !file.exists()) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.add(6, -i);
        if (!new Date(file.lastModified()).before(instance.getTime())) {
            return false;
        }
        CBLogging.m75a("CBFileCache", "File is expired and is past " + i + " days");
        return true;
    }
}
