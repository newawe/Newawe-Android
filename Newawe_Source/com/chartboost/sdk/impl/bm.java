package com.chartboost.sdk.impl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;

public class bm {
    private static final String[] f640a;
    private C0442b f641b;

    /* renamed from: com.chartboost.sdk.impl.bm.1 */
    class C04391 implements OnShowListener {
        final /* synthetic */ AlertDialog f633a;
        final /* synthetic */ int f634b;
        final /* synthetic */ ArrayList f635c;
        final /* synthetic */ int f636d;
        final /* synthetic */ bm f637e;

        /* renamed from: com.chartboost.sdk.impl.bm.1.1 */
        class C04381 implements OnClickListener {
            final /* synthetic */ int f631a;
            final /* synthetic */ C04391 f632b;

            C04381(C04391 c04391, int i) {
                this.f632b = c04391;
                this.f631a = i;
            }

            public void onClick(View v) {
                if (this.f632b.f637e.f641b != null) {
                    this.f632b.f637e.f641b.m726a(this.f632b.f637e, this.f631a);
                }
                this.f632b.f633a.dismiss();
            }
        }

        C04391(bm bmVar, AlertDialog alertDialog, int i, ArrayList arrayList, int i2) {
            this.f637e = bmVar;
            this.f633a = alertDialog;
            this.f634b = i;
            this.f635c = arrayList;
            this.f636d = i2;
        }

        public void onShow(DialogInterface d) {
            Button[] a = bm.m729b(this.f633a);
            for (int i = 0; i < this.f634b; i++) {
                CharSequence charSequence = (CharSequence) this.f635c.get(i);
                Button button = a[i];
                if (this.f636d == i) {
                    button.setTypeface(null, 1);
                }
                button.setText(charSequence);
                button.setOnClickListener(new C04381(this, i));
            }
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bm.2 */
    class C04402 implements OnCancelListener {
        final /* synthetic */ bm f638a;

        C04402(bm bmVar) {
            this.f638a = bmVar;
        }

        public void onCancel(DialogInterface arg0) {
            this.f638a.f641b.m725a(this.f638a);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bm.a */
    public static class C0441a {
        private final Bundle f639a;

        public C0441a() {
            this.f639a = new Bundle();
        }

        public C0441a m720a(String str) {
            this.f639a.putString("arg:title", str);
            return this;
        }

        public C0441a m722b(String str) {
            this.f639a.putString("arg:message", str);
            return this;
        }

        public C0441a m723c(String str) {
            this.f639a.putString("arg:left", str);
            return this;
        }

        public C0441a m724d(String str) {
            this.f639a.putString("arg:right", str);
            return this;
        }

        public bm m721a(Context context, C0442b c0442b) {
            return new bm(context, this.f639a, c0442b);
        }
    }

    /* renamed from: com.chartboost.sdk.impl.bm.b */
    public static abstract class C0442b {
        public abstract void m726a(bm bmVar, int i);

        public void m725a(bm bmVar) {
        }
    }

    static {
        f640a = new String[]{"arg:left", "arg:center", "arg:right"};
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public bm(android.content.Context r10, android.os.Bundle r11, com.chartboost.sdk.impl.bm.C0442b r12) {
        /*
        r9 = this;
        r1 = 0;
        r8 = 0;
        r9.<init>();
        r9.f641b = r12;
        r0 = "arg:title";
        r2 = r11.getString(r0);
        r0 = "arg:message";
        r3 = r11.getString(r0);
        r0 = "arg:default";
        r4 = -1;
        r5 = r11.getInt(r0, r4);
        r4 = new java.util.ArrayList;
        r4.<init>();
        r0 = r1;
    L_0x0020:
        r6 = 3;
        if (r0 >= r6) goto L_0x0037;
    L_0x0023:
        r6 = f640a;
        r6 = r6[r0];
        r6 = r11.getString(r6);
        r7 = android.text.TextUtils.isEmpty(r6);
        if (r7 != 0) goto L_0x0034;
    L_0x0031:
        r4.add(r6);
    L_0x0034:
        r0 = r0 + 1;
        goto L_0x0020;
    L_0x0037:
        r0 = new android.app.AlertDialog$Builder;
        r0.<init>(r10);
        r0 = r0.setTitle(r2);
        r2 = r0.setMessage(r3);
        r3 = r4.size();
        switch(r3) {
            case 1: goto L_0x0078;
            case 2: goto L_0x006e;
            case 3: goto L_0x0064;
            default: goto L_0x004b;
        };
    L_0x004b:
        r2 = r2.create();
        r0 = new com.chartboost.sdk.impl.bm$1;
        r1 = r9;
        r0.<init>(r1, r2, r3, r4, r5);
        r2.setOnShowListener(r0);
        r0 = new com.chartboost.sdk.impl.bm$2;
        r0.<init>(r9);
        r2.setOnCancelListener(r0);
        r2.show();
        return;
    L_0x0064:
        r0 = 2;
        r0 = r4.get(r0);
        r0 = (java.lang.CharSequence) r0;
        r2.setNegativeButton(r0, r8);
    L_0x006e:
        r0 = 1;
        r0 = r4.get(r0);
        r0 = (java.lang.CharSequence) r0;
        r2.setNeutralButton(r0, r8);
    L_0x0078:
        r0 = r4.get(r1);
        r0 = (java.lang.CharSequence) r0;
        r2.setPositiveButton(r0, r8);
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.bm.<init>(android.content.Context, android.os.Bundle, com.chartboost.sdk.impl.bm$b):void");
    }

    private static final Button[] m729b(AlertDialog alertDialog) {
        ViewGroup viewGroup = (ViewGroup) alertDialog.getButton(-1).getParent();
        int childCount = viewGroup.getChildCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getVisibility() == 0 && (childAt instanceof Button)) {
                arrayList.add((Button) childAt);
            }
        }
        Button[] buttonArr = new Button[arrayList.size()];
        arrayList.toArray(buttonArr);
        return buttonArr;
    }
}
