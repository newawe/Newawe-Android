package com.google.android.youtube.player;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.youtube.player.YouTubePlayer.OnInitializedListener;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView.C0651b;
import com.google.android.youtube.player.internal.ab;

public class YouTubePlayerSupportFragment extends Fragment implements Provider {
    private final C1194a f3646a;
    private Bundle f3647b;
    private YouTubePlayerView f3648c;
    private String f3649d;
    private OnInitializedListener f3650e;
    private boolean f3651f;

    /* renamed from: com.google.android.youtube.player.YouTubePlayerSupportFragment.a */
    private final class C1194a implements C0651b {
        final /* synthetic */ YouTubePlayerSupportFragment f3645a;

        private C1194a(YouTubePlayerSupportFragment youTubePlayerSupportFragment) {
            this.f3645a = youTubePlayerSupportFragment;
        }

        public final void m4121a(YouTubePlayerView youTubePlayerView) {
        }

        public final void m4122a(YouTubePlayerView youTubePlayerView, String str, OnInitializedListener onInitializedListener) {
            this.f3645a.initialize(str, this.f3645a.f3650e);
        }
    }

    public YouTubePlayerSupportFragment() {
        this.f3646a = new C1194a();
    }

    private void m4124a() {
        if (this.f3648c != null && this.f3650e != null) {
            this.f3648c.m4143a(this.f3651f);
            this.f3648c.m4142a(getActivity(), this, this.f3649d, this.f3650e, this.f3647b);
            this.f3647b = null;
            this.f3650e = null;
        }
    }

    public static YouTubePlayerSupportFragment newInstance() {
        return new YouTubePlayerSupportFragment();
    }

    public void initialize(String str, OnInitializedListener onInitializedListener) {
        this.f3649d = ab.m881a(str, (Object) "Developer key cannot be null or empty");
        this.f3650e = onInitializedListener;
        m4124a();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3647b = bundle != null ? bundle.getBundle("YouTubePlayerSupportFragment.KEY_PLAYER_VIEW_STATE") : null;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.f3648c = new YouTubePlayerView(getActivity(), null, 0, this.f3646a);
        m4124a();
        return this.f3648c;
    }

    public void onDestroy() {
        if (this.f3648c != null) {
            Activity activity = getActivity();
            YouTubePlayerView youTubePlayerView = this.f3648c;
            boolean z = activity == null || activity.isFinishing();
            youTubePlayerView.m4145b(z);
        }
        super.onDestroy();
    }

    public void onDestroyView() {
        this.f3648c.m4147c(getActivity().isFinishing());
        this.f3648c = null;
        super.onDestroyView();
    }

    public void onPause() {
        this.f3648c.m4146c();
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        this.f3648c.m4144b();
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBundle("YouTubePlayerSupportFragment.KEY_PLAYER_VIEW_STATE", this.f3648c != null ? this.f3648c.m4149e() : this.f3647b);
    }

    public void onStart() {
        super.onStart();
        this.f3648c.m4141a();
    }

    public void onStop() {
        this.f3648c.m4148d();
        super.onStop();
    }
}
