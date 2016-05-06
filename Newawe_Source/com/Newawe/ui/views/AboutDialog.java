package com.Newawe.ui.views;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.Newawe.C0186R;
import com.Newawe.MainNavigationActivity;
import com.Newawe.MainNavigationActivity.ApplicationMode;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class AboutDialog extends Dialog {
    MainNavigationActivity mActivity;
    private String mAppVersion;
    private TextView mDescription;
    private ImageView mLogo;
    private String mWidgetName;

    /* renamed from: com.Newawe.ui.views.AboutDialog.1 */
    class C02901 implements OnTouchListener {
        final /* synthetic */ MainNavigationActivity val$activity;
        final /* synthetic */ String val$link;

        C02901(String str, MainNavigationActivity mainNavigationActivity) {
            this.val$link = str;
            this.val$activity = mainNavigationActivity;
        }

        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    this.val$activity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(this.val$link)));
                    break;
            }
            return true;
        }
    }

    private void init(MainNavigationActivity activity) {
        try {
            this.mActivity = activity;
            setContentView(C0186R.layout.about_dialog);
            this.mLogo = (ImageView) findViewById(C0186R.id.logo);
            this.mDescription = (TextView) findViewById(C0186R.id.description);
            this.mWidgetName = this.mActivity.getConfig().getWidgetName();
            try {
                this.mAppVersion = this.mActivity.getPackageManager().getPackageInfo(this.mActivity.getPackageName(), 0).versionName;
            } catch (NameNotFoundException e) {
            }
            this.mLogo.setOnTouchListener(new C02901(_getAppsgeyserUrl(), activity));
            setCancelable(true);
            TextView flagThisApp = (TextView) findViewById(C0186R.id.flagThisApp);
            flagThisApp.setText(Html.fromHtml(activity.getString(C0186R.string.aboutFlagText).replace("APP_ID", Integer.valueOf(activity.getConfig().getApplicationId()).toString())));
            flagThisApp.setMovementMethod(LinkMovementMethod.getInstance());
            ((TextView) findViewById(C0186R.id.poweredSign)).setText(activity.getResources().getString(C0186R.string.build) + " " + activity.getResources().getString(C0186R.string.platformVersion));
            setApplicationMode(ApplicationMode.COMMON);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public AboutDialog(Context context) {
        super(context);
    }

    public AboutDialog(MainNavigationActivity activity, int theme) {
        super(activity, theme);
        init(activity);
    }

    public void showDialog() {
        show();
    }

    public void setApplicationMode(ApplicationMode mode) {
        if (mode == ApplicationMode.COMMON) {
            String publisherName = this.mActivity.getConfig().getPublisherName();
            String link = _getAppsgeyserUrl();
            String templateDescription = this.mActivity.getResources().getString(C0186R.string.aboutDescriptionWithPubName);
            if (publisherName.length() == 0) {
                templateDescription = this.mActivity.getResources().getString(C0186R.string.aboutDescriptionWithoutPubName);
            }
            this.mDescription.setText(Html.fromHtml(templateDescription.replace("PUB_NAME", publisherName).replace("APPSGEYSER_URL", link).replace("APP_VERSION", "v." + this.mAppVersion)));
            this.mDescription.setMovementMethod(LinkMovementMethod.getInstance());
            this.mLogo.setVisibility(0);
        } else if (mode == ApplicationMode.CUSTOM) {
            this.mDescription.setText(Html.fromHtml(this.mWidgetName + "<br /> <br />v." + this.mAppVersion));
            this.mLogo.setVisibility(4);
        }
    }

    private String _getAppsgeyserUrl() {
        String affLink = this.mActivity.getConfig().getAffiliateString();
        String realLink = "http://www.appsgeyser.com";
        if (affLink.length() > 0) {
            realLink = realLink + "?" + affLink;
        }
        return realLink;
    }
}
