package com.google.android.youtube.player.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.google.android.youtube.player.internal.e */
public interface C0654e extends IInterface {

    /* renamed from: com.google.android.youtube.player.internal.e.a */
    public static abstract class C1205a extends Binder implements C0654e {

        /* renamed from: com.google.android.youtube.player.internal.e.a.a */
        private static class C1204a implements C0654e {
            private IBinder f3675a;

            C1204a(IBinder iBinder) {
                this.f3675a = iBinder;
            }

            public final void m4218a(boolean z) throws RemoteException {
                int i = 1;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.youtube.player.internal.IOnFullscreenListener");
                    if (!z) {
                        i = 0;
                    }
                    obtain.writeInt(i);
                    this.f3675a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.f3675a;
            }
        }

        public C1205a() {
            attachInterface(this, "com.google.android.youtube.player.internal.IOnFullscreenListener");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    parcel.enforceInterface("com.google.android.youtube.player.internal.IOnFullscreenListener");
                    m927a(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.youtube.player.internal.IOnFullscreenListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m927a(boolean z) throws RemoteException;
}
