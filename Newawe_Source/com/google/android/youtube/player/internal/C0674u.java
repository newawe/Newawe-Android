package com.google.android.youtube.player.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.google.android.youtube.player.internal.u */
public interface C0674u extends IInterface {

    /* renamed from: com.google.android.youtube.player.internal.u.a */
    public static abstract class C1224a extends Binder implements C0674u {

        /* renamed from: com.google.android.youtube.player.internal.u.a.a */
        private static class C1223a implements C0674u {
            private IBinder f3699a;

            C1223a(IBinder iBinder) {
                this.f3699a = iBinder;
            }

            public final IBinder asBinder() {
                return this.f3699a;
            }
        }

        public C1224a() {
            attachInterface(this, "com.google.android.youtube.player.internal.dynamic.IObjectWrapper");
        }

        public static C0674u m4285a(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.youtube.player.internal.dynamic.IObjectWrapper");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof C0674u)) ? new C1223a(iBinder) : (C0674u) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1598968902:
                    parcel2.writeString("com.google.android.youtube.player.internal.dynamic.IObjectWrapper");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }
}
