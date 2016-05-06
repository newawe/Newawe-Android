package com.jirbo.adcolony;

import com.jirbo.adcolony.C0807n.C0781a;
import com.jirbo.adcolony.C0807n.ad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;

public abstract class AdColonyAd implements Serializable {
    static final int f1832a = 0;
    static final int f1833b = 1;
    static final int f1834c = 2;
    static final int f1835d = 3;
    static final int f1836e = 4;
    HashMap<String, String> f1837A;
    HashMap<String, ArrayList<String>> f1838B;
    int f1839f;
    int f1840g;
    String f1841h;
    ad f1842i;
    C0781a f1843j;
    String f1844k;
    String f1845l;
    String f1846m;
    String f1847n;
    String f1848o;
    double f1849p;
    double f1850q;
    int f1851r;
    boolean f1852s;
    boolean f1853t;
    boolean f1854u;
    boolean f1855v;
    boolean f1856w;
    AdColonyNativeAdView f1857x;
    AdColonyAdListener f1858y;
    AdColonyIAPEngagement f1859z;

    abstract void m2115a();

    abstract boolean m2116a(boolean z);

    abstract boolean m2117b();

    abstract boolean isReady();

    public AdColonyAd() {
        this.f1839f = f1832a;
        this.f1844k = StringUtils.EMPTY;
        this.f1845l = StringUtils.EMPTY;
        this.f1846m = StringUtils.EMPTY;
        this.f1847n = StringUtils.EMPTY;
        this.f1848o = StringUtils.EMPTY;
        this.f1849p = 0.0d;
        this.f1850q = 0.0d;
        this.f1859z = AdColonyIAPEngagement.NONE;
        this.f1837A = new HashMap();
        this.f1838B = new HashMap();
    }

    public boolean shown() {
        return this.f1839f == f1836e;
    }

    public boolean notShown() {
        return this.f1839f != f1836e;
    }

    public boolean canceled() {
        return this.f1839f == f1833b;
    }

    public boolean noFill() {
        return this.f1839f == f1834c;
    }

    public boolean skipped() {
        return this.f1839f == f1835d;
    }

    public boolean iapEnabled() {
        return this.f1855v;
    }

    public AdColonyIAPEngagement iapEngagementType() {
        return this.f1859z;
    }

    public String iapProductID() {
        return this.f1847n;
    }

    public int getAvailableViews() {
        if (isReady() && m2119c()) {
            return this.f1842i.m2400f();
        }
        return f1832a;
    }

    boolean m2119c() {
        return m2118b(false);
    }

