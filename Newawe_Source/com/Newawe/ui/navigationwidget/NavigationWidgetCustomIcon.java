package com.Newawe.ui.navigationwidget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.Newawe.C0186R;
import java.io.IOException;
import java.io.InputStream;

public class NavigationWidgetCustomIcon {
    private final int ICON_SIZE_IN_DP;
    private TextView _badge;
    private ViewGroup _iconView;
    private ImageButton _imageButton;

    public NavigationWidgetCustomIcon(ViewGroup iconView) {
        this.ICON_SIZE_IN_DP = 38;
        this._iconView = iconView;
        this._badge = (TextView) this._iconView.findViewById(C0186R.id.badge);
        this._imageButton = (ImageButton) this._iconView.findViewById(C0186R.id.imageButton);
    }

    public void hideIcon() {
        this._iconView.setVisibility(8);
    }

    public void showIcon() {
        this._iconView.setVisibility(0);
    }

    public void showBadge() {
        this._badge.setVisibility(0);
    }

    public void hideBadge() {
        this._badge.setVisibility(8);
    }

    public void updateBadge(String text) {
        if (text.length() > 0) {
            showBadge();
            this._badge.setText(text);
            return;
        }
        hideBadge();
    }

    public void updateIcon(String iconPath) {
        Context context = this._iconView.getContext();
        try {
            InputStream is = context.getAssets().open(iconPath);
            int px = Math.round(38.0f * (context.getResources().getDisplayMetrics().xdpi / 160.0f));
            this._imageButton.setImageBitmap(Bitmap.createScaledBitmap(BitmapFactory.decodeStream(is), px, px, false));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        this._imageButton.setOnClickListener(listener);
    }
}
