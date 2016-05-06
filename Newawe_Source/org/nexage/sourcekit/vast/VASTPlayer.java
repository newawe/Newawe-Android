package org.nexage.sourcekit.vast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import org.nexage.sourcekit.util.DefaultMediaPicker;
import org.nexage.sourcekit.util.NetworkTools;
import org.nexage.sourcekit.util.VASTLog;
import org.nexage.sourcekit.vast.activity.VASTActivity;
import org.nexage.sourcekit.vast.model.VASTModel;
import org.nexage.sourcekit.vast.processor.VASTProcessor;

public class VASTPlayer {
    public static final int ERROR_EXCEEDED_WRAPPER_LIMIT = 6;
    public static final int ERROR_NONE = 0;
    public static final int ERROR_NO_NETWORK = 1;
    public static final int ERROR_POST_VALIDATION = 5;
    public static final int ERROR_SCHEMA_VALIDATION = 4;
    public static final int ERROR_VIDEO_PLAYBACK = 7;
    public static final int ERROR_XML_OPEN_OR_READ = 2;
    public static final int ERROR_XML_PARSE = 3;
    private static final String TAG = "VASTPlayer";
    public static final String VERSION = "1.3";
    public static VASTPlayerListener listener;
    private Context context;
    private VASTModel vastModel;

    /* renamed from: org.nexage.sourcekit.vast.VASTPlayer.1 */
    class C09351 implements Runnable {
        final /* synthetic */ String val$urlString;

        C09351(String str) {
            this.val$urlString = str;
        }

        public void run() {
            Exception e;
            Throwable th;
            BufferedReader bufferedReader = null;
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(new URL(this.val$urlString).openStream()));
                try {
                    StringBuffer sb = new StringBuffer();
                    while (true) {
                        String line = in.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line).append(System.getProperty("line.separator"));
                    }
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e2) {
                        }
                    }
                    VASTPlayer.this.loadVideoWithData(sb.toString());
                    bufferedReader = in;
                } catch (Exception e3) {
                    e = e3;
                    bufferedReader = in;
                    try {
                        VASTPlayer.this.sendError(VASTPlayer.ERROR_XML_OPEN_OR_READ);
                        VASTLog.m3660e(VASTPlayer.TAG, e.getMessage(), e);
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e4) {
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e5) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    bufferedReader = in;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
                VASTPlayer.this.sendError(VASTPlayer.ERROR_XML_OPEN_OR_READ);
                VASTLog.m3660e(VASTPlayer.TAG, e.getMessage(), e);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            }
        }
    }

    /* renamed from: org.nexage.sourcekit.vast.VASTPlayer.2 */
    class C09362 implements Runnable {
        final /* synthetic */ String val$xmlData;

        C09362(String str) {
            this.val$xmlData = str;
        }

        public void run() {
            VASTProcessor processor = new VASTProcessor(new DefaultMediaPicker(VASTPlayer.this.context));
            int error = processor.process(this.val$xmlData);
            if (error == 0) {
                VASTPlayer.this.vastModel = processor.getModel();
                VASTPlayer.this.sendReady();
                return;
            }
            VASTPlayer.this.sendError(error);
        }
    }

    /* renamed from: org.nexage.sourcekit.vast.VASTPlayer.3 */
    class C09373 implements Runnable {
        C09373() {
        }

        public void run() {
            VASTPlayer.listener.vastReady();
        }
    }

    /* renamed from: org.nexage.sourcekit.vast.VASTPlayer.4 */
    class C09384 implements Runnable {
        final /* synthetic */ int val$error;

        C09384(int i) {
            this.val$error = i;
        }

        public void run() {
            VASTPlayer.listener.vastError(this.val$error);
        }
    }

    public interface VASTPlayerListener {
        void vastClick();

        void vastComplete();

        void vastDismiss();

        void vastError(int i);

        void vastReady();
    }

    public VASTPlayer(Context context, VASTPlayerListener listener) {
        this.context = context;
        listener = listener;
    }

    public void loadVideoWithUrl(String urlString) {
        VASTLog.m3658d(TAG, "loadVideoWithUrl " + urlString);
        this.vastModel = null;
        if (NetworkTools.connectedToInternet(this.context)) {
            new Thread(new C09351(urlString)).start();
        } else {
            sendError(ERROR_NO_NETWORK);
        }
    }

    public void loadVideoWithData(String xmlData) {
        VASTLog.m3662v(TAG, "loadVideoWithData\n" + xmlData);
        this.vastModel = null;
        if (NetworkTools.connectedToInternet(this.context)) {
            new Thread(new C09362(xmlData)).start();
        } else {
            sendError(ERROR_NO_NETWORK);
        }
    }

    public void play() {
        VASTLog.m3658d(TAG, "play");
        if (this.vastModel == null) {
            VASTLog.m3663w(TAG, "vastModel is null; nothing to play");
        } else if (NetworkTools.connectedToInternet(this.context)) {
            Intent vastPlayerIntent = new Intent(this.context, VASTActivity.class);
            vastPlayerIntent.putExtra("com.nexage.android.vast.player.vastModel", this.vastModel);
            this.context.startActivity(vastPlayerIntent);
        } else {
            sendError(ERROR_NO_NETWORK);
        }
    }

    private void sendReady() {
        VASTLog.m3658d(TAG, "sendReady");
        if (listener != null) {
            ((Activity) this.context).runOnUiThread(new C09373());
        }
    }

    private void sendError(int error) {
        VASTLog.m3658d(TAG, "sendError");
        if (listener != null) {
            ((Activity) this.context).runOnUiThread(new C09384(error));
        }
    }
}
