package com.inmobi.commons.core.configs;

import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.inmobi.commons.core.configs.f */
public class PkConfig extends Config {
    private String f3841a;
    private String f3842b;
    private String f3843c;
    private String f3844d;

    public PkConfig() {
        this.f3841a = "010001";
        this.f3842b = "E72409364B865B757E1D6B8DB73011BBB1D20C1A9F931ADD3C4C09E2794CE102F8AA7F2D50EB88F9880A576E6C7B0E95712CAE9416F7BACB798564627846E93B";
        this.f3843c = "rsa";
        this.f3844d = SchemaSymbols.ATTVAL_TRUE_1;
    }

    public String m4509a() {
        return "pk";
    }

    public void m4510a(JSONObject jSONObject) throws JSONException {
        super.m1346a(jSONObject);
        this.f3841a = jSONObject.getString("e");
        this.f3842b = jSONObject.getString("m");
        this.f3843c = jSONObject.getString("alg");
        this.f3844d = jSONObject.getString("ver");
    }

    public JSONObject m4511b() throws JSONException {
        JSONObject b = super.m1347b();
        b.put("e", this.f3841a);
        b.put("m", this.f3842b);
        b.put("alg", this.f3843c);
        b.put("ver", this.f3844d);
        return b;
    }

    public boolean m4512c() {
        if (this.f3841a.trim().length() == 0 || this.f3842b.trim().length() == 0 || this.f3843c.trim().length() == 0 || this.f3844d.trim().length() == 0) {
            return false;
        }
        return true;
    }

    public Config m4513d() {
        return new PkConfig();
    }

    public String m4514e() {
        return this.f3841a;
    }

    public String m4515f() {
        return this.f3842b;
    }

    public String m4516g() {
        return this.f3844d;
    }
}
