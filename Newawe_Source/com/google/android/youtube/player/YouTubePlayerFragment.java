package com.google.android.youtube.player;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView.C0651b;
import com.google.android.youtube.player.internal.ab;

public class YouTubePlayerFragment extends Fragment implements Provider {
    private final C1193a f3639a;
    private Bundle f3640b;
    private YouTubePlayerView f3641c;
    private String f3642d;
    private OnInitializedListener f3643e;
    private boolean f3644f;

    /* renamed from: com.google.android.youtube.player.YouTubePlayerFragment.a */
    private final class C1193a implements C0651b {
        final /* synthetic */ YouTubePlayerFragment f3638a;

        private C1193a(YouTubePlayerFragment youTubePlayerFragment) {
            this.f3638a = youTubePlayerFragment;
        }

        public final void m4117a(YouTubePlayerView youTubePlayerView) {
        }

        public final void m4118a(YouTubePlayerView youTubePlayerView, String str, OnInitializedListener onInitializedListener) {
            this.f3638a.initialize(str, this.f3638a.f3643e);
        }
    }

    public YouTubePlayerFragment() {
        this.f3639a = new C1193a();
    }

    private void m4120a() {
        if (this.f3641c != null && this.f3643e != null) {
            this.f3641c.m4143a(this.f3644f);
            this.f3641c.m4142a(getActivity(), this, this.f3642d, this.f3643e, this.f3640b);
            this.f3640b = null;
            this.f3643e = null;
        }
    }

    public static YouTubePlayerFragment newInstance() {
        return new YouTubePlayerFragment();
    }

    public void initialize(String str, OnInitializedListener onInitializedListener) {
        this.f3642d = ab.m881a(str, (Object) "Developer key cannot be null or empty");
        this.f3643e = onInitializedListener;
        m4120a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3640b = bundle != null ? bundle.getBundle("YouTubePlayerFragment.KEY_PLAYER_VIEW_STATE") : null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3641c = new YouTubePlayerView(getActivity(), null, 0, this.f3639a);
        m4120a();
        return this.f3641c;
    }

    public void onDestroy() {
        if (this.f3641c != null) {
            Activity activity = getActivity();
            YouTubePlayerView youTubePlayerView = this.f3641c;
            boolean z = activity == null || activity.isFinishing();
            youTubePlayerView.m4145b(z);
        }
        super.onDestroy();
    }

    public void onDestroyView() {
        this.f3641c.m4147c(getActivity().isFinishing());
        this.f3641c = null;
        super.onDestroyView();
    }

    public void onPause() {
        this.f3641c.m4146c();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.f3641c.m4144b();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBundle("YouTubePlayerFragment.KEY_PLAYER_VIEW_STATE", this.f3641c != null ? this.f3641c.m4149e() : this.f3640b);
    }

    public void onStart() {
        super.onStart();
        this.f3641c.m4141a();
    }

    public void onStop() {
        this.f3641c.m4148d();
        super.onStop();
    }
}
