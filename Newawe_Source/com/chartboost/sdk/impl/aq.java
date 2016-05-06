package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.Newawe.storage.DatabaseOpenHelper;
import com.chartboost.sdk.Libraries.C0323e.C0321a;
import com.chartboost.sdk.Libraries.CBUtility;
import org.apache.commons.lang.StringUtils;

public class aq extends ap {
    private ImageView f3484a;

    public aq(aw awVar, Context context) {
        super(context);
        this.f3484a = new ImageView(context);
        addView(this.f3484a, new LayoutParams(-1, -1));
    }

    public void m3885a(C0321a c0321a, int i) {
        C0321a a = c0321a.m127a("assets").m127a(CBUtility.m93c().m165a() ? "portrait" : "landscape");
        if (a.m134c()) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            String str = StringUtils.EMPTY;
            if (!(a.m138e("checksum") == null || a.m138e("checksum").isEmpty())) {
                str = a.m138e("checksum");
            }
            bc.m652a().m658a(a.m138e(DatabaseOpenHelper.HISTORY_ROW_URL), str, null, this.f3484a, bundle);
        }
    }

    public int m3884a() {
        return CBUtility.m86a(110, getContext());
    }
}
