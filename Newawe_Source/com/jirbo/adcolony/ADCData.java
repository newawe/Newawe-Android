package com.jirbo.adcolony;

import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.apache.commons.lang.StringUtils;

public class ADCData {
    static C0721i f1795a;
    static C0721i f1796b;
    static C0721i f1797c;

    /* renamed from: com.jirbo.adcolony.ADCData.i */
    static class C0721i {
        C0721i() {
        }

        boolean m2092m() {
            return false;
        }

        boolean m2087f() {
            return false;
        }

        boolean m2090k() {
            return false;
        }

        boolean m2094p() {
            return b_() || m2084c();
        }

        boolean b_() {
            return false;
        }

        boolean m2084c() {
            return false;
        }

        boolean m2082a() {
            return false;
        }

        boolean c_() {
            return false;
        }

        boolean m2088g() {
            return true;
        }

        C1240g m2093n() {
            return null;
        }

        C1236c m2089h() {
            return null;
        }

        String m2083b() {
            return m2095q();
        }

        double m2085d() {
            return 0.0d;
        }

        int m2086e() {
            return 0;
        }

        boolean m2091l() {
            return false;
        }

        public String toString() {
            return m2095q();
        }

        String m2095q() {
            ae c1259y = new C1259y();
            m2080a(c1259y);
            return c1259y.toString();
        }

        void m2080a(ae aeVar) {
        }

        void m2081a(ae aeVar, String str) {
            if (str != null) {
                aeVar.m2212b('\"');
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    char charAt = str.charAt(i);
                    switch (charAt) {
                        case ConnectionResult.INTERNAL_ERROR /*8*/:
                            aeVar.m2210a("\\b");
                            break;
                        case ConnectionResult.SERVICE_INVALID /*9*/:
                            aeVar.m2210a("\\t");
                            break;
                        case MetaData.DEFAULT_MAX_ADS /*10*/:
                            aeVar.m2210a("\\n");
                            break;
                        case Tokens.EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                            aeVar.m2210a("\\f");
                            break;
                        case ConnectionResult.CANCELED /*13*/:
                            aeVar.m2210a("\\r");
                            break;
                        case Tokens.EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF /*34*/:
                            aeVar.m2210a("\\\"");
                            break;
                        case Tokens.EXPRTOKEN_NUMBER /*47*/:
                            aeVar.m2210a("\\/");
                            break;
                        case C0302R.styleable.Theme_alertDialogButtonGroupStyle /*92*/:
                            aeVar.m2210a("\\\\");
                            break;
                        default:
                            if (charAt >= ' ' && charAt <= '~') {
                                aeVar.m2212b(charAt);
                                break;
                            }
                            aeVar.m2210a("\\u");
                            int i2 = charAt;
                            for (int i3 = 0; i3 < 4; i3++) {
                                int i4 = (i2 >> 12) & 15;
                                i2 <<= 4;
                                if (i4 <= 9) {
                                    aeVar.m2208a((long) i4);
                                } else {
                                    aeVar.m2212b((char) ((i4 - 10) + 97));
                                }
                            }
                            break;
                    }
                }
                aeVar.m2212b('\"');
            }
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCData.a */
    static class C1234a extends C0721i {
        C1234a() {
        }

        boolean m4600a() {
            return true;
        }

        String m4601b() {
            return SchemaSymbols.ATTVAL_FALSE;
        }

