package com.chartboost.sdk.Libraries;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.Newawe.storage.DatabaseOpenHelper;
import com.android.volley.DefaultRetryPolicy;
import com.chartboost.sdk.C0372g;
import com.chartboost.sdk.C0372g.C0371b;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.impl.bc;
import com.chartboost.sdk.impl.bc.C0413b;
import java.io.File;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.chartboost.sdk.Libraries.j */
public final class C1018j implements C0371b {
    private C0333a f3393a;
    private C0372g f3394b;
    private String f3395c;
    private float f3396d;
    private C0413b f3397e;

    /* renamed from: com.chartboost.sdk.Libraries.j.2 */
    class C03322 implements Runnable {
        final /* synthetic */ C0321a f177a;
        final /* synthetic */ String f178b;
        final /* synthetic */ Bundle f179c;
        final /* synthetic */ C1018j f180d;

        C03322(C1018j c1018j, C0321a c0321a, String str, Bundle bundle) {
            this.f180d = c1018j;
            this.f177a = c0321a;
            this.f178b = str;
            this.f179c = bundle;
        }

        public void run() {
            String str = StringUtils.EMPTY;
            if (!(this.f177a.m138e("checksum") == null || this.f177a.m138e("checksum").isEmpty())) {
                str = this.f177a.m138e("checksum");
            }
            bc.m652a().m658a(this.f178b, str, this.f180d.f3397e, null, this.f179c == null ? new Bundle() : this.f179c);
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.j.a */
    public static class C0333a {
        private int f181a;
        private String f182b;
        private File f183c;
        private Bitmap f184d;
        private C0330h f185e;
        private int f186f;
        private int f187g;

        public C0333a(String str, File file, C0330h c0330h) {
            this.f186f = -1;
            this.f187g = -1;
            this.f183c = file;
            this.f182b = str;
            this.f184d = null;
            this.f181a = 1;
            this.f185e = c0330h;
        }

        public Bitmap m240a() {
            if (this.f184d == null) {
                m241b();
            }
            return this.f184d;
        }

        public void m241b() {
            if (this.f184d == null) {
                CBLogging.m75a("MemoryBitmap", "Loading image '" + this.f182b + "' from cache");
                byte[] b = this.f185e.m211b(this.f183c);
                if (b == null) {
                    CBLogging.m77b("MemoryBitmap", "decode() - bitmap not found");
                    return;
                }
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeByteArray(b, 0, b.length, options);
                Options options2 = new Options();
                options2.inJustDecodeBounds = false;
                options2.inDither = false;
                options2.inPurgeable = true;
                options2.inInputShareable = true;
                options2.inTempStorage = new byte[AccessibilityNodeInfoCompat.ACTION_PASTE];
                options2.inSampleSize = 1;
                while (options2.inSampleSize < 32) {
                    try {
                        this.f184d = BitmapFactory.decodeByteArray(b, 0, b.length, options2);
                        break;
                    } catch (Throwable e) {
                        CBLogging.m78b("MemoryBitmap", "OutOfMemoryError suppressed - trying larger sample size", e);
                        options2.inSampleSize *= 2;
                    } catch (Throwable e2) {
                        CBLogging.m78b("MemoryBitmap", "Exception raised decoding bitmap", e2);
                    }
                }
                this.f181a = options2.inSampleSize;
            }
        }

        public void m242c() {
            try {
                if (!(this.f184d == null || this.f184d.isRecycled())) {
                    this.f184d.recycle();
                }
            } catch (Exception e) {
            }
            this.f184d = null;
        }

        public int m243d() {
            if (this.f184d != null) {
                return this.f184d.getWidth();
            }
            if (this.f186f >= 0) {
                return this.f186f;
            }
            m239f();
            return this.f186f;
        }

        public int m244e() {
            if (this.f184d != null) {
                return this.f184d.getHeight();
            }
            if (this.f187g >= 0) {
                return this.f187g;
            }
            m239f();
            return this.f187g;
        }

        private void m239f() {
            try {
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(this.f183c.getAbsolutePath(), options);
                this.f186f = options.outWidth;
                this.f187g = options.outHeight;
            } catch (Throwable e) {
                CBLogging.m78b("MemoryBitmap", "Error decoding file size", e);
            }
        }
    }

    /* renamed from: com.chartboost.sdk.Libraries.j.1 */
    class C10171 implements C0413b {
        final /* synthetic */ C1018j f3392a;

        C10171(C1018j c1018j) {
            this.f3392a = c1018j;
        }

        public void m3716a(C0333a c0333a, Bundle bundle) {
            this.f3392a.f3393a = c0333a;
            this.f3392a.f3394b.m469a(this.f3392a);
        }
    }

    public C1018j(C0372g c0372g) {
        this.f3396d = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        this.f3397e = new C10171(this);
        this.f3394b = c0372g;
    }

    public int m3723b() {
        return this.f3393a.m243d() * this.f3393a.f181a;
    }

    public int m3724c() {
        return this.f3393a.m244e() * this.f3393a.f181a;
    }

    public void m3721a(String str) {
        m3720a(this.f3394b.m481g(), str, new Bundle());
    }

    public void m3720a(C0321a c0321a, String str, Bundle bundle) {
        C0321a a = c0321a.m127a(str);
        this.f3395c = str;
        if (!a.m131b()) {
            Object e = a.m138e(DatabaseOpenHelper.HISTORY_ROW_URL);
            this.f3396d = a.m127a("scale").m125a((float) DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            if (!TextUtils.isEmpty(e)) {
                this.f3394b.m475b((C0371b) this);
                CBUtility.m96e().post(new C03322(this, a, e, bundle));
            }
        }
    }

    public boolean m3722a() {
        return m3726e();
    }

    public void m3725d() {
        if (this.f3393a != null) {
            this.f3393a.m242c();
        }
    }

    public boolean m3726e() {
        return this.f3393a != null;
    }

    public Bitmap m3727f() {
        return this.f3393a != null ? this.f3393a.m240a() : null;
    }

    public float m3728g() {
        return this.f3396d;
    }

    public int m3729h() {
        return Math.round(((float) m3723b()) / this.f3396d);
    }

    public int m3730i() {
        return Math.round(((float) m3724c()) / this.f3396d);
    }
}
