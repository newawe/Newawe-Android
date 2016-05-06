package com.Newawe.controllers;

import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.Newawe.C0186R;
import com.Newawe.MainNavigationActivity;
import com.Newawe.utils.ImageReader;
import java.io.IOException;

public class SplashScreenController {
    private final int SPLASH_SCREEN_TIMEOUT;
    private MainNavigationActivity _activity;
    private Handler _handler;
    private ViewGroup _splashScreenView;
    private Runnable hideSplashScreenViewRunnable;
    private Runnable showSplashScreenViewRunnable;

    /* renamed from: com.Newawe.controllers.SplashScreenController.1 */
    class C02181 implements Runnable {
        C02181() {
        }

        public void run() {
            SplashScreenController.this._activity.showSplashScreen();
        }
    }

    /* renamed from: com.Newawe.controllers.SplashScreenController.2 */
    class C02192 implements Runnable {
        C02192() {
        }

        public void run() {
            if (SplashScreenController.this._activity.getFullScreenBannerController().isOnScreen()) {
                SplashScreenController.this._splashScreenView.setVisibility(8);
            } else {
                SplashScreenController.this._activity.showContentView();
            }
        }
    }

    /* renamed from: com.Newawe.controllers.SplashScreenController.3 */
    class C02203 implements Runnable {
        C02203() {
        }

        public void run() {
            SplashScreenController.this._activity.runOnUiThread(SplashScreenController.this.hideSplashScreenViewRunnable);
        }
    }

    public SplashScreenController(ViewGroup splashScreenView, MainNavigationActivity activity) {
        this.SPLASH_SCREEN_TIMEOUT = 2000;
        this._handler = new Handler();
        this.showSplashScreenViewRunnable = new C02181();
        this.hideSplashScreenViewRunnable = new C02192();
        this._splashScreenView = splashScreenView;
        this._activity = activity;
    }

    public void showSplashScreen(String imagePath) {
        try {
            ((ImageView) this._splashScreenView.findViewById(C0186R.id.splashScreenImage)).setImageBitmap(ImageReader.decodeFile(this._activity.getAssets().open(imagePath), this._activity.getWindowManager().getDefaultDisplay().getWidth()));
            this._activity.runOnUiThread(this.showSplashScreenViewRunnable);
            this._handler.postDelayed(new C02203(), 2000);
        } catch (IOException e) {
            e.printStackTrace();
            this._activity.showContentView();
        }
    }
}
