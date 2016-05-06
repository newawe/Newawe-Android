package org.nexage.sourcekit.vast.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.media.MediaPlayer.OnVideoSizeChangedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewCompat;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView.ScaleType;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import mf.org.w3c.dom.traversal.NodeFilter;
import org.nexage.sourcekit.util.Assets;
import org.nexage.sourcekit.util.HttpTools;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vast.VASTPlayer;
import org.nexage.sourcekit.vast.model.TRACKING_EVENTS_TYPE;
import org.nexage.sourcekit.vast.model.VASTModel;

public class VASTActivity extends Activity implements OnCompletionListener, OnErrorListener, OnPreparedListener, OnVideoSizeChangedListener, Callback {
    private static final long QUARTILE_TIMER_INTERVAL = 250;
    private static final double SKIP_INFO_PADDING_SCALE = 0.1d;
    private static final double SKIP_INFO_SCALE = 0.15d;
    private static String TAG = null;
    private static final long TOOLBAR_HIDE_DELAY = 3000;
    private static final long VIDEO_PROGRESS_TIMER_INTERVAL = 250;
    private RelativeLayout mButtonPanel;
    private ImageButton mCloseButton;
    private int mCurrentVideoPosition;
    private Handler mHandler;
    private ImageButton mInfoButton;
    private boolean mIsCompleted;
    private boolean mIsPlayBackError;
    private boolean mIsProcessedImpressions;
    private boolean mIsVideoPaused;
    private final int mMaxProgressTrackingPoints;
    private MediaPlayer mMediaPlayer;
    private RelativeLayout mOverlay;
    private Drawable mPauseDrawable;
    private Drawable mPlayDrawable;
    private ImageButton mPlayPauseButton;
    private ProgressBar mProgressBar;
    private int mQuartile;
    private RelativeLayout mRootLayout;
    private int mScreenHeight;
    private int mScreenWidth;
    private Timer mStartVideoProgressTimer;
    private SurfaceHolder mSurfaceHolder;
    private SurfaceView mSurfaceView;
    private Timer mToolBarTimer;
    private HashMap<TRACKING_EVENTS_TYPE, List<String>> mTrackingEventMap;
    private Timer mTrackingEventTimer;
    private VASTModel mVastModel;
    private int mVideoHeight;
    private LinkedList<Integer> mVideoProgressTracker;
    private int mVideoWidth;

    /* renamed from: org.nexage.sourcekit.vast.activity.VASTActivity.1 */
    class C09391 implements OnClickListener {
        C09391() {
        }

        public void onClick(View v) {
            VASTActivity.this.overlayClicked();
        }
    }

    /* renamed from: org.nexage.sourcekit.vast.activity.VASTActivity.2 */
    class C09402 implements OnClickListener {
        C09402() {
        }

        public void onClick(View v) {
            VASTActivity.this.infoClicked();
        }
    }

    /* renamed from: org.nexage.sourcekit.vast.activity.VASTActivity.3 */
    class C09413 implements OnClickListener {
        C09413() {
        }

        public void onClick(View v) {
            VASTActivity.this.closeClicked();
        }
    }

    /* renamed from: org.nexage.sourcekit.vast.activity.VASTActivity.4 */
    class C09424 implements OnClickListener {
        C09424() {
        }

        public void onClick(View v) {
            VASTActivity.this.playPauseButtonClicked();
        }
    }

    /* renamed from: org.nexage.sourcekit.vast.activity.VASTActivity.5 */
    class C09445 extends TimerTask {

        /* renamed from: org.nexage.sourcekit.vast.activity.VASTActivity.5.1 */
        class C09431 implements Runnable {
            C09431() {
            }

            public void run() {
                VASTLog.m3658d(VASTActivity.TAG, "hiding buttons");
                VASTActivity.this.mButtonPanel.setVisibility(8);
            }
        }

        C09445() {
        }

        public void run() {
            VASTActivity.this.mHandler.post(new C09431());
        }
    }

    /* renamed from: org.nexage.sourcekit.vast.activity.VASTActivity.6 */
    class C09456 extends TimerTask {
        final /* synthetic */ int val$videoDuration;

