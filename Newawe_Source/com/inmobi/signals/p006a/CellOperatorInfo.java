package com.inmobi.signals.p006a;

/* renamed from: com.inmobi.signals.a.a */
public class CellOperatorInfo {
    private int f1688a;
    private int f1689b;
    private int f1690c;
    private int f1691d;

    public CellOperatorInfo() {
        this.f1688a = -1;
        this.f1689b = -1;
        this.f1690c = -1;
        this.f1691d = -1;
    }

    public void m1846a(int i) {
        this.f1688a = i;
    }

    public void m1848b(int i) {
        this.f1689b = i;
    }

    public void m1849c(int i) {
        this.f1690c = i;
    }

    public void m1850d(int i) {
        this.f1691d = i;
    }

    public String m1845a() {
        if (this.f1688a == -1 && this.f1689b == -1) {
            return null;
        }
        return this.f1688a + "_" + this.f1689b;
    }

    public String m1847b() {
        if (this.f1690c == -1 && this.f1691d == -1) {
            return null;
        }
        return this.f1690c + "_" + this.f1691d;
    }
}