        void m4599a(ae aeVar) {
            aeVar.m2210a(SchemaSymbols.ATTVAL_FALSE);
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCData.b */
    static class C1235b extends C0721i {
        int f3908a;

        C1235b(int i) {
            this.f3908a = i;
        }

        boolean m4603c() {
            return true;
        }

        double m4604d() {
            return (double) this.f3908a;
        }

        int m4605e() {
            return this.f3908a;
        }

        void m4602a(ae aeVar) {
            aeVar.m2208a((long) this.f3908a);
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCData.c */
    static class C1236c extends C0721i {
        ArrayList<C0721i> f3909a;

        C1236c() {
            this.f3909a = new ArrayList();
        }

        boolean m4624f() {
            return true;
        }

        boolean m4625g() {
            return this.f3909a.size() == 0 || (this.f3909a.size() == 1 && ((C0721i) this.f3909a.get(0)).m2088g());
        }

        C1236c m4627h() {
            return this;
        }

        void m4617a(ae aeVar) {
            int size = this.f3909a.size();
            if (size == 0) {
                aeVar.m2210a("[]");
            } else if (size == 1 && ((C0721i) this.f3909a.get(0)).m2088g()) {
                aeVar.m2210a("[");
                ((C0721i) this.f3909a.get(0)).m2080a(aeVar);
                aeVar.m2210a("]");
            } else {
                aeVar.m2216b("[");
                aeVar.f2086i += 2;
                int i = 0;
                int i2 = 1;
                while (i < size) {
                    int i3;
                    if (i2 != 0) {
                        i3 = 0;
                    } else {
                        aeVar.m2219c(',');
                        i3 = i2;
                    }
                    ((C0721i) this.f3909a.get(i)).m2080a(aeVar);
                    i++;
                    i2 = i3;
                }
                aeVar.m2221d();
                aeVar.f2086i -= 2;
                aeVar.m2210a("]");
            }
        }

        int m4628i() {
            return this.f3909a.size();
        }

        void m4629j() {
            this.f3909a.clear();
        }

        C1236c m4612a(C0721i c0721i) {
            this.f3909a.add(c0721i);
            return this;
        }

        C1236c m4613a(String str) {
            m4612a(new C1239f(str));
            return this;
        }

        C1236c m4608a(double d) {
            m4612a(new C1238e(d));
            return this;
        }

        C1236c m4609a(int i) {
            m4612a(new C1235b(i));
            return this;
        }

        C1236c m4614a(boolean z) {
            m4612a(z ? ADCData.f1795a : ADCData.f1796b);
            return this;
        }

        C1236c m4611a(C1236c c1236c) {
            for (int i = 0; i < c1236c.m4628i(); i++) {
                m4612a((C0721i) c1236c.f3909a.get(i));
            }
            return this;
        }

        C1240g m4615a(int i, C1240g c1240g) {
            C0721i c0721i = (C0721i) this.f3909a.get(i);
            if (c0721i == null || !c0721i.m2092m()) {
                return c1240g;
            }
            return c0721i.m2093n();
        }

        C1236c m4610a(int i, C1236c c1236c) {
            C0721i c0721i = (C0721i) this.f3909a.get(i);
            if (c0721i == null || !c0721i.m2087f()) {
                return c1236c;
            }
            return c0721i.m2089h();
        }

        String m4616a(int i, String str) {
            C0721i c0721i = (C0721i) this.f3909a.get(i);
            if (c0721i == null || !c0721i.m2090k()) {
                return str;
            }
            return c0721i.m2083b();
        }

        double m4606a(int i, double d) {
            C0721i c0721i = (C0721i) this.f3909a.get(i);
            if (c0721i == null || !c0721i.m2094p()) {
                return d;
            }
            return c0721i.m2085d();
        }

        int m4607a(int i, int i2) {
            C0721i c0721i = (C0721i) this.f3909a.get(i);
            if (c0721i == null || !c0721i.m2094p()) {
                return i2;
            }
            return c0721i.m2086e();
        }

        boolean m4618a(int i, boolean z) {
            C0721i c0721i = (C0721i) this.f3909a.get(i);
            if (c0721i == null) {
                return z;
            }
            if (c0721i.m2082a() || c0721i.m2090k()) {
                return c0721i.m2091l();
            }
            return z;
        }

        C1240g m4619b(int i) {
            C1240g a = m4615a(i, null);
            return a != null ? a : new C1240g();
        }

        C1236c m4620c(int i) {
            C1236c a = m4610a(i, null);
            return a != null ? a : new C1236c();
        }

        String m4621d(int i) {
            return m4616a(i, StringUtils.EMPTY);
        }

        double m4622e(int i) {
            return m4606a(i, 0.0d);
        }

        int m4623f(int i) {
            return m4607a(i, 0);
        }

        boolean m4626g(int i) {
            return m4618a(i, false);
        }

        C0721i a_() {
            return (C0721i) this.f3909a.remove(this.f3909a.size() - 1);
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCData.d */
    static class C1237d extends C0721i {
        C1237d() {
        }

        boolean c_() {
            return true;
        }

        String m4631b() {
            return "null";
        }

        void m4630a(ae aeVar) {
            aeVar.m2210a("null");
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCData.e */
    static class C1238e extends C0721i {
        double f3910a;

        C1238e(double d) {
            this.f3910a = d;
        }

        boolean b_() {
            return true;
        }

        double m4633d() {
            return this.f3910a;
        }

        int m4634e() {
            return (int) this.f3910a;
        }

        void m4632a(ae aeVar) {
            aeVar.m2207a(this.f3910a);
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCData.f */
    static class C1239f extends C0721i implements Serializable {
        String f3911a;

        C1239f(String str) {
            this.f3911a = str;
        }

        boolean m4639k() {
            return true;
        }

        String m4636b() {
            return this.f3911a;
        }

        double m4637d() {
            try {
                return Double.parseDouble(this.f3911a);
            } catch (NumberFormatException e) {
                return 0.0d;
            }
        }

        int m4638e() {
            return (int) m4637d();
        }

        boolean m4640l() {
            String toLowerCase = this.f3911a.toLowerCase();
            if (toLowerCase.equals(SchemaSymbols.ATTVAL_TRUE) || toLowerCase.equals("yes")) {
                return true;
            }
            return false;
        }

        void m4635a(ae aeVar) {
            m2081a(aeVar, this.f3911a);
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCData.g */
    static class C1240g extends C0721i implements Serializable {
        HashMap<String, C0721i> f3912a;
        ArrayList<String> f3913b;

        C1240g() {
            this.f3912a = new HashMap();
            this.f3913b = new ArrayList();
        }

        boolean m4664m() {
            return true;
        }

        boolean m4662g() {
            return this.f3912a.size() < 0 || (this.f3912a.size() == 1 && ((C0721i) this.f3912a.get(this.f3913b.get(0))).m2088g());
        }

        C1240g m4665n() {
            return this;
        }

        void m4648a(ae aeVar) {
            int size = this.f3913b.size();
            if (size == 0) {
                aeVar.m2210a("{}");
            } else if (size == 1 && ((C0721i) this.f3912a.get(this.f3913b.get(0))).m2088g()) {
                aeVar.m2210a("{");
                r0 = (String) this.f3913b.get(0);
                r1 = (C0721i) this.f3912a.get(r0);
                m2081a(aeVar, r0);
                aeVar.m2212b(':');
                r1.m2080a(aeVar);
                aeVar.m2210a("}");
            } else {
                aeVar.m2216b("{");
                aeVar.f2086i += 2;
                int i = 0;
                int i2 = 1;
                while (i < size) {
                    int i3;
                    if (i2 != 0) {
                        i3 = 0;
                    } else {
                        aeVar.m2219c(',');
                        i3 = i2;
                    }
                    r0 = (String) this.f3913b.get(i);
                    r1 = (C0721i) this.f3912a.get(r0);
                    m2081a(aeVar, r0);
                    aeVar.m2212b(':');
                    if (!r1.m2088g()) {
                        aeVar.m2221d();
                    }
                    r1.m2080a(aeVar);
                    i++;
                    i2 = i3;
                }
                aeVar.m2221d();
                aeVar.f2086i -= 2;
                aeVar.m2210a("}");
            }
        }

        int m4666o() {
            return this.f3913b.size();
        }

        String m4645a(int i) {
            return (String) this.f3913b.get(i);
        }

        boolean m4650a(String str) {
            return this.f3912a.containsKey(str);
        }

        C1240g m4644a(String str, C1240g c1240g) {
            C0721i c0721i = (C0721i) this.f3912a.get(str);
            if (c0721i == null || !c0721i.m2092m()) {
                return c1240g;
            }
            return c0721i.m2093n();
        }

        C1236c m4643a(String str, C1236c c1236c) {
            C0721i c0721i = (C0721i) this.f3912a.get(str);
            if (c0721i == null || !c0721i.m2087f()) {
                return c1236c;
            }
            return c0721i.m2089h();
        }

        ArrayList<String> m4647a(String str, ArrayList<String> arrayList) {
            C1236c c = m4657c(str);
            if (c != null) {
                arrayList = new ArrayList();
                for (int i = 0; i < c.m4628i(); i++) {
                    String d = c.m4621d(i);
                    if (d != null) {
                        arrayList.add(d);
                    }
                }
            }
            return arrayList;
        }

        String m4646a(String str, String str2) {
            C0721i c0721i = (C0721i) this.f3912a.get(str);
            if (c0721i == null || !c0721i.m2090k()) {
                return str2;
            }
            return c0721i.m2083b();
        }

        double m4641a(String str, double d) {
            C0721i c0721i = (C0721i) this.f3912a.get(str);
            if (c0721i == null || !c0721i.m2094p()) {
                return d;
            }
            return c0721i.m2085d();
        }

        int m4642a(String str, int i) {
            C0721i c0721i = (C0721i) this.f3912a.get(str);
            if (c0721i == null || !c0721i.m2094p()) {
                return i;
            }
            return c0721i.m2086e();
        }

        boolean m4651a(String str, boolean z) {
            C0721i c0721i = (C0721i) this.f3912a.get(str);
            if (c0721i == null) {
                return z;
            }
            if (c0721i.m2082a() || c0721i.m2090k()) {
                return c0721i.m2091l();
            }
            return z;
        }

        C1240g m4652b(String str) {
            C1240g a = m4644a(str, null);
            return a != null ? a : new C1240g();
        }

        C1236c m4657c(String str) {
            C1236c a = m4643a(str, null);
            return a != null ? a : new C1236c();
        }

        ArrayList<String> m4658d(String str) {
            ArrayList<String> a = m4647a(str, null);
            if (a == null) {
                return new ArrayList();
            }
            return a;
        }

        String m4659e(String str) {
            return m4646a(str, StringUtils.EMPTY);
        }

        double m4660f(String str) {
            return m4641a(str, 0.0d);
        }

        int m4661g(String str) {
            return m4642a(str, 0);
        }

        boolean m4663h(String str) {
            return m4651a(str, false);
        }

        void m4649a(String str, C0721i c0721i) {
            if (!this.f3912a.containsKey(str)) {
                this.f3913b.add(str);
            }
            this.f3912a.put(str, c0721i);
        }

        void m4655b(String str, String str2) {
            m4649a(str, new C1239f(str2));
        }

        void m4653b(String str, double d) {
            m4649a(str, new C1238e(d));
        }

        void m4654b(String str, int i) {
            m4649a(str, new C1235b(i));
        }

        void m4656b(String str, boolean z) {
            m4649a(str, z ? ADCData.f1795a : ADCData.f1796b);
        }
    }

    /* renamed from: com.jirbo.adcolony.ADCData.h */
    static class C1241h extends C0721i {
        C1241h() {
        }

        boolean m4668a() {
            return true;
        }

        String m4669b() {
            return SchemaSymbols.ATTVAL_TRUE;
        }

        double m4670d() {
            return 1.0d;
        }

        int m4671e() {
            return 1;
        }

        boolean m4672l() {
            return true;
        }

        void m4667a(ae aeVar) {
            aeVar.m2210a(SchemaSymbols.ATTVAL_TRUE);
        }
    }

    static {
        f1795a = new C1241h();
        f1796b = new C1234a();
        f1797c = new C1237d();
    }

    public static void main(String[] args) {
        System.out.println("==== ADCData Test ====");
        C1240g c1240g = new C1240g();
        c1240g.m4654b("one", 1);
        c1240g.m4653b("pi", 3.14d);
        c1240g.m4655b("name", "\"Abe Pralle\"");
        c1240g.m4649a(SchemaSymbols.ATTVAL_LIST, new C1236c());
        c1240g.m4649a("subtable", new C1240g());
        c1240g.m4652b("subtable").m4654b("five", 5);
        System.out.println("LIST:" + c1240g.m4657c(SchemaSymbols.ATTVAL_LIST));
        c1240g.m4657c(SchemaSymbols.ATTVAL_LIST).m4609a(3);
        System.out.println(c1240g);
        System.out.println(c1240g.m4661g("one"));
        System.out.println(c1240g.m4660f("one"));
        System.out.println(c1240g.m4661g("pi"));
        System.out.println(c1240g.m4660f("pi"));
        System.out.println(c1240g.m4659e("name"));
        System.out.println(c1240g.m4660f("name"));
        System.out.println(c1240g.m4661g("name"));
        System.out.println(c1240g.m4657c(SchemaSymbols.ATTVAL_LIST));
        System.out.println(c1240g.m4657c("list2"));
        System.out.println(c1240g.m4657c("subtable"));
        System.out.println(c1240g.m4652b("subtable"));
        System.out.println(c1240g.m4652b("subtable2"));
        System.out.println(c1240g.m4652b(SchemaSymbols.ATTVAL_LIST));
    }
}