        C09456(int i) {
            this.val$videoDuration = i;
        }

        public void run() {
            try {
                int curPos = VASTActivity.this.mMediaPlayer.getCurrentPosition();
                if (curPos != 0) {
                    int percentage = (curPos * 100) / this.val$videoDuration;
                    if (percentage >= VASTActivity.this.mQuartile * 25) {
                        if (VASTActivity.this.mQuartile == 0) {
                            VASTLog.m3661i(VASTActivity.TAG, "Video at start: (" + percentage + "%)");
                            VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.start);
                        } else if (VASTActivity.this.mQuartile == 1) {
                            VASTLog.m3661i(VASTActivity.TAG, "Video at first quartile: (" + percentage + "%)");
                            VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.firstQuartile);
                        } else if (VASTActivity.this.mQuartile == 2) {
                            VASTLog.m3661i(VASTActivity.TAG, "Video at midpoint: (" + percentage + "%)");
                            VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.midpoint);
                        } else if (VASTActivity.this.mQuartile == 3) {
                            VASTLog.m3661i(VASTActivity.TAG, "Video at third quartile: (" + percentage + "%)");
                            VASTActivity.this.processEvent(TRACKING_EVENTS_TYPE.thirdQuartile);
                            VASTActivity.this.stopQuartileTimer();
                        }
                        VASTActivity.this.mQuartile = VASTActivity.this.mQuartile + 1;
                    }
                }
            } catch (Exception e) {
                VASTLog.m3663w(VASTActivity.TAG, "mediaPlayer.getCurrentPosition exception: " + e.getMessage());
                cancel();
            }
        }
    }

    /* renamed from: org.nexage.sourcekit.vast.activity.VASTActivity.7 */
    class C09467 extends TimerTask {
        int maxAmountInList;

        C09467() {
            this.maxAmountInList = 19;
        }

        public void run() {
            if (VASTActivity.this.mMediaPlayer != null) {
                if (VASTActivity.this.mVideoProgressTracker.size() == this.maxAmountInList) {
                    int firstPosition = ((Integer) VASTActivity.this.mVideoProgressTracker.getFirst()).intValue();
                    int lastPosition = ((Integer) VASTActivity.this.mVideoProgressTracker.getLast()).intValue();
                    if (lastPosition > firstPosition) {
                        VASTLog.m3662v(VASTActivity.TAG, "video progressing (position:" + lastPosition + ")");
                        VASTActivity.this.mVideoProgressTracker.removeFirst();
                    } else {
                        VASTLog.m3659e(VASTActivity.TAG, "detected video hang");
                        VASTActivity.this.mIsPlayBackError = true;
                        VASTActivity.this.stopVideoProgressTimer();
                        VASTActivity.this.processErrorEvent();
                        VASTActivity.this.closeClicked();
                        VASTActivity.this.finishVAST();
                    }
                }
                try {
                    VASTActivity.this.mVideoProgressTracker.addLast(Integer.valueOf(VASTActivity.this.mMediaPlayer.getCurrentPosition()));
                } catch (Exception e) {
                }
            }
        }
    }

    public VASTActivity() {
        this.mVideoProgressTracker = null;
        this.mMaxProgressTrackingPoints = 20;
        this.mVastModel = null;
        this.mIsVideoPaused = false;
        this.mIsPlayBackError = false;
        this.mIsProcessedImpressions = false;
        this.mIsCompleted = false;
        this.mQuartile = 0;
    }

    static {
        TAG = "VASTActivity";
    }

    protected void onCreate(Bundle savedInstanceState) {
        VASTLog.m3658d(TAG, "in onCreate");
        super.onCreate(savedInstanceState);
        int currentOrientation = getResources().getConfiguration().orientation;
        VASTLog.m3658d(TAG, "currentOrientation:" + currentOrientation);
        if (currentOrientation != 2) {
            VASTLog.m3658d(TAG, "Orientation is not landscape.....forcing landscape");
            setRequestedOrientation(0);
            return;
        }
        VASTLog.m3658d(TAG, "orientation is landscape");
        this.mVastModel = (VASTModel) getIntent().getSerializableExtra("com.nexage.android.vast.player.vastModel");
        if (this.mVastModel == null) {
            VASTLog.m3659e(TAG, "vastModel is null. Stopping activity.");
            finishVAST();
            return;
        }
        hideTitleStatusBars();
        this.mHandler = new Handler();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.mScreenWidth = displayMetrics.widthPixels;
        this.mScreenHeight = displayMetrics.heightPixels;
        this.mTrackingEventMap = this.mVastModel.getTrackingUrls();
        createUIComponents();
    }

    protected void onStart() {
        VASTLog.m3658d(TAG, "entered onStart --(life cycle event)");
        super.onStart();
    }

    protected void onResume() {
        VASTLog.m3658d(TAG, "entered on onResume --(life cycle event)");
        super.onResume();
    }

    protected void onStop() {
        VASTLog.m3658d(TAG, "entered on onStop --(life cycle event)");
        super.onStop();
    }

    protected void onRestart() {
        VASTLog.m3658d(TAG, "entered on onRestart --(life cycle event)");
        super.onRestart();
        createMediaPlayer();
    }

    protected void onPause() {
        VASTLog.m3658d(TAG, "entered on onPause --(life cycle event)");
        super.onPause();
        if (this.mMediaPlayer != null) {
            this.mCurrentVideoPosition = this.mMediaPlayer.getCurrentPosition();
        }
        cleanActivityUp();
    }

    protected void onDestroy() {
        VASTLog.m3658d(TAG, "entered on onDestroy --(life cycle event)");
        super.onDestroy();
    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        VASTLog.m3658d(TAG, "entered onSaveInstanceState ");
        super.onSaveInstanceState(savedInstanceState);
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        VASTLog.m3658d(TAG, "in onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
    }

    private void hideTitleStatusBars() {
        requestWindowFeature(1);
        getWindow().setFlags(NodeFilter.SHOW_DOCUMENT_FRAGMENT, NodeFilter.SHOW_DOCUMENT_FRAGMENT);
    }

    private void createUIComponents() {
        LayoutParams params = new LayoutParams(-1, -1);
        createRootLayout(params);
        createSurface(params);
        createMediaPlayer();
        createOverlay(params);
        createButtonPanel(this.mScreenWidth, this.mScreenHeight);
        int size = (int) (SKIP_INFO_SCALE * ((double) Math.min(this.mScreenWidth, this.mScreenHeight)));
        createPlayPauseButton(size);
        createCloseButton(size);
        createInfoButton(size);
        setContentView(this.mRootLayout);
        createProgressBar();
    }

    private void createProgressBar() {
        LayoutParams params = new LayoutParams(-1, -2);
        params.addRule(13);
        this.mProgressBar = new ProgressBar(this);
        this.mProgressBar.setLayoutParams(params);
        this.mRootLayout.addView(this.mProgressBar);
        this.mProgressBar.setVisibility(8);
    }

    private void showProgressBar() {
        this.mProgressBar.setVisibility(0);
    }

    private void hideProgressBar() {
        this.mProgressBar.setVisibility(8);
    }

    private void createRootLayout(LayoutParams params) {
        this.mRootLayout = new RelativeLayout(this);
        this.mRootLayout.setLayoutParams(params);
        this.mRootLayout.setPadding(0, 0, 0, 0);
        this.mRootLayout.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
    }

    private void createSurface(LayoutParams params) {
        this.mSurfaceView = new SurfaceView(this);
        this.mSurfaceView.setLayoutParams(params);
        this.mSurfaceHolder = this.mSurfaceView.getHolder();
        this.mSurfaceHolder.addCallback(this);
        this.mSurfaceHolder.setType(3);
        this.mRootLayout.addView(this.mSurfaceView);
    }

    private void createMediaPlayer() {
        this.mMediaPlayer = new MediaPlayer();
        this.mMediaPlayer.setOnCompletionListener(this);
        this.mMediaPlayer.setOnErrorListener(this);
        this.mMediaPlayer.setOnPreparedListener(this);
        this.mMediaPlayer.setOnVideoSizeChangedListener(this);
        this.mMediaPlayer.setAudioStreamType(3);
    }

    private void createOverlay(LayoutParams params) {
        this.mOverlay = new RelativeLayout(this);
        this.mOverlay.setLayoutParams(params);
        this.mOverlay.setPadding(0, 0, 0, 0);
        this.mOverlay.setBackgroundColor(0);
        this.mOverlay.setOnClickListener(new C09391());
        this.mRootLayout.addView(this.mOverlay);
    }

    private void createButtonPanel(int screenWidth, int screenHeight) {
        LayoutParams params = new LayoutParams(-1, -2);
        params.addRule(12);
        this.mButtonPanel = new RelativeLayout(this);
        this.mButtonPanel.setLayoutParams(params);
        int padding = (int) (SKIP_INFO_PADDING_SCALE * ((double) Math.min(screenWidth, screenHeight)));
        this.mButtonPanel.setPadding(padding, 0, padding, 0);
        this.mButtonPanel.setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
        this.mButtonPanel.setVisibility(8);
        this.mOverlay.addView(this.mButtonPanel);
    }

    private void createInfoButton(int size) {
        String clickThroughUrl = this.mVastModel.getVideoClicks().getClickThrough();
        if (clickThroughUrl != null && clickThroughUrl.length() > 0) {
            LayoutParams params = new LayoutParams(size, size);
            params.addRule(0, 22);
            this.mInfoButton = new ImageButton(this);
            this.mInfoButton.setImageDrawable(Assets.getDrawableFromBase64(getResources(), Assets.info));
            this.mInfoButton.setLayoutParams(params);
            this.mInfoButton.setScaleType(ScaleType.CENTER_CROP);
            this.mInfoButton.setPadding(0, 0, 0, 0);
            this.mInfoButton.setBackgroundColor(0);
            this.mInfoButton.setEnabled(true);
            this.mInfoButton.setVisibility(0);
            this.mInfoButton.setOnClickListener(new C09402());
            this.mButtonPanel.addView(this.mInfoButton);
        }
    }

    private void createCloseButton(int size) {
        LayoutParams params = new LayoutParams(size, size);
        params.addRule(11);
        this.mCloseButton = new ImageButton(this);
        this.mCloseButton.setId(22);
        this.mCloseButton.setImageDrawable(Assets.getDrawableFromBase64(getResources(), Assets.exit));
        this.mCloseButton.setLayoutParams(params);
        this.mCloseButton.setScaleType(ScaleType.CENTER_CROP);
        this.mCloseButton.setPadding(0, 0, 0, 0);
        this.mCloseButton.setBackgroundColor(0);
        this.mCloseButton.setVisibility(0);
        this.mCloseButton.setOnClickListener(new C09413());
        this.mButtonPanel.addView(this.mCloseButton);
    }

    private void createPlayPauseButton(int size) {
        LayoutParams params = new LayoutParams(size, size);
        params.addRule(9);
        this.mPauseDrawable = Assets.getDrawableFromBase64(getResources(), Assets.pause);
        this.mPlayDrawable = Assets.getDrawableFromBase64(getResources(), Assets.play);
        this.mPlayPauseButton = new ImageButton(this);
        this.mPlayPauseButton.setImageDrawable(this.mPauseDrawable);
        this.mPlayPauseButton.setLayoutParams(params);
        this.mPlayPauseButton.setScaleType(ScaleType.CENTER_CROP);
        this.mPlayPauseButton.setPadding(0, 0, 0, 0);
        this.mPlayPauseButton.setBackgroundColor(0);
        this.mPlayPauseButton.setEnabled(true);
        this.mPlayPauseButton.setVisibility(0);
        this.mPlayPauseButton.setOnClickListener(new C09424());
        this.mButtonPanel.addView(this.mPlayPauseButton);
    }

    private void infoClicked() {
        VASTLog.m3658d(TAG, "entered infoClicked:");
        activateButtons(false);
        if (this.mMediaPlayer.isPlaying()) {
            this.mMediaPlayer.pause();
            this.mCurrentVideoPosition = this.mMediaPlayer.getCurrentPosition();
        }
        processClickThroughEvent();
    }

    private void activateButtons(boolean active) {
        VASTLog.m3658d(TAG, "entered activateButtons:");
        if (active) {
            this.mButtonPanel.setVisibility(0);
        } else {
            this.mButtonPanel.setVisibility(8);
        }
    }

    private void processClickThroughEvent() {
        VASTLog.m3658d(TAG, "entered processClickThroughEvent:");
        if (VASTPlayer.listener != null) {
            VASTPlayer.listener.vastClick();
        }
        String clickThroughUrl = this.mVastModel.getVideoClicks().getClickThrough();
        VASTLog.m3658d(TAG, "clickThrough url: " + clickThroughUrl);
        fireUrls(this.mVastModel.getVideoClicks().getClickTracking());
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(clickThroughUrl));
            if (getPackageManager().resolveActivity(intent, 32) == null) {
                VASTLog.m3659e(TAG, "Clickthrough error occured, uri unresolvable");
                if (((double) this.mCurrentVideoPosition) >= ((double) this.mMediaPlayer.getCurrentPosition()) * 0.99d) {
                    this.mMediaPlayer.start();
                }
                activateButtons(true);
                return;
            }
            startActivity(intent);
        } catch (NullPointerException e) {
            VASTLog.m3660e(TAG, e.getMessage(), e);
        }
    }

    private void closeClicked() {
        VASTLog.m3658d(TAG, "entered closeClicked()");
        cleanActivityUp();
        if (!this.mIsPlayBackError) {
            processEvent(TRACKING_EVENTS_TYPE.close);
        }
        finishVAST();
        VASTLog.m3658d(TAG, "leaving closeClicked()");
    }

    private void playPauseButtonClicked() {
        VASTLog.m3658d(TAG, "entered playPauseClicked");
        if (this.mMediaPlayer == null) {
            VASTLog.m3659e(TAG, "mMediaPlayer is null when playPauseButton was clicked");
            return;
        }
        boolean isPlaying = this.mMediaPlayer.isPlaying();
        VASTLog.m3658d(TAG, "isPlaying:" + isPlaying);
        if (isPlaying) {
            processPauseSteps();
        } else if (this.mIsVideoPaused) {
            processPlaySteps();
            if (!this.mIsCompleted) {
                processEvent(TRACKING_EVENTS_TYPE.resume);
            }
        } else {
            processPlaySteps();
            this.mQuartile = 0;
            startQuartileTimer();
        }
    }

    private void processPauseSteps() {
        this.mIsVideoPaused = true;
        this.mMediaPlayer.pause();
        stopVideoProgressTimer();
        stopToolBarTimer();
        this.mPlayPauseButton.setImageDrawable(this.mPlayDrawable);
        if (!this.mIsCompleted) {
            processEvent(TRACKING_EVENTS_TYPE.pause);
        }
    }

    private void processPlaySteps() {
        this.mIsVideoPaused = false;
        this.mMediaPlayer.start();
        this.mPlayPauseButton.setImageDrawable(this.mPauseDrawable);
        startToolBarTimer();
        startVideoProgressTimer();
    }

    public void onBackPressed() {
        VASTLog.m3658d(TAG, "entered onBackPressed");
        closeClicked();
    }

    public void surfaceCreated(SurfaceHolder holder) {
        VASTLog.m3658d(TAG, "surfaceCreated -- (SurfaceHolder callback)");
        try {
            if (this.mMediaPlayer == null) {
                createMediaPlayer();
            }
            showProgressBar();
            this.mMediaPlayer.setDisplay(this.mSurfaceHolder);
            String url = this.mVastModel.getPickedMediaFileURL();
            VASTLog.m3658d(TAG, "URL for media file:" + url);
            this.mMediaPlayer.setDataSource(url);
            this.mMediaPlayer.prepareAsync();
        } catch (Exception e) {
            VASTLog.m3660e(TAG, e.getMessage(), e);
        }
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int arg1, int arg2, int arg3) {
        VASTLog.m3658d(TAG, "entered surfaceChanged -- (SurfaceHolder callback)");
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        VASTLog.m3658d(TAG, "entered surfaceDestroyed -- (SurfaceHolder callback)");
        cleanUpMediaPlayer();
    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        VASTLog.m3658d(TAG, "entered onVideoSizeChanged -- (MediaPlayer callback)");
        this.mVideoWidth = width;
        this.mVideoHeight = height;
        VASTLog.m3658d(TAG, "video size: " + this.mVideoWidth + "x" + this.mVideoHeight);
    }

    public void onPrepared(MediaPlayer mp) {
        VASTLog.m3658d(TAG, "entered onPrepared called --(MediaPlayer callback) ....about to play");
        calculateAspectRatio();
        this.mMediaPlayer.start();
        hideProgressBar();
        if (this.mIsVideoPaused) {
            VASTLog.m3658d(TAG, "pausing video");
            this.mMediaPlayer.pause();
        } else {
            startVideoProgressTimer();
        }
        VASTLog.m3658d(TAG, "current location in video:" + this.mCurrentVideoPosition);
        if (this.mCurrentVideoPosition > 0) {
            VASTLog.m3658d(TAG, "seeking to location:" + this.mCurrentVideoPosition);
            this.mMediaPlayer.seekTo(this.mCurrentVideoPosition);
        }
        if (!this.mIsProcessedImpressions) {
            processImpressions();
        }
        startQuartileTimer();
        startToolBarTimer();
        if (!this.mMediaPlayer.isPlaying() && !this.mIsVideoPaused) {
            this.mMediaPlayer.start();
        }
    }

    private void calculateAspectRatio() {
        VASTLog.m3658d(TAG, "entered calculateAspectRatio");
        if (this.mVideoWidth == 0 || this.mVideoHeight == 0) {
            VASTLog.m3663w(TAG, "mVideoWidth or mVideoHeight is 0, skipping calculateAspectRatio");
            return;
        }
        VASTLog.m3658d(TAG, "calculating aspect ratio");
        double widthRatio = (1.0d * ((double) this.mScreenWidth)) / ((double) this.mVideoWidth);
        double heightRatio = (1.0d * ((double) this.mScreenHeight)) / ((double) this.mVideoHeight);
        double scale = Math.min(widthRatio, heightRatio);
        int surfaceWidth = (int) (((double) this.mVideoWidth) * scale);
        int surfaceHeight = (int) (((double) this.mVideoHeight) * scale);
        LayoutParams params = new LayoutParams(surfaceWidth, surfaceHeight);
        params.addRule(13);
        this.mSurfaceView.setLayoutParams(params);
        this.mSurfaceHolder.setFixedSize(surfaceWidth, surfaceHeight);
        VASTLog.m3658d(TAG, " screen size: " + this.mScreenWidth + "x" + this.mScreenHeight);
        VASTLog.m3658d(TAG, " video size:  " + this.mVideoWidth + "x" + this.mVideoHeight);
        VASTLog.m3658d(TAG, " widthRatio:   " + widthRatio);
        VASTLog.m3658d(TAG, " heightRatio:   " + heightRatio);
        VASTLog.m3658d(TAG, "surface size: " + surfaceWidth + "x" + surfaceHeight);
    }

    private void cleanActivityUp() {
        cleanUpMediaPlayer();
        stopQuartileTimer();
        stopVideoProgressTimer();
        stopToolBarTimer();
    }

    private void cleanUpMediaPlayer() {
        VASTLog.m3658d(TAG, "entered cleanUpMediaPlayer ");
        if (this.mMediaPlayer != null) {
            if (this.mMediaPlayer.isPlaying()) {
                this.mMediaPlayer.stop();
            }
            this.mMediaPlayer.setOnCompletionListener(null);
            this.mMediaPlayer.setOnErrorListener(null);
            this.mMediaPlayer.setOnPreparedListener(null);
            this.mMediaPlayer.setOnVideoSizeChangedListener(null);
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        VASTLog.m3659e(TAG, "entered onError -- (MediaPlayer callback)");
        this.mIsPlayBackError = true;
        VASTLog.m3659e(TAG, "Shutting down Activity due to Media Player errors: WHAT:" + what + ": EXTRA:" + extra + ":");
        processErrorEvent();
        closeClicked();
        return true;
    }

    private void processErrorEvent() {
        VASTLog.m3658d(TAG, "entered processErrorEvent");
        fireUrls(this.mVastModel.getErrorUrl());
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        VASTLog.m3658d(TAG, "entered onCOMPLETION -- (MediaPlayer callback)");
        stopVideoProgressTimer();
        stopToolBarTimer();
        this.mButtonPanel.setVisibility(0);
        this.mPlayPauseButton.setImageDrawable(this.mPlayDrawable);
        if (!this.mIsPlayBackError && !this.mIsCompleted) {
            this.mIsCompleted = true;
            processEvent(TRACKING_EVENTS_TYPE.complete);
            if (VASTPlayer.listener != null) {
                VASTPlayer.listener.vastComplete();
            }
        }
    }

    private void overlayClicked() {
        startToolBarTimer();
    }

    private void processImpressions() {
        VASTLog.m3658d(TAG, "entered processImpressions");
        this.mIsProcessedImpressions = true;
        fireUrls(this.mVastModel.getImpressions());
    }

    private void fireUrls(List<String> urls) {
        VASTLog.m3658d(TAG, "entered fireUrls");
        if (urls != null) {
            for (String url : urls) {
                VASTLog.m3662v(TAG, "\tfiring url:" + url);
                HttpTools.httpGetURL(url);
            }
            return;
        }
        VASTLog.m3658d(TAG, "\turl list is null");
    }

    private void startToolBarTimer() {
        VASTLog.m3658d(TAG, "entered startToolBarTimer");
        if (this.mQuartile != 4) {
            if (this.mMediaPlayer != null && this.mMediaPlayer.isPlaying()) {
                stopToolBarTimer();
                this.mToolBarTimer = new Timer();
                this.mToolBarTimer.schedule(new C09445(), TOOLBAR_HIDE_DELAY);
                this.mButtonPanel.setVisibility(0);
            }
            if (this.mIsVideoPaused) {
                activateButtons(true);
            }
        }
    }

    private void stopToolBarTimer() {
        VASTLog.m3658d(TAG, "entered stopToolBarTimer");
        if (this.mToolBarTimer != null) {
            this.mToolBarTimer.cancel();
            this.mToolBarTimer = null;
        }
    }

    private void startQuartileTimer() {
        VASTLog.m3658d(TAG, "entered startQuartileTimer");
        stopQuartileTimer();
        if (this.mIsCompleted) {
            VASTLog.m3658d(TAG, "ending quartileTimer becuase the video has been replayed");
            return;
        }
        int videoDuration = this.mMediaPlayer.getDuration();
        this.mTrackingEventTimer = new Timer();
        this.mTrackingEventTimer.scheduleAtFixedRate(new C09456(videoDuration), 0, VIDEO_PROGRESS_TIMER_INTERVAL);
    }

    private void stopQuartileTimer() {
        if (this.mTrackingEventTimer != null) {
            this.mTrackingEventTimer.cancel();
            this.mTrackingEventTimer = null;
        }
    }

    private void startVideoProgressTimer() {
        VASTLog.m3658d(TAG, "entered startVideoProgressTimer");
        this.mStartVideoProgressTimer = new Timer();
        this.mVideoProgressTracker = new LinkedList();
        this.mStartVideoProgressTimer.schedule(new C09467(), 0, VIDEO_PROGRESS_TIMER_INTERVAL);
    }

    private void stopVideoProgressTimer() {
        VASTLog.m3658d(TAG, "entered stopVideoProgressTimer");
        if (this.mStartVideoProgressTimer != null) {
            this.mStartVideoProgressTimer.cancel();
        }
    }

    private void processEvent(TRACKING_EVENTS_TYPE eventName) {
        VASTLog.m3661i(TAG, "entered Processing Event: " + eventName);
        fireUrls((List) this.mTrackingEventMap.get(eventName));
    }

    private void finishVAST() {
        VASTPlayer.listener.vastDismiss();
        finish();
    }
}
