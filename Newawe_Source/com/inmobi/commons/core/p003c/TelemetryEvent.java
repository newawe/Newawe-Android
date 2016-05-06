package com.inmobi.commons.core.p003c;

import android.content.ContentValues;
import org.apache.commons.lang.StringUtils;

/* renamed from: com.inmobi.commons.core.c.e */
public class TelemetryEvent {
    private static final String f1184a;
    private String f1185b;
    private String f1186c;
    private long f1187d;
    private String f1188e;

    static {
        f1184a = TelemetryEvent.class.getSimpleName();
    }

    public TelemetryEvent(String str, String str2) {
        this.f1186c = str;
        this.f1185b = str2;
        this.f1188e = null;
        this.f1187d = System.currentTimeMillis();
    }

    public TelemetryEvent(String str, String str2, String str3) {
        this.f1186c = str;
        this.f1185b = str2;
        this.f1188e = str3;
        this.f1187d = System.currentTimeMillis();
    }

    public String m1323a() {
        return this.f1186c;
    }

    public String m1325b() {
        return this.f1185b;
    }

    public String m1326c() {
        return this.f1188e == null ? StringUtils.EMPTY : this.f1188e;
    }

    public long m1327d() {
        return this.f1187d;
    }

    public void m1324a(String str) {
        this.f1188e = str;
    }

    public String toString() {
        return m1325b() + "@" + m1323a() + " ";
    }

    public static TelemetryEvent m1322a(ContentValues contentValues) {
        String asString = contentValues.getAsString("eventType");
        String asString2 = contentValues.getAsString("componentType");
        String asString3 = contentValues.getAsString("payload");
        long longValue = Long.valueOf(contentValues.getAsString("ts")).longValue();
        TelemetryEvent telemetryEvent = new TelemetryEvent(asString2, asString, asString3);
        telemetryEvent.f1187d = longValue;
        return telemetryEvent;
    }

    public ContentValues m1328e() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("componentType", m1323a());
        contentValues.put("eventType", m1325b());
        contentValues.put("payload", m1326c());
        contentValues.put("ts", String.valueOf(m1327d()));
        return contentValues;
    }
}
