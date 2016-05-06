package com.google.ads.afma.nano;

import com.google.android.gms.internal.zzsm;
import com.google.android.gms.internal.zzsn;
import com.google.android.gms.internal.zzss;
import com.google.android.gms.internal.zzst;
import com.google.android.gms.internal.zzsu;
import com.google.android.gms.internal.zzsx;
import com.startapp.android.publish.model.MetaData;
import java.io.IOException;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import org.apache.commons.lang.StringUtils;

public interface NanoAdshieldEvent {

    public static final class AdShieldEvent extends zzsu {
        private static volatile AdShieldEvent[] zzaK;
        public String appId;

        public AdShieldEvent() {
            clear();
        }

        public static AdShieldEvent[] emptyArray() {
            if (zzaK == null) {
                synchronized (zzss.zzbut) {
                    if (zzaK == null) {
                        zzaK = new AdShieldEvent[0];
                    }
                }
            }
            return zzaK;
        }

        public static AdShieldEvent parseFrom(zzsm input) throws IOException {
            return new AdShieldEvent().mergeFrom(input);
        }

        public static AdShieldEvent parseFrom(byte[] data) throws zzst {
            return (AdShieldEvent) zzsu.mergeFrom(new AdShieldEvent(), data);
        }

        public AdShieldEvent clear() {
            this.appId = StringUtils.EMPTY;
            this.zzbuu = -1;
            return this;
        }

        public AdShieldEvent mergeFrom(zzsm input) throws IOException {
            while (true) {
                int zzIX = input.zzIX();
                switch (zzIX) {
                    case DurationDV.DURATION_TYPE /*0*/:
                        break;
                    case MetaData.DEFAULT_MAX_ADS /*10*/:
                        this.appId = input.readString();
                        continue;
                    default:
                        if (!zzsx.zzb(input, zzIX)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        public void writeTo(zzsn output) throws IOException {
            if (!this.appId.equals(StringUtils.EMPTY)) {
                output.zzn(1, this.appId);
            }
            super.writeTo(output);
        }

        protected int zzz() {
            int zzz = super.zzz();
            return !this.appId.equals(StringUtils.EMPTY) ? zzz + zzsn.zzo(1, this.appId) : zzz;
        }
    }
}
