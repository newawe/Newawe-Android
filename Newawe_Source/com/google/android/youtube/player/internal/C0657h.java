package com.google.android.youtube.player.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.google.android.youtube.player.internal.h */
public interface C0657h extends IInterface {

    /* renamed from: com.google.android.youtube.player.internal.h.a */
    public static abstract class C1211a extends Binder implements C0657h {

        /* renamed from: com.google.android.youtube.player.internal.h.a.a */
        private static class C1210a implements C0657h {
            private IBinder f3678a;

            C1210a(IBinder iBinder) {
                this.f3678a = iBinder;
            }

            public final void m4230a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.youtube.player.internal.IPlaylistEventListener");
                    this.f3678a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.f3678a;
            }

            public final void m4231b() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.youtube.player.internal.IPlaylistEventListener");
                    this.f3678a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m4232c() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.youtube.player.internal.IPlaylistEventListener");
                    this.f3678a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public C1211a() {
            attachInterface(this, "com.google.android.youtube.player.internal.IPlaylistEventListener");
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    parcel.enforceInterface("com.google.android.youtube.player.internal.IPlaylistEventListener");
                    m939a();
                    parcel2.writeNoException();
                    return true;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    parcel.enforceInterface("com.google.android.youtube.player.internal.IPlaylistEventListener");
                    m940b();
                    parcel2.writeNoException();
                    return true;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    parcel.enforceInterface("com.google.android.youtube.player.internal.IPlaylistEventListener");
                    m941c();
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.youtube.player.internal.IPlaylistEventListener");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    void m939a() throws RemoteException;

    void m940b() throws RemoteException;

    void m941c() throws RemoteException;
}
