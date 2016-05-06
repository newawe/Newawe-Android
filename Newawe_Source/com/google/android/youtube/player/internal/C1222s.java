package com.google.android.youtube.player.internal;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.View;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.ErrorReason;
import com.google.android.youtube.player.YouTubePlayer.OnFullscreenListener;
import com.google.android.youtube.player.YouTubePlayer.PlaybackEventListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStateChangeListener;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayer.PlaylistEventListener;
import com.google.android.youtube.player.internal.C0654e.C1205a;
import com.google.android.youtube.player.internal.C0655f.C1207a;
import com.google.android.youtube.player.internal.C0656g.C1209a;
import com.google.android.youtube.player.internal.C0657h.C1211a;
import java.util.List;

/* renamed from: com.google.android.youtube.player.internal.s */
public final class C1222s implements YouTubePlayer {
    private C1199b f3697a;
    private C0653d f3698b;

    /* renamed from: com.google.android.youtube.player.internal.s.1 */
    class C13271 extends C1205a {
        final /* synthetic */ OnFullscreenListener f4249a;
        final /* synthetic */ C1222s f4250b;

        C13271(C1222s c1222s, OnFullscreenListener onFullscreenListener) {
            this.f4250b = c1222s;
            this.f4249a = onFullscreenListener;
        }

        public final void m5130a(boolean z) {
            this.f4249a.onFullscreen(z);
        }
    }

    /* renamed from: com.google.android.youtube.player.internal.s.2 */
    class C13282 extends C1211a {
        final /* synthetic */ PlaylistEventListener f4251a;
        final /* synthetic */ C1222s f4252b;

        C13282(C1222s c1222s, PlaylistEventListener playlistEventListener) {
            this.f4252b = c1222s;
            this.f4251a = playlistEventListener;
        }

        public final void m5131a() {
            this.f4251a.onPrevious();
        }

        public final void m5132b() {
            this.f4251a.onNext();
        }

        public final void m5133c() {
            this.f4251a.onPlaylistEnded();
        }
    }

    /* renamed from: com.google.android.youtube.player.internal.s.3 */
    class C13293 extends C1209a {
        final /* synthetic */ PlayerStateChangeListener f4253a;
        final /* synthetic */ C1222s f4254b;

        C13293(C1222s c1222s, PlayerStateChangeListener playerStateChangeListener) {
            this.f4254b = c1222s;
            this.f4253a = playerStateChangeListener;
        }

        public final void m5134a() {
            this.f4253a.onLoading();
        }

        public final void m5135a(String str) {
            this.f4253a.onLoaded(str);
        }

        public final void m5136b() {
            this.f4253a.onAdStarted();
        }

        public final void m5137b(String str) {
            ErrorReason valueOf;
            try {
                valueOf = ErrorReason.valueOf(str);
            } catch (IllegalArgumentException e) {
                valueOf = ErrorReason.UNKNOWN;
            } catch (NullPointerException e2) {
                valueOf = ErrorReason.UNKNOWN;
            }
            this.f4253a.onError(valueOf);
        }

        public final void m5138c() {
            this.f4253a.onVideoStarted();
        }

        public final void m5139d() {
            this.f4253a.onVideoEnded();
        }
    }

    /* renamed from: com.google.android.youtube.player.internal.s.4 */
    class C13304 extends C1207a {
        final /* synthetic */ PlaybackEventListener f4255a;
        final /* synthetic */ C1222s f4256b;

        C13304(C1222s c1222s, PlaybackEventListener playbackEventListener) {
            this.f4256b = c1222s;
            this.f4255a = playbackEventListener;
        }

        public final void m5140a() {
            this.f4255a.onPlaying();
        }

        public final void m5141a(int i) {
            this.f4255a.onSeekTo(i);
        }

        public final void m5142a(boolean z) {
            this.f4255a.onBuffering(z);
        }

        public final void m5143b() {
            this.f4255a.onPaused();
        }

        public final void m5144c() {
            this.f4255a.onStopped();
        }
    }

    public C1222s(C1199b c1199b, C0653d c0653d) {
        this.f3697a = (C1199b) ab.m880a((Object) c1199b, (Object) "connectionClient cannot be null");
        this.f3698b = (C0653d) ab.m880a((Object) c0653d, (Object) "embeddedPlayer cannot be null");
    }

