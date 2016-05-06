package com.google.android.youtube.player.internal;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.youtube.player.YouTubeThumbnailView;
import com.google.android.youtube.player.internal.C0659j.C1215a;

/* renamed from: com.google.android.youtube.player.internal.p */
public final class C1325p extends C1198a {
    private final Handler f4243a;
    private C1199b f4244b;
    private C0660k f4245c;
    private boolean f4246d;
    private boolean f4247e;

    /* renamed from: com.google.android.youtube.player.internal.p.a */
    private final class C1324a extends C1215a {
        final /* synthetic */ C1325p f4242a;

        /* renamed from: com.google.android.youtube.player.internal.p.a.1 */
        class C06641 implements Runnable {
            final /* synthetic */ boolean f821a;
            final /* synthetic */ boolean f822b;
            final /* synthetic */ Bitmap f823c;
            final /* synthetic */ String f824d;
            final /* synthetic */ C1324a f825e;

            C06641(C1324a c1324a, boolean z, boolean z2, Bitmap bitmap, String str) {
                this.f825e = c1324a;
                this.f821a = z;
                this.f822b = z2;
                this.f823c = bitmap;
                this.f824d = str;
            }

            public final void run() {
                this.f825e.f4242a.f4246d = this.f821a;
                this.f825e.f4242a.f4247e = this.f822b;
                this.f825e.f4242a.m4155a(this.f823c, this.f824d);
            }
        }

        /* renamed from: com.google.android.youtube.player.internal.p.a.2 */
        class C06652 implements Runnable {
            final /* synthetic */ boolean f826a;
            final /* synthetic */ boolean f827b;
            final /* synthetic */ String f828c;
            final /* synthetic */ C1324a f829d;

            C06652(C1324a c1324a, boolean z, boolean z2, String str) {
                this.f829d = c1324a;
                this.f826a = z;
                this.f827b = z2;
                this.f828c = str;
            }

            public final void run() {
                this.f829d.f4242a.f4246d = this.f826a;
                this.f829d.f4242a.f4247e = this.f827b;
                this.f829d.f4242a.m4160b(this.f828c);
            }
        }

        private C1324a(C1325p c1325p) {
            this.f4242a = c1325p;
        }

        public final void m5115a(Bitmap bitmap, String str, boolean z, boolean z2) {
            this.f4242a.f4243a.post(new C06641(this, z, z2, bitmap, str));
        }

        public final void m5116a(String str, boolean z, boolean z2) {
            this.f4242a.f4243a.post(new C06652(this, z, z2, str));
        }
    }

    public C1325p(C1199b c1199b, YouTubeThumbnailView youTubeThumbnailView) {
        super(youTubeThumbnailView);
        this.f4244b = (C1199b) ab.m880a((Object) c1199b, (Object) "connectionClient cannot be null");
        this.f4245c = c1199b.m4171a(new C1324a());
        this.f4243a = new Handler(Looper.getMainLooper());
    }

    public final void m5120a(String str) {
        try {
            this.f4245c.m946a(str);
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public final void m5121a(String str, int i) {
        try {
            this.f4245c.m947a(str, i);
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    protected final boolean m5122a() {
        return super.m4158a() && this.f4245c != null;
    }

    public final void m5123c() {
        try {
            this.f4245c.m945a();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public final void m5124d() {
        try {
            this.f4245c.m948b();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public final void m5125e() {
        try {
            this.f4245c.m949c();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    public final boolean m5126f() {
        return this.f4247e;
    }

    public final boolean m5127g() {
        return this.f4246d;
    }

    public final void m5128h() {
        try {
            this.f4245c.m950d();
        } catch (RemoteException e) {
        }
        this.f4244b.m963d();
        this.f4245c = null;
        this.f4244b = null;
    }
}
