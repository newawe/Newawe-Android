package com.chartboost.sdk.impl;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import com.chartboost.sdk.C0351c;
import com.chartboost.sdk.Libraries.C0315b;
import com.chartboost.sdk.Libraries.C0330h;
import com.chartboost.sdk.Libraries.C1018j.C0333a;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.impl.C0472n.C0470a;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

public final class bc {
    private static volatile bc f568c;
    private C0330h f569a;
    private Map<String, C0333a> f570b;

    /* renamed from: com.chartboost.sdk.impl.bc.a */
    private class C0412a implements Runnable {
        final /* synthetic */ bc f561a;
        private String f562b;
        private final WeakReference<ImageView> f563c;
        private C0413b f564d;
        private String f565e;
        private Bundle f566f;

        /* renamed from: com.chartboost.sdk.impl.bc.a.3 */
        class C04113 implements Runnable {
            final /* synthetic */ C0333a f559a;
            final /* synthetic */ C0412a f560b;

            C04113(C0412a c0412a, C0333a c0333a) {
                this.f560b = c0412a;
                this.f559a = c0333a;
            }

            public void run() {
                if (this.f560b.f563c != null) {
                    ImageView imageView = (ImageView) this.f560b.f563c.get();
                    C0412a a = bc.m655b(imageView);
                    if (this.f559a != null && this.f560b == a) {
                        imageView.setImageBitmap(this.f559a.m240a());
                    }
                }
                if (this.f560b.f564d != null) {
                    this.f560b.f564d.m647a(this.f559a, this.f560b.f566f);
                }
            }
        }

        /* renamed from: com.chartboost.sdk.impl.bc.a.1 */
        class C10541 implements C0470a {
            final /* synthetic */ C0412a f3541a;

            C10541(C0412a c0412a) {
                this.f3541a = c0412a;
            }

            public void m3955a(C0475s c0475s) {
                CBLogging.m77b("CBWebImageCache", "Error downloading the bitmap image from the server");
                if (!(c0475s == null || TextUtils.isEmpty(c0475s.getMessage()))) {
                    CBLogging.m77b("CBWebImageCache", c0475s.getMessage());
                }
                if (c0475s != null && c0475s.f770a != null) {
                    CBLogging.m77b("CBWebImageCache", "Error status Code: " + c0475s.f770a.f729a);
                }
            }
        }

        /* renamed from: com.chartboost.sdk.impl.bc.a.2 */
        class C10552 extends C0467l<String> {
            final /* synthetic */ C0412a f3542a;

            C10552(C0412a c0412a, int i, String str, C0470a c0470a) {
                this.f3542a = c0412a;
                super(i, str, c0470a);
            }

            protected /* synthetic */ void m3957b(Object obj) {
                m3958c((String) obj);
            }

            protected C0472n<String> m3956a(C0464i c0464i) {
                try {
                    byte[] bArr = c0464i.f730b;
                    String b = C0315b.m103b(C0315b.m102a(bArr));
                    if (TextUtils.isEmpty(b)) {
                        b = StringUtils.EMPTY;
                    }
                    if (!b.equals(this.f3542a.f565e)) {
                        this.f3542a.f565e = b;
                        CBLogging.m77b("CBWebImageCache:ImageDownloader", "Error: checksum did not match while downloading from " + this.f3542a.f562b);
                    }
                    this.f3542a.f561a.f569a.m208a(this.f3542a.f561a.f569a.m227r(), String.format("%s%s", new Object[]{this.f3542a.f565e, ".png"}), bArr);
                    this.f3542a.f561a.m650a(this.f3542a.f565e);
                    return C0472n.m826a(null, null);
                } catch (Exception e) {
                    return C0472n.m825a(new C0475s("Bitmap response data is empty, unable to download the bitmap"));
                }
            }

            protected void m3958c(String str) {
                this.f3542a.m646a();
            }
        }

        public C0412a(bc bcVar, ImageView imageView, C0413b c0413b, String str, Bundle bundle, String str2) {
            this.f561a = bcVar;
            this.f563c = new WeakReference(imageView);
            Drawable c0414c = new C0414c(this);
            if (imageView != null) {
                imageView.setImageDrawable(c0414c);
            }
            this.f565e = str;
            this.f564d = c0413b;
            this.f566f = bundle;
            this.f562b = str2;
        }

        public void run() {
            if (this.f561a.m657b(this.f565e)) {
                m646a();
                return;
            }
            C0470a c10541 = new C10541(this);
            CBLogging.m75a("CBWebImageCache", "downloading image to cache... " + this.f562b);
            ba.m610a(C0351c.m378y()).m623a().m815a(new C10552(this, 0, this.f562b, c10541));
        }

        public void m646a() {
            C0333a b = m641b();
            if (!(b == null || this.f563c == null || this.f563c.get() == null || this != bc.m655b((ImageView) this.f563c.get()))) {
                b.m241b();
            }
            CBUtility.m96e().post(new C04113(this, b));
        }

        private C0333a m641b() {
            return (C0333a) this.f561a.f570b.get(this.f565e);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bc.b */
    public interface C0413b {
        void m647a(C0333a c0333a, Bundle bundle);
    }

    /* renamed from: com.chartboost.sdk.impl.bc.c */
    static class C0414c extends BitmapDrawable {
        private final WeakReference<C0412a> f567a;

        public C0414c(C0412a c0412a) {
            this.f567a = new WeakReference(c0412a);
        }

        public C0412a m648a() {
            return (C0412a) this.f567a.get();
        }
    }

    static {
        f568c = null;
    }

    public static bc m652a() {
        if (f568c == null) {
            synchronized (bc.class) {
                if (f568c == null) {
                    f568c = new bc();
                }
            }
        }
        return f568c;
    }

    private bc() {
        this.f569a = new C0330h(true);
        this.f570b = new HashMap();
    }

    public void m659b() {
        this.f569a.m218h();
        this.f570b.clear();
    }

    public void m658a(String str, String str2, C0413b c0413b, ImageView imageView, Bundle bundle) {
        C0333a a = m650a(str2);
        if (a != null) {
            if (imageView != null) {
                imageView.setImageBitmap(a.m240a());
            }
            if (c0413b != null) {
                c0413b.m647a(a, bundle);
                return;
            }
            return;
        }
        if (str == null && c0413b != null) {
            c0413b.m647a(null, bundle);
        }
        ax.m541a().execute(new C0412a(this, imageView, c0413b, str2, bundle, str));
    }

    private C0333a m650a(String str) {
        if (!m657b(str)) {
            if (this.f570b.containsKey(str)) {
                this.f570b.remove(str);
            }
            return null;
        } else if (this.f570b.containsKey(str)) {
            return (C0333a) this.f570b.get(str);
        } else {
            C0333a c0333a = new C0333a(str, this.f569a.m212c(this.f569a.m227r(), String.format("%s%s", new Object[]{str, ".png"})), this.f569a);
            this.f570b.put(str, c0333a);
            return c0333a;
        }
    }

    private static C0412a m655b(ImageView imageView) {
        if (imageView != null) {
            Drawable drawable = imageView.getDrawable();
            if (drawable instanceof C0414c) {
                return ((C0414c) drawable).m648a();
            }
        }
        return null;
    }

    private boolean m657b(String str) {
        return this.f569a.m213c(String.format("%s%s", new Object[]{str, ".png"}));
    }
}
