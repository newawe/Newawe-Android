package com.google.android.youtube.player.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.google.android.youtube.player.internal.c */
public interface C0652c extends IInterface {

    /* renamed from: com.google.android.youtube.player.internal.c.a */
    public static abstract class C1201a extends Binder implements C0652c {

        /* renamed from: com.google.android.youtube.player.internal.c.a.a */
        private static class C1200a implements C0652c {
            private IBinder f3673a;

            C1200a(IBinder iBinder) {
                this.f3673a = iBinder;
            }

            public final void m4173a(String str, IBinder iBinder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.youtube.player.internal.IConnectionCallbacks");
                    obtain.writeString(str);
                    obtain.writeStrongBinder(iBinder);
                    this.f3673a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.f3673a;
            }
        }

        public C1201a() {
            attachInterface(this, "com.google.android.youtube.player.internal.IConnectionCallbacks");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    parcel.enforceInterface("com.google.android.youtube.player.internal.IConnectionCallbacks");
                    m883a(parcel.readString(), parcel.readStrongBinder());
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.youtube.player.internal.IConnectionCallbacks");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m883a(String str, IBinder iBinder) throws RemoteException;
}
