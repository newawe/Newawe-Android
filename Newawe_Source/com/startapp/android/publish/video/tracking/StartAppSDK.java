package com.startapp.android.publish.video.tracking;

/* renamed from: com.startapp.android.publish.video.tracking.e */
public class StartAppSDK extends StartAppSDK {
    private static final long serialVersionUID = 1;
    private int pauseNum;
    private StartAppSDK pauseOrigin;

    /* renamed from: com.startapp.android.publish.video.tracking.e.a */
    public enum StartAppSDK {
        INAPP,
        EXTERNAL
    }

    public StartAppSDK(String str, int i, int i2, int i3, StartAppSDK startAppSDK) {
        super(str, i, i2);
        this.pauseNum = i3;
        this.pauseOrigin = startAppSDK;
    }

    public int a_() {
        return this.pauseNum;
    }

    public StartAppSDK m5393e() {
        return this.pauseOrigin;
    }

    public String m5392c() {
        return m5011c(m5391h() + m5390g());
    }

    private String m5390g() {
        return "&pn=" + a_();
    }

    private String m5391h() {
        return "&po=" + m5393e().toString();
    }
}
