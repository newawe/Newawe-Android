package com.google.android.youtube.player.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.youtube.player.internal.C0659j.C1215a.C1214a;
import com.google.android.youtube.player.internal.C0660k.C1217a;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

/* renamed from: com.google.android.youtube.player.internal.l */
public interface C0661l extends IInterface {

    /* renamed from: com.google.android.youtube.player.internal.l.a */
    public static abstract class C1219a extends Binder implements C0661l {

        /* renamed from: com.google.android.youtube.player.internal.l.a.a */
        private static class C1218a implements C0661l {
            private IBinder f3682a;

            C1218a(IBinder iBinder) {
                this.f3682a = iBinder;
            }

            public final IBinder m4244a() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.youtube.player.internal.IYouTubeService");
                    this.f3682a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    IBinder readStrongBinder = obtain2.readStrongBinder();
                    return readStrongBinder;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final C0660k m4245a(C0659j c0659j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.youtube.player.internal.IYouTubeService");
                    obtain.writeStrongBinder(c0659j != null ? c0659j.asBinder() : null);
                    this.f3682a.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    C0660k a = C1217a.m4243a(obtain2.readStrongBinder());
                    return a;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final void m4246a(boolean z) throws RemoteException {
                int i = 0;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.youtube.player.internal.IYouTubeService");
                    if (z) {
                        i = 1;
                    }
                    obtain.writeInt(i);
                    this.f3682a.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public final IBinder asBinder() {
                return this.f3682a;
            }
        }

        public static C0661l m4247a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.youtube.player.internal.IYouTubeService");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0661l)) ? new C1218a(iBinder) : (C0661l) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IBinder iBinder = null;
            switch (i) {
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    parcel.enforceInterface("com.google.android.youtube.player.internal.IYouTubeService");
                    IBinder a = m951a();
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(a);
                    return true;
                case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                    C0659j c0659j;
                    parcel.enforceInterface("com.google.android.youtube.player.internal.IYouTubeService");
                    IBinder readStrongBinder = parcel.readStrongBinder();
                    if (readStrongBinder == null) {
                        c0659j = null;
                    } else {
                        IInterface queryLocalInterface = readStrongBinder.queryLocalInterface("com.google.android.youtube.player.internal.IThumbnailLoaderClient");
                        c0659j = (queryLocalInterface == null || !(queryLocalInterface instanceof C0659j)) ? new C1214a(readStrongBinder) : (C0659j) queryLocalInterface;
                    }
                    C0660k a2 = m952a(c0659j);
                    parcel2.writeNoException();
                    if (a2 != null) {
                        iBinder = a2.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case ConnectionResult.SERVICE_DISABLED /*3*/:
                    parcel.enforceInterface("com.google.android.youtube.player.internal.IYouTubeService");
                    m953a(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.youtube.player.internal.IYouTubeService");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IBinder m951a() throws RemoteException;

    C0660k m952a(C0659j c0659j) throws RemoteException;

    void m953a(boolean z) throws RemoteException;
}
