package com.startapp.android.publish.video.p025a;

import com.startapp.android.publish.video.tracking.VideoTrackingLink;
import com.startapp.android.publish.video.tracking.VideoTrackingLink.TrackingSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.http.HttpStatus;

/* renamed from: com.startapp.android.publish.video.a.b */
public class StartAppSDK {
    private VideoTrackingLink[] f3262a;
    private com.startapp.android.publish.video.tracking.StartAppSDK f3263b;
    private String f3264c;
    private int f3265d;
    private String f3266e;
    private StartAppSDK f3267f;

    /* renamed from: com.startapp.android.publish.video.a.b.a */
    public enum StartAppSDK {
        GENERAL(Integer.valueOf(HttpStatus.SC_BAD_REQUEST)),
        FILE_DOWNLOAD(Integer.valueOf(HttpStatus.SC_UNAUTHORIZED));
        
        Integer code;

        private StartAppSDK(Integer num) {
            this.code = num;
        }

        public String toString() {
            return this.code.toString();
        }
    }

    public StartAppSDK(VideoTrackingLink[] videoTrackingLinkArr, com.startapp.android.publish.video.tracking.StartAppSDK startAppSDK, String str, int i) {
        this.f3266e = StringUtils.EMPTY;
        this.f3262a = videoTrackingLinkArr;
        this.f3263b = startAppSDK;
        this.f3264c = str;
        this.f3265d = i;
    }

    public StartAppSDK m3600a(StartAppSDK startAppSDK) {
        this.f3267f = startAppSDK;
        return this;
    }

    public StartAppSDK m3601a(String str) {
        this.f3266e = str;
        return this;
    }

    public StartAppSDK m3599a() {
        if (m3596b()) {
            List arrayList = new ArrayList();
            for (VideoTrackingLink videoTrackingLink : this.f3262a) {
                if (videoTrackingLink.m3638b() == null) {
                    com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoEventBuilder", 5, "Ignoring tracking link - tracking url is null: tracking link = " + videoTrackingLink);
                } else if (this.f3263b.m2961a() <= 0 || videoTrackingLink.m3639c()) {
                    arrayList.add(m3593a(videoTrackingLink));
                } else {
                    com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoEventBuilder", 3, "Ignoring tracking link - external replay event: tracking link = " + videoTrackingLink);
                }
            }
            return new StartAppSDK(arrayList, this.f3266e);
        }
        com.startapp.android.publish.p022h.StartAppSDK.m2928a("VideoEventBuilder", 3, "Some fields have illegal values");
        return null;
    }

    private boolean m3596b() {
        return (this.f3262a == null || this.f3263b == null) ? false : true;
    }

    private String m3593a(VideoTrackingLink videoTrackingLink) {
        StringBuilder stringBuilder = new StringBuilder();
        com.startapp.android.publish.video.tracking.StartAppSDK b = m3594b(videoTrackingLink);
        String b2 = videoTrackingLink.m3638b();
        stringBuilder.append(m3595b(b2)).append(b.m5010c());
        if (b.m5013f()) {
            stringBuilder.append(com.startapp.android.publish.p022h.StartAppSDK.m2889a(com.startapp.android.publish.p022h.StartAppSDK.m3027b(b2)));
        }
        return stringBuilder.toString();
    }

    private com.startapp.android.publish.video.tracking.StartAppSDK m3594b(VideoTrackingLink videoTrackingLink) {
        TrackingSource e = videoTrackingLink.m3641e();
        com.startapp.android.publish.video.tracking.StartAppSDK startAppSDK = this.f3263b;
        boolean z = e != null && e == TrackingSource.STARTAPP;
        return startAppSDK.m5008b(z).m5006a(videoTrackingLink.m3639c()).m5007b(videoTrackingLink.m3640d());
    }

    private String m3595b(String str) {
        String replace = str.replace("[ASSETURI]", this.f3264c).replace("[CONTENTPLAYHEAD]", m3598d()).replace("[CACHEBUSTING]", m3597c());
        return this.f3267f != null ? replace.replace("[ERRORCODE]", this.f3267f.toString()) : replace;
    }

    private String m3597c() {
        return new Integer(10000000 + new Random().nextInt(90000000)).toString();
    }

    private String m3598d() {
        long convert = TimeUnit.SECONDS.convert((long) this.f3265d, TimeUnit.MILLISECONDS);
        long j = convert % 60;
        long j2 = convert / 3600;
        convert = (convert % 3600) / 60;
        long j3 = (long) (this.f3265d % DateUtils.MILLIS_IN_SECOND);
        return String.format("%02d:%02d:%02d.%03d", new Object[]{Long.valueOf(j2), Long.valueOf(convert), Long.valueOf(j), Long.valueOf(j3)});
    }
}