    boolean m2118b(boolean z) {
        if (this.f1839f == f1836e) {
            return true;
        }
        if (!isReady() && !z) {
            return false;
        }
        if (!m2116a(true) && z) {
            return false;
        }
        this.f1842i = C0745a.f2001l.m2272g(this.f1841h);
        this.f1843j = z ? this.f1842i.m2406l() : this.f1842i.m2405k();
        if (this.f1843j == null) {
            return false;
        }
        this.f1837A.put("replay", this.f1843j.f2280x.f2335a);
        this.f1837A.put("card_shown", this.f1843j.f2280x.f2336b);
        this.f1837A.put("html5_interaction", this.f1843j.f2280x.f2337c);
        this.f1837A.put("cancel", this.f1843j.f2280x.f2338d);
        this.f1837A.put("download", this.f1843j.f2280x.f2339e);
        this.f1837A.put(SchemaSymbols.ATTVAL_SKIP, this.f1843j.f2280x.f2340f);
        this.f1837A.put("info", this.f1843j.f2280x.f2341g);
        this.f1837A.put("custom_event", this.f1843j.f2280x.f2342h);
        this.f1837A.put("midpoint", this.f1843j.f2280x.f2343i);
        this.f1837A.put("card_dissolved", this.f1843j.f2280x.f2344j);
        this.f1837A.put("start", this.f1843j.f2280x.f2345k);
        this.f1837A.put("third_quartile", this.f1843j.f2280x.f2346l);
        this.f1837A.put("complete", this.f1843j.f2280x.f2347m);
        this.f1837A.put("continue", this.f1843j.f2280x.f2348n);
        this.f1837A.put("in_video_engagement", this.f1843j.f2280x.f2349o);
        this.f1837A.put("reward_v4vc", this.f1843j.f2280x.f2350p);
        this.f1837A.put("v4iap", this.f1843j.f2280x.f2351q);
        this.f1837A.put("first_quartile", this.f1843j.f2280x.f2352r);
        this.f1837A.put("video_expanded", this.f1843j.f2280x.f2353s);
        this.f1837A.put("sound_mute", this.f1843j.f2280x.f2354t);
        this.f1837A.put("sound_unmute", this.f1843j.f2280x.f2355u);
        this.f1837A.put("video_paused", this.f1843j.f2280x.f2356v);
        this.f1837A.put("video_resumed", this.f1843j.f2280x.f2357w);
        this.f1837A.put("native_start", this.f1843j.f2280x.f2358x);
        this.f1837A.put("native_first_quartile", this.f1843j.f2280x.f2359y);
        this.f1837A.put("native_midpoint", this.f1843j.f2280x.f2360z);
        this.f1837A.put("native_third_quartile", this.f1843j.f2280x.f2331A);
        this.f1837A.put("native_complete", this.f1843j.f2280x.f2332B);
        this.f1837A.put("native_overlay_click", this.f1843j.f2280x.f2333C);
        this.f1838B.put("replay", this.f1843j.f2277u.f2531a);
        this.f1838B.put("card_shown", this.f1843j.f2277u.f2532b);
        this.f1838B.put("html5_interaction", this.f1843j.f2277u.f2533c);
        this.f1838B.put("cancel", this.f1843j.f2277u.f2534d);
        this.f1838B.put("download", this.f1843j.f2277u.f2535e);
        this.f1838B.put(SchemaSymbols.ATTVAL_SKIP, this.f1843j.f2277u.f2536f);
        this.f1838B.put("midpoint", this.f1843j.f2277u.f2538h);
        this.f1838B.put("card_dissolved", this.f1843j.f2277u.f2539i);
        this.f1838B.put("start", this.f1843j.f2277u.f2540j);
        this.f1838B.put("third_quartile", this.f1843j.f2277u.f2541k);
        this.f1838B.put("complete", this.f1843j.f2277u.f2542l);
        this.f1838B.put("continue", this.f1843j.f2277u.f2543m);
        this.f1838B.put("in_video_engagement", this.f1843j.f2277u.f2544n);
        this.f1838B.put("reward_v4vc", this.f1843j.f2277u.f2545o);
        this.f1838B.put("v4iap", this.f1843j.f2277u.f2547q);
        this.f1838B.put("first_quartile", this.f1843j.f2277u.f2546p);
        this.f1838B.put("video_expanded", this.f1843j.f2277u.f2548r);
        this.f1838B.put("sound_mute", this.f1843j.f2277u.f2549s);
        this.f1838B.put("sound_unmute", this.f1843j.f2277u.f2550t);
        this.f1838B.put("video_paused", this.f1843j.f2277u.f2551u);
        this.f1838B.put("video_resumed", this.f1843j.f2277u.f2552v);
        this.f1838B.put("native_start", this.f1843j.f2277u.f2553w);
        this.f1838B.put("native_first_quartile", this.f1843j.f2277u.f2554x);
        this.f1838B.put("native_midpoint", this.f1843j.f2277u.f2555y);
        this.f1838B.put("native_third_quartile", this.f1843j.f2277u.f2556z);
        this.f1838B.put("native_complete", this.f1843j.f2277u.f2528A);
        this.f1838B.put("native_overlay_click", this.f1843j.f2277u.f2529B);
        return true;
    }

    public String getZoneID() {
        if (this.f1841h == null) {
            return StringUtils.EMPTY;
        }
        return this.f1841h;
    }
}
