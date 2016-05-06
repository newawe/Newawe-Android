package com.Newawe;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.VideoView;
import com.Newawe.ads.AdsLoader;
import com.Newawe.ads.AdsLoader.AdsLoadingFinishedListener;
import com.Newawe.ads.AdsLoader.HeadersReceiver;
import com.Newawe.ads.BottomBannerLayout;
import com.Newawe.ads.behavior.BehaviorAcceptor;
import com.Newawe.ads.behavior.BehaviorFactory;
import com.Newawe.ads.behavior.BehaviorVisitor;
import com.Newawe.server.AppsGeyserServerClient;
import com.Newawe.ui.views.BrowserWebView;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class VideoPlayerActivity extends Activity implements AdsLoadingFinishedListener, BehaviorAcceptor, HeadersReceiver {
    private AdsLoader adsLoader;
    private BottomBannerLayout bannerLayout;
    private VideoView mVideo;
    private ProgressBar progressBar;

    /* renamed from: com.Newawe.VideoPlayerActivity.1 */
    class C01871 implements OnErrorListener {
        C01871() {
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            VideoPlayerActivity.this.mVideo.setOnErrorListener(null);
            Intent intent = VideoPlayerActivity.this.getIntent();
            if (intent != null) {
                Intent externalIntent = new Intent("android.intent.action.VIEW", intent.getData());
                externalIntent.setFlags(268435456);
                VideoPlayerActivity.this.getApplicationContext().startActivity(externalIntent);
                VideoPlayerActivity.this.finish();
            }
            return false;
        }
    }

    /* renamed from: com.Newawe.VideoPlayerActivity.2 */
    class C01892 implements OnPreparedListener {

        /* renamed from: com.Newawe.VideoPlayerActivity.2.1 */
        class C01881 implements OnVideoSizeChangedListener {
            C01881() {
            }

            public void onVideoSizeChanged(MediaPlayer mp, int arg1, int arg2) {
                VideoPlayerActivity.this.progressBar.setVisibility(8);
                mp.start();
            }
        }

        C01892() {
        }

        public void onPrepared(MediaPlayer mp) {
            VideoPlayerActivity.this.progressBar.setVisibility(8);
            mp.start();
            mp.setOnVideoSizeChangedListener(new C01881());
        }
    }

    public VideoPlayerActivity() {
        this.mVideo = null;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Uri uri = getIntent().getData();
        setContentView(C0186R.layout.video_layout);
        this.mVideo = (VideoView) findViewById(C0186R.id.video);
        this.progressBar = (ProgressBar) findViewById(C0186R.id.video_progress_bar);
        _initSmallBanner();
        this.mVideo.setVideoURI(uri);
        this.mVideo.setOnErrorListener(new C01871());
        MediaController vidControl = new MediaController(this);
        vidControl.setAnchorView(this.mVideo);
        this.mVideo.setMediaController(vidControl);
        this.mVideo.start();
        this.progressBar.setVisibility(0);
        this.mVideo.setOnPreparedListener(new C01892());
    }

    private void _initSmallBanner() {
        this.adsLoader = new AdsLoader();
        this.bannerLayout = new BottomBannerLayout();
        this.bannerLayout.init(this, this.adsLoader);
        this.adsLoader.init(AppsGeyserServerClient.getInstance(Factory.getInstance().getMainNavigationActivity()).GetBannerUrl(), this);
        this.adsLoader.setAdsLoadingFinishedListener(this);
        this.adsLoader.setHeaderReceiver(this);
        this.adsLoader.setBottomBannerLayout(this.bannerLayout);
        this.adsLoader.reload();
    }

    protected void onPause() {
        super.onPause();
        ((BrowserWebView) findViewById(C0186R.id.banner_webkit)).pauseTimers();
    }

    protected void onResume() {
        super.onResume();
        BrowserWebView bannerBrowser = (BrowserWebView) findViewById(C0186R.id.banner_webkit);
        if (bannerBrowser != null) {
            bannerBrowser.resumeTimers();
            try {
                WebView.class.getMethod("onResume", new Class[0]).invoke(bannerBrowser, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onAdLoadFinished() {
        _applyBehaviors(new BehaviorFactory().createPostloadBehaviors(this.adsLoader.getLastResponseHeaders()));
    }

    private void _applyBehaviors(List<BehaviorVisitor> behaviors) {
        for (BehaviorVisitor visitor : behaviors) {
            acceptBehavior(visitor);
            this.adsLoader.acceptBehavior(visitor);
            this.bannerLayout.acceptBehavior(visitor);
        }
    }

    public void acceptBehavior(BehaviorVisitor visitor) {
        visitor.visit(this);
    }

    public boolean onAdHeadersReceived(Map<String, List<String>> headers) {
        _applyBehaviors(new BehaviorFactory().createPreloadBehaviors(headers));
        if (new Date().compareTo(new Date(getSharedPreferences(MainNavigationActivity.PREFS_NAME, 0).getLong(MainNavigationActivity.ADS_SLEEP_PARAM, 0))) < 0) {
            return false;
        }
        return true;
    }
}