    public final View m4271a() {
        try {
            return (View) C1331v.m5146a(this.f3698b.m926s());
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void m4272a(Configuration configuration) {
        try {
            this.f3698b.m886a(configuration);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void m4273a(boolean z) {
        try {
            this.f3698b.m895a(z);
            this.f3697a.m4172a(z);
            this.f3697a.m963d();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final boolean m4274a(int i, KeyEvent keyEvent) {
        try {
            return this.f3698b.m896a(i, keyEvent);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final boolean m4275a(Bundle bundle) {
        try {
            return this.f3698b.m897a(bundle);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void addFullscreenControlFlag(int i) {
        try {
            this.f3698b.m908d(i);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void m4276b() {
        try {
            this.f3698b.m920m();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void m4277b(boolean z) {
        try {
            this.f3698b.m911e(z);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final boolean m4278b(int i, KeyEvent keyEvent) {
        try {
            return this.f3698b.m904b(i, keyEvent);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void m4279c() {
        try {
            this.f3698b.m921n();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void cuePlaylist(String str) {
        cuePlaylist(str, 0, 0);
    }

    public final void cuePlaylist(String str, int i, int i2) {
        try {
            this.f3698b.m893a(str, i, i2);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void cueVideo(String str) {
        cueVideo(str, 0);
    }

    public final void cueVideo(String str, int i) {
        try {
            this.f3698b.m892a(str, i);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void cueVideos(List<String> list) {
        cueVideos(list, 0, 0);
    }

    public final void cueVideos(List<String> list, int i, int i2) {
        try {
            this.f3698b.m894a((List) list, i, i2);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void m4280d() {
        try {
            this.f3698b.m922o();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void m4281e() {
        try {
            this.f3698b.m923p();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void m4282f() {
        try {
            this.f3698b.m924q();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void m4283g() {
        try {
            this.f3698b.m919l();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final int getCurrentTimeMillis() {
        try {
            return this.f3698b.m915h();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final int getDurationMillis() {
        try {
            return this.f3698b.m916i();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final int getFullscreenControlFlags() {
        try {
            return this.f3698b.m917j();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final Bundle m4284h() {
        try {
            return this.f3698b.m925r();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final boolean hasNext() {
        try {
            return this.f3698b.m910d();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final boolean hasPrevious() {
        try {
            return this.f3698b.m912e();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final boolean isPlaying() {
        try {
            return this.f3698b.m907c();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void loadPlaylist(String str) {
        loadPlaylist(str, 0, 0);
    }

    public final void loadPlaylist(String str, int i, int i2) {
        try {
            this.f3698b.m901b(str, i, i2);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void loadVideo(String str) {
        loadVideo(str, 0);
    }

    public final void loadVideo(String str, int i) {
        try {
            this.f3698b.m900b(str, i);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void loadVideos(List<String> list) {
        loadVideos(list, 0, 0);
    }

    public final void loadVideos(List<String> list, int i, int i2) {
        try {
            this.f3698b.m902b((List) list, i, i2);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void next() {
        try {
            this.f3698b.m913f();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void pause() {
        try {
            this.f3698b.m898b();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void play() {
        try {
            this.f3698b.m884a();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void previous() {
        try {
            this.f3698b.m914g();
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void release() {
        m4273a(true);
    }

    public final void seekRelativeMillis(int i) {
        try {
            this.f3698b.m899b(i);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void seekToMillis(int i) {
        try {
            this.f3698b.m885a(i);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void setFullscreen(boolean z) {
        try {
            this.f3698b.m903b(z);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void setFullscreenControlFlags(int i) {
        try {
            this.f3698b.m905c(i);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void setManageAudioFocus(boolean z) {
        try {
            this.f3698b.m909d(z);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void setOnFullscreenListener(OnFullscreenListener onFullscreenListener) {
        try {
            this.f3698b.m887a(new C13271(this, onFullscreenListener));
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void setPlaybackEventListener(PlaybackEventListener playbackEventListener) {
        try {
            this.f3698b.m888a(new C13304(this, playbackEventListener));
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void setPlayerStateChangeListener(PlayerStateChangeListener playerStateChangeListener) {
        try {
            this.f3698b.m889a(new C13293(this, playerStateChangeListener));
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void setPlayerStyle(PlayerStyle playerStyle) {
        try {
            this.f3698b.m891a(playerStyle.name());
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void setPlaylistEventListener(PlaylistEventListener playlistEventListener) {
        try {
            this.f3698b.m890a(new C13282(this, playlistEventListener));
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }

    public final void setShowFullscreenButton(boolean z) {
        try {
            this.f3698b.m906c(z);
        } catch (RemoteException e) {
            throw new C0666q(e);
        }
    }
}
