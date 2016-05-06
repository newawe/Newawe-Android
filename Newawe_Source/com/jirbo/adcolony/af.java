package com.jirbo.adcolony;

import com.google.android.gcm.GCMConstants;
import com.jirbo.adcolony.ADCData.C1240g;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;

class af implements Serializable {
    String f2087a;
    int f2088b;
    int f2089c;
    int f2090d;

    af() {
        this.f2087a = StringUtils.EMPTY;
    }

    af(String str) {
        this.f2087a = StringUtils.EMPTY;
        this.f2087a = str;
    }

    boolean m2223a(C1240g c1240g) {
        if (c1240g == null) {
            return false;
        }
        this.f2087a = c1240g.m4646a("uuid", GCMConstants.EXTRA_ERROR);
        this.f2088b = c1240g.m4661g("skipped_plays");
        this.f2089c = c1240g.m4661g("play_order_index");
        return true;
    }

    C1240g m2222a() {
        C1240g c1240g = new C1240g();
        c1240g.m4655b("uuid", this.f2087a);
        c1240g.m4654b("skipped_plays", this.f2088b);
        c1240g.m4654b("play_order_index", this.f2089c);
        return c1240g;
    }
}
