package com.Newawe.media;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.Newawe.utils.UrlConverter;
import java.io.IOException;

public class WebViewJsAudioPlayer {
    public static String JS_INTERFACE_NAME;
    private String _onLoadListener;
    private String _onTrackFinishedListener;
    private WebView _parentWebView;
    private MediaPlayer _player;

    /* renamed from: com.Newawe.media.WebViewJsAudioPlayer.1 */
    class C02411 implements OnPreparedListener {
        C02411() {
        }

        public void onPrepared(MediaPlayer mp) {
            if (!(WebViewJsAudioPlayer.this._onLoadListener == null || WebViewJsAudioPlayer.this._onLoadListener.length() == 0)) {
                WebViewJsAudioPlayer.this._callFunction(WebViewJsAudioPlayer.this._onLoadListener, null);
            }
            mp.start();
        }
    }

    /* renamed from: com.Newawe.media.WebViewJsAudioPlayer.2 */
    class C02422 implements OnCompletionListener {
        C02422() {
        }

        public void onCompletion(MediaPlayer mp) {
            if (mp.isPlaying() && WebViewJsAudioPlayer.this._onTrackFinishedListener != null && WebViewJsAudioPlayer.this._onTrackFinishedListener.length() != 0) {
                WebViewJsAudioPlayer.this._callFunction(WebViewJsAudioPlayer.this._onTrackFinishedListener, null);
            }
        }
    }

    /* renamed from: com.Newawe.media.WebViewJsAudioPlayer.3 */
    class C02433 implements OnErrorListener {
        C02433() {
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            WebViewJsAudioPlayer.this._player.reset();
            return false;
        }
    }

    /* renamed from: com.Newawe.media.WebViewJsAudioPlayer.4 */
    class C02444 implements Runnable {
        final /* synthetic */ String val$script;

        C02444(String str) {
            this.val$script = str;
        }

        public void run() {
            WebViewJsAudioPlayer.this._parentWebView.loadUrl(this.val$script);
        }
    }

    static {
        JS_INTERFACE_NAME = "AudioPlayer";
    }

    public WebViewJsAudioPlayer(WebView parentWebView) {
        this._parentWebView = parentWebView;
        this._player = new MediaPlayer();
    }

    @JavascriptInterface
    public void play(String url) {
        String absoluteUrl = new UrlConverter(this._parentWebView).toAbsolute(url);
        stop();
        _setPlayerEvenets();
        if (absoluteUrl.contains("file://")) {
            _playLocal(absoluteUrl);
        } else {
            _playInternet(absoluteUrl);
        }
    }

    @JavascriptInterface
    private void _playInternet(String url) {
        try {
            this._player.setDataSource(this._parentWebView.getContext(), Uri.parse(url));
            this._player.setAudioStreamType(3);
            this._player.prepareAsync();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e2) {
            e2.printStackTrace();
        } catch (IllegalStateException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    @JavascriptInterface
    private void _playLocal(String url) {
        UrlConverter converter = new UrlConverter(this._parentWebView);
        if (url.contains("file://")) {
            try {
                AssetFileDescriptor fileDescriptor = converter.toFileDescriptor(url);
                this._player.setDataSource(fileDescriptor.getFileDescriptor(), fileDescriptor.getStartOffset(), fileDescriptor.getLength());
                this._player.setAudioStreamType(3);
                this._player.prepareAsync();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e2) {
                e2.printStackTrace();
            } catch (IllegalStateException e3) {
                e3.printStackTrace();
            } catch (IOException e4) {
                e4.printStackTrace();
            }
        }
    }

    @JavascriptInterface
    private void _setPlayerEvenets() {
        this._player.setOnPreparedListener(new C02411());
        this._player.setOnCompletionListener(new C02422());
        this._player.setOnErrorListener(new C02433());
    }

    @JavascriptInterface
    public void setOnLoadListener(String functionName) {
        this._onLoadListener = functionName;
    }

    @JavascriptInterface
    public void setOnTrackFinishedListener(String functionName) {
        this._onTrackFinishedListener = functionName;
    }

    @JavascriptInterface
    private void _callFunction(String functionName, String[] args) {
        StringBuilder callString = new StringBuilder();
        callString.append("javascript:");
        callString.append(functionName);
        callString.append("(");
        if (args != null) {
            int numArgs = 0;
            for (String arg : args) {
                if (numArgs > 0) {
                    callString.append(",");
                }
                callString.append("'");
                callString.append(arg.replace("'", "'"));
                callString.append("'");
                numArgs++;
            }
        }
        callString.append(");");
        this._parentWebView.post(new C02444(callString.toString()));
    }

    @JavascriptInterface
    public int getDuration() {
        if (this._player == null || !this._player.isPlaying()) {
            return 0;
        }
        return this._player.getDuration();
    }

    @JavascriptInterface
    public int getCurrentPosition() {
        if (this._player == null || !this._player.isPlaying()) {
            return 0;
        }
        return this._player.getCurrentPosition();
    }

    @JavascriptInterface
    public void stop() {
        if (this._player != null) {
            if (this._player.isPlaying()) {
                this._player.stop();
            }
            this._player.reset();
        }
    }

    @JavascriptInterface
    public void pause() {
        if (this._player != null && this._player.isPlaying()) {
            this._player.pause();
        }
    }

    @JavascriptInterface
    public void resume() {
        if (this._player != null && !this._player.isPlaying()) {
            this._player.start();
        }
    }
}
