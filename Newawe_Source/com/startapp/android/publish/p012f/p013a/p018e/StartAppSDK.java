package com.startapp.android.publish.p012f.p013a.p018e;

import java.io.DataInput;

/* renamed from: com.startapp.android.publish.f.a.e.e */
public class StartAppSDK extends StartAppSDK {
    protected DataInput m4841a(byte[] bArr) {
        DataInput a = super.m2789a(bArr);
        m4839b(a);
        return a;
    }

    protected com.startapp.android.publish.p012f.p013a.p014a.StartAppSDK m4840a(DataInput dataInput) {
        long readInt = (long) dataInput.readInt();
        com.startapp.android.publish.p012f.p013a.p014a.StartAppSDK startAppSDK = new com.startapp.android.publish.p012f.p013a.p014a.StartAppSDK(readInt << 6);
        m2790a(dataInput, startAppSDK, readInt);
        return startAppSDK;
    }

    private void m4839b(DataInput dataInput) {
        try {
            dataInput.readInt();
        } catch (Throwable e) {
            throw new RuntimeException("problem incrementInputStreamForBackwordCompatability", e);
        }
    }
}
