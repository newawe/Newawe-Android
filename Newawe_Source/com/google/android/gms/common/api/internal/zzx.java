package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import java.lang.ref.WeakReference;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;

public class zzx<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private final Object zzagI;
    private final WeakReference<GoogleApiClient> zzagK;
    private ResultTransform<? super R, ? extends Result> zzaiN;
    private zzx<? extends Result> zzaiO;
    private ResultCallbacks<? super R> zzaiP;
    private PendingResult<R> zzaiQ;
    private Status zzaiR;
    private final zza zzaiS;

    /* renamed from: com.google.android.gms.common.api.internal.zzx.1 */
    class C05301 implements Runnable {
        final /* synthetic */ Result zzaiT;
        final /* synthetic */ zzx zzaiU;

        C05301(zzx com_google_android_gms_common_api_internal_zzx, Result result) {
            this.zzaiU = com_google_android_gms_common_api_internal_zzx;
            this.zzaiT = result;
        }

        @WorkerThread
        public void run() {
            GoogleApiClient googleApiClient;
            try {
                this.zzaiU.zzaiS.sendMessage(this.zzaiU.zzaiS.obtainMessage(0, this.zzaiU.zzaiN.onSuccess(this.zzaiT)));
                this.zzaiU.zzc(this.zzaiT);
                googleApiClient = (GoogleApiClient) this.zzaiU.zzagK.get();
                if (googleApiClient != null) {
                    googleApiClient.zzb(this.zzaiU);
                }
            } catch (RuntimeException e) {
                this.zzaiU.zzaiS.sendMessage(this.zzaiU.zzaiS.obtainMessage(1, e));
                this.zzaiU.zzc(this.zzaiT);
                googleApiClient = (GoogleApiClient) this.zzaiU.zzagK.get();
                if (googleApiClient != null) {
                    googleApiClient.zzb(this.zzaiU);
                }
            } catch (Throwable th) {
                Throwable th2 = th;
                this.zzaiU.zzc(this.zzaiT);
                googleApiClient = (GoogleApiClient) this.zzaiU.zzagK.get();
                if (googleApiClient != null) {
                    googleApiClient.zzb(this.zzaiU);
                }
            }
        }
    }

    private final class zza extends Handler {
        final /* synthetic */ zzx zzaiU;

        public zza(zzx com_google_android_gms_common_api_internal_zzx, Looper looper) {
            this.zzaiU = com_google_android_gms_common_api_internal_zzx;
            super(looper);
        }

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case DurationDV.DURATION_TYPE /*0*/:
                    PendingResult pendingResult = (PendingResult) msg.obj;
                    synchronized (this.zzaiU.zzagI) {
                        if (pendingResult != null) {
                            if (pendingResult instanceof zzt) {
                                this.zzaiU.zzaiO.zzy(((zzt) pendingResult).getStatus());
                            } else {
                                this.zzaiU.zzaiO.zza(pendingResult);
                            }
                            break;
                        }
                        this.zzaiU.zzaiO.zzy(new Status(13, "Transform returned null"));
                        break;
                    }
                case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                    RuntimeException runtimeException = (RuntimeException) msg.obj;
                    Log.e("TransformedResultImpl", "Runtime exception on the transformation worker thread: " + runtimeException.getMessage());
                    throw runtimeException;
                default:
                    Log.e("TransformedResultImpl", "TransformationResultHandler received unknown message type: " + msg.what);
            }
        }
    }

    public zzx(WeakReference<GoogleApiClient> weakReference) {
        this.zzaiN = null;
        this.zzaiO = null;
        this.zzaiP = null;
        this.zzaiQ = null;
        this.zzagI = new Object();
        this.zzaiR = null;
        com.google.android.gms.common.internal.zzx.zzb((Object) weakReference, (Object) "GoogleApiClient reference must not be null");
        this.zzagK = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) this.zzagK.get();
        this.zzaiS = new zza(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    private void zzc(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                Log.w("TransformedResultImpl", "Unable to release " + result, e);
            }
        }
    }

    private void zzpT() {
        if (this.zzaiN != null || this.zzaiP != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zzagK.get();
            if (!(this.zzaiN == null || googleApiClient == null)) {
                googleApiClient.zza(this);
            }
            if (this.zzaiR != null) {
                zzz(this.zzaiR);
            } else if (this.zzaiQ != null) {
                this.zzaiQ.setResultCallback(this);
            }
        }
    }

    private boolean zzpV() {
        return (this.zzaiP == null || ((GoogleApiClient) this.zzagK.get()) == null) ? false : true;
    }

    private void zzy(Status status) {
        synchronized (this.zzagI) {
            this.zzaiR = status;
            zzz(this.zzaiR);
        }
    }

    private void zzz(Status status) {
        synchronized (this.zzagI) {
            if (this.zzaiN != null) {
                Object onFailure = this.zzaiN.onFailure(status);
                com.google.android.gms.common.internal.zzx.zzb(onFailure, (Object) "onFailure must not return null");
                this.zzaiO.zzy(onFailure);
            } else if (zzpV()) {
                this.zzaiP.onFailure(status);
            }
        }
    }

    public void andFinally(@NonNull ResultCallbacks<? super R> callbacks) {
        boolean z = true;
        synchronized (this.zzagI) {
            com.google.android.gms.common.internal.zzx.zza(this.zzaiP == null, (Object) "Cannot call andFinally() twice.");
            if (this.zzaiN != null) {
                z = false;
            }
            com.google.android.gms.common.internal.zzx.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzaiP = callbacks;
            zzpT();
        }
    }

    public void onResult(R result) {
        synchronized (this.zzagI) {
            if (!result.getStatus().isSuccess()) {
                zzy(result.getStatus());
                zzc((Result) result);
            } else if (this.zzaiN != null) {
                zzs.zzpN().submit(new C05301(this, result));
            } else if (zzpV()) {
                this.zzaiP.onSuccess(result);
            }
        }
    }

    @NonNull
    public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> transform) {
        TransformedResult com_google_android_gms_common_api_internal_zzx;
        boolean z = true;
        synchronized (this.zzagI) {
            com.google.android.gms.common.internal.zzx.zza(this.zzaiN == null, (Object) "Cannot call then() twice.");
            if (this.zzaiP != null) {
                z = false;
            }
            com.google.android.gms.common.internal.zzx.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzaiN = transform;
            com_google_android_gms_common_api_internal_zzx = new zzx(this.zzagK);
            this.zzaiO = com_google_android_gms_common_api_internal_zzx;
            zzpT();
        }
        return com_google_android_gms_common_api_internal_zzx;
    }

    public void zza(PendingResult<?> pendingResult) {
        synchronized (this.zzagI) {
            this.zzaiQ = pendingResult;
            zzpT();
        }
    }

    void zzpU() {
        synchronized (this.zzagI) {
            this.zzaiP = null;
        }
    }
}
