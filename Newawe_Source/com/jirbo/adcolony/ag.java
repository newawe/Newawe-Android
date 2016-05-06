package com.jirbo.adcolony;

import com.jirbo.adcolony.ADCData.C1236c;
import com.jirbo.adcolony.ADCData.C1240g;
import java.util.ArrayList;

class ag {
    C0762d f2091a;
    boolean f2092b;
    ArrayList<af> f2093c;

    ag(C0762d c0762d) {
        this.f2092b = false;
        this.f2093c = new ArrayList();
        this.f2091a = c0762d;
    }

    void m2226a() {
        int i = 0;
        C1236c c = C0776k.m2336c(new C0771f("zone_state.txt"));
        if (c != null) {
            this.f2093c.clear();
            for (int i2 = 0; i2 < c.m4628i(); i2++) {
                C1240g b = c.m4619b(i2);
                af afVar = new af();
                if (afVar.m2223a(b)) {
                    this.f2093c.add(afVar);
                }
            }
        }
        String[] strArr = this.f2091a.f2141a.f2124l;
        int length = strArr.length;
        while (i < length) {
            m2225a(strArr[i]);
            i++;
        }
    }

    void m2227b() {
        int i = 0;
        C0777l.f2239a.m2353b((Object) "Saving zone state...");
        this.f2092b = false;
        C1236c c1236c = new C1236c();
        String[] strArr = this.f2091a.f2141a.f2124l;
        int length = strArr.length;
        while (i < length) {
            c1236c.m4612a(m2225a(strArr[i]).m2222a());
            i++;
        }
        C0776k.m2329a(new C0771f("zone_state.txt"), c1236c);
        C0777l.f2239a.m2353b((Object) "Saved zone state");
    }

    int m2228c() {
        return this.f2093c.size();
    }

    af m2224a(int i) {
        return (af) this.f2093c.get(i);
    }

    af m2225a(String str) {
        af afVar;
        int size = this.f2093c.size();
        for (int i = 0; i < size; i++) {
            afVar = (af) this.f2093c.get(i);
            if (afVar.f2087a.equals(str)) {
                return afVar;
            }
        }
        this.f2092b = true;
        afVar = new af(str);
        this.f2093c.add(afVar);
        return afVar;
    }

    void m2229d() {
        if (this.f2092b) {
            m2227b();
        }
    }
}
