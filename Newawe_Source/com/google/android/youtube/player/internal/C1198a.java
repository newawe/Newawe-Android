package com.google.android.youtube.player.internal;

import android.graphics.Bitmap;
import com.google.android.youtube.player.YouTubeThumbnailLoader;
import com.google.android.youtube.player.YouTubeThumbnailLoader.ErrorReason;
import com.google.android.youtube.player.YouTubeThumbnailLoader.OnThumbnailLoadedListener;
import com.google.android.youtube.player.YouTubeThumbnailView;
import java.lang.ref.WeakReference;
import java.util.NoSuchElementException;

/* renamed from: com.google.android.youtube.player.internal.a */
public abstract class C1198a implements YouTubeThumbnailLoader {
    private final WeakReference<YouTubeThumbnailView> f3669a;
    private OnThumbnailLoadedListener f3670b;
    private boolean f3671c;
    private boolean f3672d;

    public C1198a(YouTubeThumbnailView youTubeThumbnailView) {
        this.f3669a = new WeakReference(ab.m879a((Object) youTubeThumbnailView));
    }

    private void m4154i() {
        if (!m4158a()) {
            throw new IllegalStateException("This YouTubeThumbnailLoader has been released");
        }
    }

    public final void m4155a(Bitmap bitmap, String str) {
        YouTubeThumbnailView youTubeThumbnailView = (YouTubeThumbnailView) this.f3669a.get();
        if (m4158a() && youTubeThumbnailView != null) {
            youTubeThumbnailView.setImageBitmap(bitmap);
            if (this.f3670b != null) {
                this.f3670b.onThumbnailLoaded(youTubeThumbnailView, str);
            }
        }
    }

    public abstract void m4156a(String str);

    public abstract void m4157a(String str, int i);

    protected boolean m4158a() {
        return !this.f3672d;
    }

    public final void m4159b() {
        if (m4158a()) {
            C0678y.m971a("The finalize() method for a YouTubeThumbnailLoader has work to do. You should have called release().", new Object[0]);
            release();
        }
    }

    public final void m4160b(String str) {
        YouTubeThumbnailView youTubeThumbnailView = (YouTubeThumbnailView) this.f3669a.get();
        if (m4158a() && this.f3670b != null && youTubeThumbnailView != null) {
            ErrorReason valueOf;
            try {
                valueOf = ErrorReason.valueOf(str);
            } catch (IllegalArgumentException e) {
                valueOf = ErrorReason.UNKNOWN;
            } catch (NullPointerException e2) {
                valueOf = ErrorReason.UNKNOWN;
            }
            this.f3670b.onThumbnailError(youTubeThumbnailView, valueOf);
        }
    }

    public abstract void m4161c();

    public abstract void m4162d();

    public abstract void m4163e();

    public abstract boolean m4164f();

    public final void first() {
        m4154i();
        if (this.f3671c) {
            m4163e();
            return;
        }
        throw new IllegalStateException("Must call setPlaylist first");
    }

    public abstract boolean m4165g();

    public abstract void m4166h();

    public final boolean hasNext() {
        m4154i();
        return m4164f();
    }

    public final boolean hasPrevious() {
        m4154i();
        return m4165g();
    }

    public final void next() {
        m4154i();
        if (!this.f3671c) {
            throw new IllegalStateException("Must call setPlaylist first");
        } else if (m4164f()) {
            m4161c();
        } else {
            throw new NoSuchElementException("Called next at end of playlist");
        }
    }

    public final void previous() {
        m4154i();
        if (!this.f3671c) {
            throw new IllegalStateException("Must call setPlaylist first");
        } else if (m4165g()) {
            m4162d();
        } else {
            throw new NoSuchElementException("Called previous at start of playlist");
        }
    }

    public final void release() {
        if (m4158a()) {
            this.f3672d = true;
            this.f3670b = null;
            m4166h();
        }
    }

    public final void setOnThumbnailLoadedListener(OnThumbnailLoadedListener onThumbnailLoadedListener) {
        m4154i();
        this.f3670b = onThumbnailLoadedListener;
    }

    public final void setPlaylist(String str) {
        setPlaylist(str, 0);
    }

    public final void setPlaylist(String str, int i) {
        m4154i();
        this.f3671c = true;
        m4157a(str, i);
    }

    public final void setVideo(String str) {
        m4154i();
        this.f3671c = false;
        m4156a(str);
    }
}
