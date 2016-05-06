package com.inmobi.ads;

import android.content.ContentValues;

/* renamed from: com.inmobi.ads.a */
class Ad {
    private static final String f1046a;
    private String f1047b;
    private String f1048c;
    private String f1049d;
    private long f1050e;
    private long f1051f;
    private String f1052g;

    static {
        f1046a = Ad.class.getSimpleName();
    }

    public Ad(AdNetworkResponse adNetworkResponse, String str, String str2) {
        this.f1047b = adNetworkResponse.m1200b().m4424b();
        this.f1048c = adNetworkResponse.m1200b().m4427c();
        this.f1049d = str;
        this.f1050e = adNetworkResponse.m1200b().m4431e();
        this.f1051f = System.currentTimeMillis();
        this.f1052g = str2;
    }

    public Ad(ContentValues contentValues) {
        this.f1047b = contentValues.getAsString("ad_type");
        this.f1048c = contentValues.getAsString("ad_size");
        this.f1049d = contentValues.getAsString("ad_content");
        this.f1050e = contentValues.getAsLong("placement_id").longValue();
        this.f1051f = contentValues.getAsLong("placement_id").longValue();
        this.f1052g = contentValues.getAsString("imp_id");
    }

    public ContentValues m1129a() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ad_type", this.f1047b);
        contentValues.put("ad_size", this.f1048c);
        contentValues.put("ad_content", this.f1049d);
        contentValues.put("placement_id", Long.valueOf(this.f1050e));
        contentValues.put("insertion_ts", Long.valueOf(this.f1051f));
        contentValues.put("imp_id", this.f1052g);
        return contentValues;
    }

    public String m1130b() {
        return this.f1049d;
    }

    public String m1131c() {
        return this.f1052g;
    }
}
