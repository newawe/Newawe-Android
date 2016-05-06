package com.google.android.youtube.player;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.google.android.youtube.player.internal.C0673t.C0671a;
import com.google.android.youtube.player.internal.C0673t.C0672b;
import com.google.android.youtube.player.internal.C1198a;
import com.google.android.youtube.player.internal.C1199b;
import com.google.android.youtube.player.internal.aa;
import com.google.android.youtube.player.internal.ab;

public final class YouTubeThumbnailView extends ImageView {
    private C1199b f806a;
    private C1198a f807b;

    public interface OnInitializedListener {
        void onInitializationFailure(YouTubeThumbnailView youTubeThumbnailView, YouTubeInitializationResult youTubeInitializationResult);

        void onInitializationSuccess(YouTubeThumbnailView youTubeThumbnailView, YouTubeThumbnailLoader youTubeThumbnailLoader);
    }

    /* renamed from: com.google.android.youtube.player.YouTubeThumbnailView.a */
    private static final class C1197a implements C0671a, C0672b {
        private YouTubeThumbnailView f3667a;
        private OnInitializedListener f3668b;

        public C1197a(YouTubeThumbnailView youTubeThumbnailView, OnInitializedListener onInitializedListener) {
            this.f3667a = (YouTubeThumbnailView) ab.m880a((Object) youTubeThumbnailView, (Object) "thumbnailView cannot be null");
            this.f3668b = (OnInitializedListener) ab.m880a((Object) onInitializedListener, (Object) "onInitializedlistener cannot be null");
        }

        private void m4150c() {
            if (this.f3667a != null) {
                this.f3667a.f806a = null;
                this.f3667a = null;
                this.f3668b = null;
            }
        }

        public final void m4151a() {
            if (this.f3667a != null && this.f3667a.f806a != null) {
                this.f3667a.f807b = aa.m874a().m876a(this.f3667a.f806a, this.f3667a);
                this.f3668b.onInitializationSuccess(this.f3667a, this.f3667a.f807b);
                m4150c();
            }
        }

        public final void m4152a(YouTubeInitializationResult youTubeInitializationResult) {
            this.f3668b.onInitializationFailure(this.f3667a, youTubeInitializationResult);
            m4150c();
        }

        public final void m4153b() {
            m4150c();
        }
    }

    public YouTubeThumbnailView(Context context) {
        this(context, null);
    }

    public YouTubeThumbnailView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public YouTubeThumbnailView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    protected final void finalize() throws Throwable {
        if (this.f807b != null) {
            this.f807b.m4159b();
            this.f807b = null;
        }
        super.finalize();
    }

    public final void initialize(String str, OnInitializedListener onInitializedListener) {
        Object c1197a = new C1197a(this, onInitializedListener);
        this.f806a = aa.m874a().m877a(getContext(), str, c1197a, c1197a);
        this.f806a.m964e();
    }
}
