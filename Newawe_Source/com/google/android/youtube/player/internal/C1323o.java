package com.google.android.youtube.player.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.youtube.player.internal.C0661l.C1219a;
import com.google.android.youtube.player.internal.C0673t.C0671a;
import com.google.android.youtube.player.internal.C0673t.C0672b;
import com.google.android.youtube.player.internal.C1221r.C1326d;

/* renamed from: com.google.android.youtube.player.internal.o */
public final class C1323o extends C1221r<C0661l> implements C1199b {
    private final String f4238b;
    private final String f4239c;
    private final String f4240d;
    private boolean f4241e;

    public C1323o(Context context, String str, String str2, String str3, C0671a c0671a, C0672b c0672b) {
        super(context, c0671a, c0672b);
        this.f4238b = (String) ab.m879a((Object) str);
        this.f4239c = ab.m881a(str2, (Object) "callingPackage cannot be null or empty");
        this.f4240d = ab.m881a(str3, (Object) "callingAppVersion cannot be null or empty");
    }

    private final void m5106k() {
        m4269i();
        if (this.f4241e) {
            throw new IllegalStateException("Connection client has been released");
        }
    }

    public final IBinder m5107a() {
        m5106k();
        try {
            return ((C0661l) m4270j()).m951a();
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    protected final /* synthetic */ IInterface m5108a(IBinder iBinder) {
        return C1219a.m4247a(iBinder);
    }

    public final C0660k m5109a(C0659j c0659j) {
        m5106k();
        try {
            return ((C0661l) m4270j()).m952a(c0659j);
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    protected final void m5110a(C0658i c0658i, C1326d c1326d) throws RemoteException {
        c0658i.m942a(c1326d, 1202, this.f4239c, this.f4240d, this.f4238b, null);
    }

    public final void m5111a(boolean z) {
        if (m4266f()) {
            try {
                ((C0661l) m4270j()).m953a(z);
            } catch (RemoteException e) {
            }
            this.f4241e = true;
        }
    }

    protected final String m5112b() {
        return "com.google.android.youtube.player.internal.IYouTubeService";
    }

    protected final String m5113c() {
        return "com.google.android.youtube.api.service.START";
    }

    public final void m5114d() {
        if (!this.f4241e) {
            m5111a(true);
        }
        super.m4264d();
    }
}
