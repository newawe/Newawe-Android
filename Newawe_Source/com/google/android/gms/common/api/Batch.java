package com.google.android.gms.common.api;

import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.internal.zzb;
import java.util.ArrayList;
import java.util.List;

public final class Batch extends zzb<BatchResult> {
    private int zzafZ;
    private boolean zzaga;
    private boolean zzagb;
    private final PendingResult<?>[] zzagc;
    private final Object zzpV;

    public static final class Builder {
        private GoogleApiClient zzaaj;
        private List<PendingResult<?>> zzage;

        public Builder(GoogleApiClient googleApiClient) {
            this.zzage = new ArrayList();
            this.zzaaj = googleApiClient;
        }

        public <R extends Result> BatchResultToken<R> add(PendingResult<R> pendingResult) {
            BatchResultToken<R> batchResultToken = new BatchResultToken(this.zzage.size());
            this.zzage.add(pendingResult);
            return batchResultToken;
        }

        public Batch build() {
            return new Batch(this.zzaaj, null);
        }
    }

    /* renamed from: com.google.android.gms.common.api.Batch.1 */
    class C10951 implements zza {
        final /* synthetic */ Batch zzagd;

        C10951(Batch batch) {
            this.zzagd = batch;
        }

        public void zzu(Status status) {
            synchronized (this.zzagd.zzpV) {
                if (this.zzagd.isCanceled()) {
                    return;
                }
                if (status.isCanceled()) {
                    this.zzagd.zzagb = true;
                } else if (!status.isSuccess()) {
                    this.zzagd.zzaga = true;
                }
                this.zzagd.zzafZ = this.zzagd.zzafZ - 1;
                if (this.zzagd.zzafZ == 0) {
                    if (this.zzagd.zzagb) {
                        super.cancel();
                    } else {
                        this.zzagd.zza(new BatchResult(this.zzagd.zzaga ? new Status(13) : Status.zzagC, this.zzagd.zzagc));
                    }
                }
            }
        }
    }

    private Batch(List<PendingResult<?>> pendingResultList, GoogleApiClient apiClient) {
        super(apiClient);
        this.zzpV = new Object();
        this.zzafZ = pendingResultList.size();
        this.zzagc = new PendingResult[this.zzafZ];
        if (pendingResultList.isEmpty()) {
            zza(new BatchResult(Status.zzagC, this.zzagc));
            return;
        }
        for (int i = 0; i < pendingResultList.size(); i++) {
            PendingResult pendingResult = (PendingResult) pendingResultList.get(i);
            this.zzagc[i] = pendingResult;
            pendingResult.zza(new C10951(this));
        }
    }

    public void cancel() {
        super.cancel();
        for (PendingResult cancel : this.zzagc) {
            cancel.cancel();
        }
    }

    public BatchResult createFailedResult(Status status) {
        return new BatchResult(status, this.zzagc);
    }

    public /* synthetic */ Result zzc(Status status) {
        return createFailedResult(status);
    }
}
