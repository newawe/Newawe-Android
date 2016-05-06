package com.google.android.youtube.player;

import android.app.Activity;
import android.os.Bundle;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayerView.C0651b;

public class YouTubeBaseActivity extends Activity {
    private C1192a f793a;
    private YouTubePlayerView f794b;
    private int f795c;
    private Bundle f796d;

    /* renamed from: com.google.android.youtube.player.YouTubeBaseActivity.a */
    private final class C1192a implements C0651b {
        final /* synthetic */ YouTubeBaseActivity f3637a;

        private C1192a(YouTubeBaseActivity youTubeBaseActivity) {
            this.f3637a = youTubeBaseActivity;
        }

        public final void m4115a(YouTubePlayerView youTubePlayerView) {
            if (!(this.f3637a.f794b == null || this.f3637a.f794b == youTubePlayerView)) {
                this.f3637a.f794b.m4147c(true);
            }
            this.f3637a.f794b = youTubePlayerView;
            if (this.f3637a.f795c > 0) {
                youTubePlayerView.m4141a();
            }
            if (this.f3637a.f795c >= 2) {
                youTubePlayerView.m4144b();
            }
        }

        public final void m4116a(YouTubePlayerView youTubePlayerView, String str, OnInitializedListener onInitializedListener) {
            youTubePlayerView.m4142a(this.f3637a, youTubePlayerView, str, onInitializedListener, this.f3637a.f796d);
            this.f3637a.f796d = null;
        }
    }

    final C0651b m860a() {
        return this.f793a;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f793a = new C1192a();
        this.f796d = bundle != null ? bundle.getBundle("YouTubeBaseActivity.KEY_PLAYER_VIEW_STATE") : null;
    }

    protected void onDestroy() {
        if (this.f794b != null) {
            this.f794b.m4145b(isFinishing());
        }
        super.onDestroy();
    }

    protected void onPause() {
        this.f795c = 1;
        if (this.f794b != null) {
            this.f794b.m4146c();
        }
        super.onPause();
    }

    protected void onResume() {
        super.onResume();
        this.f795c = 2;
        if (this.f794b != null) {
            this.f794b.m4144b();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBundle("YouTubeBaseActivity.KEY_PLAYER_VIEW_STATE", this.f794b != null ? this.f794b.m4149e() : this.f796d);
    }

    protected void onStart() {
        super.onStart();
        this.f795c = 1;
        if (this.f794b != null) {
            this.f794b.m4141a();
        }
    }

    protected void onStop() {
        this.f795c = 0;
        if (this.f794b != null) {
            this.f794b.m4148d();
        }
        super.onStop();
    }
}
