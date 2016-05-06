package com.google.android.gms.internal;

import android.support.v4.media.TransportMediator;
import com.astuetz.pagerslidingtabstrip.C0302R;
import com.google.android.gms.common.ConnectionResult;
import com.startapp.android.publish.model.MetaData;
import java.io.IOException;
import java.util.Arrays;
import mf.org.apache.xerces.impl.dv.xs.DurationDV;
import mf.org.apache.xerces.util.XMLStringBuffer;
import org.apache.commons.lang.StringUtils;
import org.apache.http.conn.params.ConnManagerParams;

public interface zzsz {

    public static final class zza extends zzso<zza> {
        public String[] zzbuI;
        public String[] zzbuJ;
        public int[] zzbuK;
        public long[] zzbuL;

        public zza() {
            zzJC();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_internal_zzsz_zza = (zza) o;
            if (!zzss.equals(this.zzbuI, com_google_android_gms_internal_zzsz_zza.zzbuI) || !zzss.equals(this.zzbuJ, com_google_android_gms_internal_zzsz_zza.zzbuJ) || !zzss.equals(this.zzbuK, com_google_android_gms_internal_zzsz_zza.zzbuK) || !zzss.equals(this.zzbuL, com_google_android_gms_internal_zzsz_zza.zzbuL)) {
                return false;
            }
            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                return com_google_android_gms_internal_zzsz_zza.zzbuj == null || com_google_android_gms_internal_zzsz_zza.zzbuj.isEmpty();
            } else {
                return this.zzbuj.equals(com_google_android_gms_internal_zzsz_zza.zzbuj);
            }
        }

        public int hashCode() {
            int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + zzss.hashCode(this.zzbuI)) * 31) + zzss.hashCode(this.zzbuJ)) * 31) + zzss.hashCode(this.zzbuK)) * 31) + zzss.hashCode(this.zzbuL)) * 31;
            int hashCode2 = (this.zzbuj == null || this.zzbuj.isEmpty()) ? 0 : this.zzbuj.hashCode();
            return hashCode2 + hashCode;
        }

        public /* synthetic */ zzsu mergeFrom(zzsm com_google_android_gms_internal_zzsm) throws IOException {
            return zzS(com_google_android_gms_internal_zzsm);
        }

        public void writeTo(zzsn output) throws IOException {
            int i = 0;
            if (this.zzbuI != null && this.zzbuI.length > 0) {
                for (String str : this.zzbuI) {
                    if (str != null) {
                        output.zzn(1, str);
                    }
                }
            }
            if (this.zzbuJ != null && this.zzbuJ.length > 0) {
                for (String str2 : this.zzbuJ) {
                    if (str2 != null) {
                        output.zzn(2, str2);
                    }
                }
            }
            if (this.zzbuK != null && this.zzbuK.length > 0) {
                for (int zzA : this.zzbuK) {
                    output.zzA(3, zzA);
                }
            }
            if (this.zzbuL != null && this.zzbuL.length > 0) {
                while (i < this.zzbuL.length) {
                    output.zzb(4, this.zzbuL[i]);
                    i++;
                }
            }
            super.writeTo(output);
        }

        public zza zzJC() {
            this.zzbuI = zzsx.zzbuB;
            this.zzbuJ = zzsx.zzbuB;
            this.zzbuK = zzsx.zzbuw;
            this.zzbuL = zzsx.zzbux;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }

        public zza zzS(zzsm com_google_android_gms_internal_zzsm) throws IOException {
            while (true) {
                int zzIX = com_google_android_gms_internal_zzsm.zzIX();
                int zzc;
                Object obj;
                int zzmq;
                Object obj2;
                switch (zzIX) {
                    case DurationDV.DURATION_TYPE /*0*/:
                        break;
                    case MetaData.DEFAULT_MAX_ADS /*10*/:
                        zzc = zzsx.zzc(com_google_android_gms_internal_zzsm, 10);
                        zzIX = this.zzbuI == null ? 0 : this.zzbuI.length;
                        obj = new String[(zzc + zzIX)];
                        if (zzIX != 0) {
                            System.arraycopy(this.zzbuI, 0, obj, 0, zzIX);
                        }
                        while (zzIX < obj.length - 1) {
                            obj[zzIX] = com_google_android_gms_internal_zzsm.readString();
                            com_google_android_gms_internal_zzsm.zzIX();
                            zzIX++;
                        }
                        obj[zzIX] = com_google_android_gms_internal_zzsm.readString();
                        this.zzbuI = obj;
                        continue;
                    case ConnectionResult.SERVICE_UPDATING /*18*/:
                        zzc = zzsx.zzc(com_google_android_gms_internal_zzsm, 18);
                        zzIX = this.zzbuJ == null ? 0 : this.zzbuJ.length;
                        obj = new String[(zzc + zzIX)];
                        if (zzIX != 0) {
                            System.arraycopy(this.zzbuJ, 0, obj, 0, zzIX);
                        }
                        while (zzIX < obj.length - 1) {
                            obj[zzIX] = com_google_android_gms_internal_zzsm.readString();
                            com_google_android_gms_internal_zzsm.zzIX();
                            zzIX++;
                        }
                        obj[zzIX] = com_google_android_gms_internal_zzsm.readString();
                        this.zzbuJ = obj;
                        continue;
                    case Tokens.EXPRTOKEN_OPERATOR_PLUS /*24*/:
                        zzc = zzsx.zzc(com_google_android_gms_internal_zzsm, 24);
                        zzIX = this.zzbuK == null ? 0 : this.zzbuK.length;
                        obj = new int[(zzc + zzIX)];
                        if (zzIX != 0) {
                            System.arraycopy(this.zzbuK, 0, obj, 0, zzIX);
                        }
                        while (zzIX < obj.length - 1) {
                            obj[zzIX] = com_google_android_gms_internal_zzsm.zzJb();
                            com_google_android_gms_internal_zzsm.zzIX();
                            zzIX++;
                        }
                        obj[zzIX] = com_google_android_gms_internal_zzsm.zzJb();
                        this.zzbuK = obj;
                        continue;
                    case Tokens.EXPRTOKEN_OPERATOR_EQUAL /*26*/:
                        zzmq = com_google_android_gms_internal_zzsm.zzmq(com_google_android_gms_internal_zzsm.zzJf());
                        zzc = com_google_android_gms_internal_zzsm.getPosition();
                        zzIX = 0;
                        while (com_google_android_gms_internal_zzsm.zzJk() > 0) {
                            com_google_android_gms_internal_zzsm.zzJb();
                            zzIX++;
                        }
                        com_google_android_gms_internal_zzsm.zzms(zzc);
                        zzc = this.zzbuK == null ? 0 : this.zzbuK.length;
                        obj2 = new int[(zzIX + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzbuK, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzsm.zzJb();
                            zzc++;
                        }
                        this.zzbuK = obj2;
                        com_google_android_gms_internal_zzsm.zzmr(zzmq);
                        continue;
                    case XMLStringBuffer.DEFAULT_SIZE /*32*/:
                        zzc = zzsx.zzc(com_google_android_gms_internal_zzsm, 32);
                        zzIX = this.zzbuL == null ? 0 : this.zzbuL.length;
                        obj = new long[(zzc + zzIX)];
                        if (zzIX != 0) {
                            System.arraycopy(this.zzbuL, 0, obj, 0, zzIX);
                        }
                        while (zzIX < obj.length - 1) {
                            obj[zzIX] = com_google_android_gms_internal_zzsm.zzJa();
                            com_google_android_gms_internal_zzsm.zzIX();
                            zzIX++;
                        }
                        obj[zzIX] = com_google_android_gms_internal_zzsm.zzJa();
                        this.zzbuL = obj;
                        continue;
                    case Tokens.EXPRTOKEN_AXISNAME_ANCESTOR_OR_SELF /*34*/:
                        zzmq = com_google_android_gms_internal_zzsm.zzmq(com_google_android_gms_internal_zzsm.zzJf());
                        zzc = com_google_android_gms_internal_zzsm.getPosition();
                        zzIX = 0;
                        while (com_google_android_gms_internal_zzsm.zzJk() > 0) {
                            com_google_android_gms_internal_zzsm.zzJa();
                            zzIX++;
                        }
                        com_google_android_gms_internal_zzsm.zzms(zzc);
                        zzc = this.zzbuL == null ? 0 : this.zzbuL.length;
                        obj2 = new long[(zzIX + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzbuL, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzsm.zzJa();
                            zzc++;
                        }
                        this.zzbuL = obj2;
                        com_google_android_gms_internal_zzsm.zzmr(zzmq);
                        continue;
                    default:
                        if (!zza(com_google_android_gms_internal_zzsm, zzIX)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzz() {
            int i;
            int i2;
            int i3;
            int i4 = 0;
            int zzz = super.zzz();
            if (this.zzbuI == null || this.zzbuI.length <= 0) {
                i = zzz;
            } else {
                i2 = 0;
                i3 = 0;
                for (String str : this.zzbuI) {
                    if (str != null) {
                        i3++;
                        i2 += zzsn.zzgO(str);
                    }
                }
                i = (zzz + i2) + (i3 * 1);
            }
            if (this.zzbuJ != null && this.zzbuJ.length > 0) {
                i3 = 0;
                zzz = 0;
                for (String str2 : this.zzbuJ) {
                    if (str2 != null) {
                        zzz++;
                        i3 += zzsn.zzgO(str2);
                    }
                }
                i = (i + i3) + (zzz * 1);
            }
            if (this.zzbuK != null && this.zzbuK.length > 0) {
                i3 = 0;
                for (int zzz2 : this.zzbuK) {
                    i3 += zzsn.zzmx(zzz2);
                }
                i = (i + i3) + (this.zzbuK.length * 1);
            }
            if (this.zzbuL == null || this.zzbuL.length <= 0) {
                return i;
            }
            i2 = 0;
            while (i4 < this.zzbuL.length) {
                i2 += zzsn.zzas(this.zzbuL[i4]);
                i4++;
            }
            return (i + i2) + (this.zzbuL.length * 1);
        }
    }

    public static final class zzb extends zzso<zzb> {
        public String version;
        public int zzbuM;
        public String zzbuN;

        public zzb() {
            zzJD();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzb)) {
                return false;
            }
            zzb com_google_android_gms_internal_zzsz_zzb = (zzb) o;
            if (this.zzbuM != com_google_android_gms_internal_zzsz_zzb.zzbuM) {
                return false;
            }
            if (this.zzbuN == null) {
                if (com_google_android_gms_internal_zzsz_zzb.zzbuN != null) {
                    return false;
                }
            } else if (!this.zzbuN.equals(com_google_android_gms_internal_zzsz_zzb.zzbuN)) {
                return false;
            }
            if (this.version == null) {
                if (com_google_android_gms_internal_zzsz_zzb.version != null) {
                    return false;
                }
            } else if (!this.version.equals(com_google_android_gms_internal_zzsz_zzb.version)) {
                return false;
            }
            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                return com_google_android_gms_internal_zzsz_zzb.zzbuj == null || com_google_android_gms_internal_zzsz_zzb.zzbuj.isEmpty();
            } else {
                return this.zzbuj.equals(com_google_android_gms_internal_zzsz_zzb.zzbuj);
            }
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.version == null ? 0 : this.version.hashCode()) + (((this.zzbuN == null ? 0 : this.zzbuN.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.zzbuM) * 31)) * 31)) * 31;
            if (!(this.zzbuj == null || this.zzbuj.isEmpty())) {
                i = this.zzbuj.hashCode();
            }
            return hashCode + i;
        }

        public /* synthetic */ zzsu mergeFrom(zzsm com_google_android_gms_internal_zzsm) throws IOException {
            return zzT(com_google_android_gms_internal_zzsm);
        }

        public void writeTo(zzsn output) throws IOException {
            if (this.zzbuM != 0) {
                output.zzA(1, this.zzbuM);
            }
            if (!this.zzbuN.equals(StringUtils.EMPTY)) {
                output.zzn(2, this.zzbuN);
            }
            if (!this.version.equals(StringUtils.EMPTY)) {
                output.zzn(3, this.version);
            }
            super.writeTo(output);
        }

        public zzb zzJD() {
            this.zzbuM = 0;
            this.zzbuN = StringUtils.EMPTY;
            this.version = StringUtils.EMPTY;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }

        public zzb zzT(zzsm com_google_android_gms_internal_zzsm) throws IOException {
            while (true) {
                int zzIX = com_google_android_gms_internal_zzsm.zzIX();
                switch (zzIX) {
                    case DurationDV.DURATION_TYPE /*0*/:
                        break;
                    case ConnectionResult.INTERNAL_ERROR /*8*/:
                        zzIX = com_google_android_gms_internal_zzsm.zzJb();
                        switch (zzIX) {
                            case DurationDV.DURATION_TYPE /*0*/:
                            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                            case ConnectionResult.SERVICE_DISABLED /*3*/:
                            case ConnectionResult.SIGN_IN_REQUIRED /*4*/:
                            case MetaData.DEFAULT_SMART_REDIRECT_TIMEOUT /*5*/:
                            case ConnectionResult.RESOLUTION_REQUIRED /*6*/:
                            case ConnectionResult.NETWORK_ERROR /*7*/:
                            case ConnectionResult.INTERNAL_ERROR /*8*/:
                            case ConnectionResult.SERVICE_INVALID /*9*/:
                            case MetaData.DEFAULT_MAX_ADS /*10*/:
                            case ConnectionResult.LICENSE_CHECK_FAILED /*11*/:
                            case Tokens.EXPRTOKEN_NODETYPE_COMMENT /*12*/:
                            case ConnectionResult.CANCELED /*13*/:
                            case ConnectionResult.TIMEOUT /*14*/:
                            case ConnectionResult.INTERRUPTED /*15*/:
                            case ConnectionResult.API_UNAVAILABLE /*16*/:
                            case ConnectionResult.SIGN_IN_FAILED /*17*/:
                            case ConnectionResult.SERVICE_UPDATING /*18*/:
                            case ConnectionResult.SERVICE_MISSING_PERMISSION /*19*/:
                            case ConnManagerParams.DEFAULT_MAX_TOTAL_CONNECTIONS /*20*/:
                            case Tokens.EXPRTOKEN_OPERATOR_SLASH /*21*/:
                            case Tokens.EXPRTOKEN_OPERATOR_DOUBLE_SLASH /*22*/:
                            case Tokens.EXPRTOKEN_OPERATOR_UNION /*23*/:
                            case Tokens.EXPRTOKEN_OPERATOR_PLUS /*24*/:
                            case Tokens.EXPRTOKEN_OPERATOR_MINUS /*25*/:
                            case Tokens.EXPRTOKEN_OPERATOR_EQUAL /*26*/:
                                this.zzbuM = zzIX;
                                break;
                            default:
                                continue;
                        }
                    case ConnectionResult.SERVICE_UPDATING /*18*/:
                        this.zzbuN = com_google_android_gms_internal_zzsm.readString();
                        continue;
                    case Tokens.EXPRTOKEN_OPERATOR_EQUAL /*26*/:
                        this.version = com_google_android_gms_internal_zzsm.readString();
                        continue;
                    default:
                        if (!zza(com_google_android_gms_internal_zzsm, zzIX)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzz() {
            int zzz = super.zzz();
            if (this.zzbuM != 0) {
                zzz += zzsn.zzC(1, this.zzbuM);
            }
            if (!this.zzbuN.equals(StringUtils.EMPTY)) {
                zzz += zzsn.zzo(2, this.zzbuN);
            }
            return !this.version.equals(StringUtils.EMPTY) ? zzz + zzsn.zzo(3, this.version) : zzz;
        }
    }

    public static final class zzc extends zzso<zzc> {
        public byte[] zzbuO;
        public byte[][] zzbuP;
        public boolean zzbuQ;

        public zzc() {
            zzJE();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzc)) {
                return false;
            }
            zzc com_google_android_gms_internal_zzsz_zzc = (zzc) o;
            if (!Arrays.equals(this.zzbuO, com_google_android_gms_internal_zzsz_zzc.zzbuO) || !zzss.zza(this.zzbuP, com_google_android_gms_internal_zzsz_zzc.zzbuP) || this.zzbuQ != com_google_android_gms_internal_zzsz_zzc.zzbuQ) {
                return false;
            }
            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                return com_google_android_gms_internal_zzsz_zzc.zzbuj == null || com_google_android_gms_internal_zzsz_zzc.zzbuj.isEmpty();
            } else {
                return this.zzbuj.equals(com_google_android_gms_internal_zzsz_zzc.zzbuj);
            }
        }

        public int hashCode() {
            int hashCode = ((this.zzbuQ ? 1231 : 1237) + ((((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.zzbuO)) * 31) + zzss.zza(this.zzbuP)) * 31)) * 31;
            int hashCode2 = (this.zzbuj == null || this.zzbuj.isEmpty()) ? 0 : this.zzbuj.hashCode();
            return hashCode2 + hashCode;
        }

        public /* synthetic */ zzsu mergeFrom(zzsm com_google_android_gms_internal_zzsm) throws IOException {
            return zzU(com_google_android_gms_internal_zzsm);
        }

        public void writeTo(zzsn output) throws IOException {
            if (!Arrays.equals(this.zzbuO, zzsx.zzbuD)) {
                output.zza(1, this.zzbuO);
            }
            if (this.zzbuP != null && this.zzbuP.length > 0) {
                for (byte[] bArr : this.zzbuP) {
                    if (bArr != null) {
                        output.zza(2, bArr);
                    }
                }
            }
            if (this.zzbuQ) {
                output.zze(3, this.zzbuQ);
            }
            super.writeTo(output);
        }

        public zzc zzJE() {
            this.zzbuO = zzsx.zzbuD;
            this.zzbuP = zzsx.zzbuC;
            this.zzbuQ = false;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }

        public zzc zzU(zzsm com_google_android_gms_internal_zzsm) throws IOException {
            while (true) {
                int zzIX = com_google_android_gms_internal_zzsm.zzIX();
                switch (zzIX) {
                    case DurationDV.DURATION_TYPE /*0*/:
                        break;
                    case MetaData.DEFAULT_MAX_ADS /*10*/:
                        this.zzbuO = com_google_android_gms_internal_zzsm.readBytes();
                        continue;
                    case ConnectionResult.SERVICE_UPDATING /*18*/:
                        int zzc = zzsx.zzc(com_google_android_gms_internal_zzsm, 18);
                        zzIX = this.zzbuP == null ? 0 : this.zzbuP.length;
                        Object obj = new byte[(zzc + zzIX)][];
                        if (zzIX != 0) {
                            System.arraycopy(this.zzbuP, 0, obj, 0, zzIX);
                        }
                        while (zzIX < obj.length - 1) {
                            obj[zzIX] = com_google_android_gms_internal_zzsm.readBytes();
                            com_google_android_gms_internal_zzsm.zzIX();
                            zzIX++;
                        }
                        obj[zzIX] = com_google_android_gms_internal_zzsm.readBytes();
                        this.zzbuP = obj;
                        continue;
                    case Tokens.EXPRTOKEN_OPERATOR_PLUS /*24*/:
                        this.zzbuQ = com_google_android_gms_internal_zzsm.zzJc();
                        continue;
                    default:
                        if (!zza(com_google_android_gms_internal_zzsm, zzIX)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzz() {
            int i = 0;
            int zzz = super.zzz();
            if (!Arrays.equals(this.zzbuO, zzsx.zzbuD)) {
                zzz += zzsn.zzb(1, this.zzbuO);
            }
            if (this.zzbuP != null && this.zzbuP.length > 0) {
                int i2 = 0;
                int i3 = 0;
                while (i < this.zzbuP.length) {
                    byte[] bArr = this.zzbuP[i];
                    if (bArr != null) {
                        i3++;
                        i2 += zzsn.zzG(bArr);
                    }
                    i++;
                }
                zzz = (zzz + i2) + (i3 * 1);
            }
            return this.zzbuQ ? zzz + zzsn.zzf(3, this.zzbuQ) : zzz;
        }
    }

    public static final class zzd extends zzso<zzd> {
        public String tag;
        public long zzbuR;
        public long zzbuS;
        public long zzbuT;
        public int zzbuU;
        public boolean zzbuV;
        public zze[] zzbuW;
        public zzb zzbuX;
        public byte[] zzbuY;
        public byte[] zzbuZ;
        public byte[] zzbva;
        public zza zzbvb;
        public String zzbvc;
        public long zzbvd;
        public zzc zzbve;
        public byte[] zzbvf;
        public int zzbvg;
        public int[] zzbvh;
        public long zzbvi;
        public int zzob;

        public zzd() {
            zzJF();
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zzd)) {
                return false;
            }
            zzd com_google_android_gms_internal_zzsz_zzd = (zzd) o;
            if (this.zzbuR != com_google_android_gms_internal_zzsz_zzd.zzbuR || this.zzbuS != com_google_android_gms_internal_zzsz_zzd.zzbuS || this.zzbuT != com_google_android_gms_internal_zzsz_zzd.zzbuT) {
                return false;
            }
            if (this.tag == null) {
                if (com_google_android_gms_internal_zzsz_zzd.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(com_google_android_gms_internal_zzsz_zzd.tag)) {
                return false;
            }
            if (this.zzbuU != com_google_android_gms_internal_zzsz_zzd.zzbuU || this.zzob != com_google_android_gms_internal_zzsz_zzd.zzob || this.zzbuV != com_google_android_gms_internal_zzsz_zzd.zzbuV || !zzss.equals(this.zzbuW, com_google_android_gms_internal_zzsz_zzd.zzbuW)) {
                return false;
            }
            if (this.zzbuX == null) {
                if (com_google_android_gms_internal_zzsz_zzd.zzbuX != null) {
                    return false;
                }
            } else if (!this.zzbuX.equals(com_google_android_gms_internal_zzsz_zzd.zzbuX)) {
                return false;
            }
            if (!Arrays.equals(this.zzbuY, com_google_android_gms_internal_zzsz_zzd.zzbuY) || !Arrays.equals(this.zzbuZ, com_google_android_gms_internal_zzsz_zzd.zzbuZ) || !Arrays.equals(this.zzbva, com_google_android_gms_internal_zzsz_zzd.zzbva)) {
                return false;
            }
            if (this.zzbvb == null) {
                if (com_google_android_gms_internal_zzsz_zzd.zzbvb != null) {
                    return false;
                }
            } else if (!this.zzbvb.equals(com_google_android_gms_internal_zzsz_zzd.zzbvb)) {
                return false;
            }
            if (this.zzbvc == null) {
                if (com_google_android_gms_internal_zzsz_zzd.zzbvc != null) {
                    return false;
                }
            } else if (!this.zzbvc.equals(com_google_android_gms_internal_zzsz_zzd.zzbvc)) {
                return false;
            }
            if (this.zzbvd != com_google_android_gms_internal_zzsz_zzd.zzbvd) {
                return false;
            }
            if (this.zzbve == null) {
                if (com_google_android_gms_internal_zzsz_zzd.zzbve != null) {
                    return false;
                }
            } else if (!this.zzbve.equals(com_google_android_gms_internal_zzsz_zzd.zzbve)) {
                return false;
            }
            if (!Arrays.equals(this.zzbvf, com_google_android_gms_internal_zzsz_zzd.zzbvf) || this.zzbvg != com_google_android_gms_internal_zzsz_zzd.zzbvg || !zzss.equals(this.zzbvh, com_google_android_gms_internal_zzsz_zzd.zzbvh) || this.zzbvi != com_google_android_gms_internal_zzsz_zzd.zzbvi) {
                return false;
            }
            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                return com_google_android_gms_internal_zzsz_zzd.zzbuj == null || com_google_android_gms_internal_zzsz_zzd.zzbuj.isEmpty();
            } else {
                return this.zzbuj.equals(com_google_android_gms_internal_zzsz_zzd.zzbuj);
            }
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((((((((((this.zzbve == null ? 0 : this.zzbve.hashCode()) + (((((this.zzbvc == null ? 0 : this.zzbvc.hashCode()) + (((this.zzbvb == null ? 0 : this.zzbvb.hashCode()) + (((((((((this.zzbuX == null ? 0 : this.zzbuX.hashCode()) + (((((this.zzbuV ? 1231 : 1237) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.zzbuR ^ (this.zzbuR >>> 32)))) * 31) + ((int) (this.zzbuS ^ (this.zzbuS >>> 32)))) * 31) + ((int) (this.zzbuT ^ (this.zzbuT >>> 32)))) * 31)) * 31) + this.zzbuU) * 31) + this.zzob) * 31)) * 31) + zzss.hashCode(this.zzbuW)) * 31)) * 31) + Arrays.hashCode(this.zzbuY)) * 31) + Arrays.hashCode(this.zzbuZ)) * 31) + Arrays.hashCode(this.zzbva)) * 31)) * 31)) * 31) + ((int) (this.zzbvd ^ (this.zzbvd >>> 32)))) * 31)) * 31) + Arrays.hashCode(this.zzbvf)) * 31) + this.zzbvg) * 31) + zzss.hashCode(this.zzbvh)) * 31) + ((int) (this.zzbvi ^ (this.zzbvi >>> 32)))) * 31;
            if (!(this.zzbuj == null || this.zzbuj.isEmpty())) {
                i = this.zzbuj.hashCode();
            }
            return hashCode + i;
        }

        public /* synthetic */ zzsu mergeFrom(zzsm com_google_android_gms_internal_zzsm) throws IOException {
            return zzV(com_google_android_gms_internal_zzsm);
        }

        public void writeTo(zzsn output) throws IOException {
            int i = 0;
            if (this.zzbuR != 0) {
                output.zzb(1, this.zzbuR);
            }
            if (!this.tag.equals(StringUtils.EMPTY)) {
                output.zzn(2, this.tag);
            }
            if (this.zzbuW != null && this.zzbuW.length > 0) {
                for (zzsu com_google_android_gms_internal_zzsu : this.zzbuW) {
                    if (com_google_android_gms_internal_zzsu != null) {
                        output.zza(3, com_google_android_gms_internal_zzsu);
                    }
                }
            }
            if (!Arrays.equals(this.zzbuY, zzsx.zzbuD)) {
                output.zza(6, this.zzbuY);
            }
            if (this.zzbvb != null) {
                output.zza(7, this.zzbvb);
            }
            if (!Arrays.equals(this.zzbuZ, zzsx.zzbuD)) {
                output.zza(8, this.zzbuZ);
            }
            if (this.zzbuX != null) {
                output.zza(9, this.zzbuX);
            }
            if (this.zzbuV) {
                output.zze(10, this.zzbuV);
            }
            if (this.zzbuU != 0) {
                output.zzA(11, this.zzbuU);
            }
            if (this.zzob != 0) {
                output.zzA(12, this.zzob);
            }
            if (!Arrays.equals(this.zzbva, zzsx.zzbuD)) {
                output.zza(13, this.zzbva);
            }
            if (!this.zzbvc.equals(StringUtils.EMPTY)) {
                output.zzn(14, this.zzbvc);
            }
            if (this.zzbvd != 180000) {
                output.zzc(15, this.zzbvd);
            }
            if (this.zzbve != null) {
                output.zza(16, this.zzbve);
            }
            if (this.zzbuS != 0) {
                output.zzb(17, this.zzbuS);
            }
            if (!Arrays.equals(this.zzbvf, zzsx.zzbuD)) {
                output.zza(18, this.zzbvf);
            }
            if (this.zzbvg != 0) {
                output.zzA(19, this.zzbvg);
            }
            if (this.zzbvh != null && this.zzbvh.length > 0) {
                while (i < this.zzbvh.length) {
                    output.zzA(20, this.zzbvh[i]);
                    i++;
                }
            }
            if (this.zzbuT != 0) {
                output.zzb(21, this.zzbuT);
            }
            if (this.zzbvi != 0) {
                output.zzb(22, this.zzbvi);
            }
            super.writeTo(output);
        }

        public zzd zzJF() {
            this.zzbuR = 0;
            this.zzbuS = 0;
            this.zzbuT = 0;
            this.tag = StringUtils.EMPTY;
            this.zzbuU = 0;
            this.zzob = 0;
            this.zzbuV = false;
            this.zzbuW = zze.zzJG();
            this.zzbuX = null;
            this.zzbuY = zzsx.zzbuD;
            this.zzbuZ = zzsx.zzbuD;
            this.zzbva = zzsx.zzbuD;
            this.zzbvb = null;
            this.zzbvc = StringUtils.EMPTY;
            this.zzbvd = 180000;
            this.zzbve = null;
            this.zzbvf = zzsx.zzbuD;
            this.zzbvg = 0;
            this.zzbvh = zzsx.zzbuw;
            this.zzbvi = 0;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }

        public zzd zzV(zzsm com_google_android_gms_internal_zzsm) throws IOException {
            while (true) {
                int zzIX = com_google_android_gms_internal_zzsm.zzIX();
                int zzc;
                Object obj;
                switch (zzIX) {
                    case DurationDV.DURATION_TYPE /*0*/:
                        break;
                    case ConnectionResult.INTERNAL_ERROR /*8*/:
                        this.zzbuR = com_google_android_gms_internal_zzsm.zzJa();
                        continue;
                    case ConnectionResult.SERVICE_UPDATING /*18*/:
                        this.tag = com_google_android_gms_internal_zzsm.readString();
                        continue;
                    case Tokens.EXPRTOKEN_OPERATOR_EQUAL /*26*/:
                        zzc = zzsx.zzc(com_google_android_gms_internal_zzsm, 26);
                        zzIX = this.zzbuW == null ? 0 : this.zzbuW.length;
                        obj = new zze[(zzc + zzIX)];
                        if (zzIX != 0) {
                            System.arraycopy(this.zzbuW, 0, obj, 0, zzIX);
                        }
                        while (zzIX < obj.length - 1) {
                            obj[zzIX] = new zze();
                            com_google_android_gms_internal_zzsm.zza(obj[zzIX]);
                            com_google_android_gms_internal_zzsm.zzIX();
                            zzIX++;
                        }
                        obj[zzIX] = new zze();
                        com_google_android_gms_internal_zzsm.zza(obj[zzIX]);
                        this.zzbuW = obj;
                        continue;
                    case MetaData.DEFAULT_HTML_3D_PROBABILITY_3D /*50*/:
                        this.zzbuY = com_google_android_gms_internal_zzsm.readBytes();
                        continue;
                    case C0302R.styleable.Theme_toolbarStyle /*58*/:
                        if (this.zzbvb == null) {
                            this.zzbvb = new zza();
                        }
                        com_google_android_gms_internal_zzsm.zza(this.zzbvb);
                        continue;
                    case C0302R.styleable.Theme_textAppearanceSearchResultSubtitle /*66*/:
                        this.zzbuZ = com_google_android_gms_internal_zzsm.readBytes();
                        continue;
                    case C0302R.styleable.Theme_dropDownListViewStyle /*74*/:
                        if (this.zzbuX == null) {
                            this.zzbuX = new zzb();
                        }
                        com_google_android_gms_internal_zzsm.zza(this.zzbuX);
                        continue;
                    case MetaData.DEFAULT_PROBABILITY_3D /*80*/:
                        this.zzbuV = com_google_android_gms_internal_zzsm.zzJc();
                        continue;
                    case C0302R.styleable.Theme_colorButtonNormal /*88*/:
                        this.zzbuU = com_google_android_gms_internal_zzsm.zzJb();
                        continue;
                    case C0302R.styleable.Theme_buttonBarPositiveButtonStyle /*96*/:
                        this.zzob = com_google_android_gms_internal_zzsm.zzJb();
                        continue;
                    case C0302R.styleable.Theme_ratingBarStyle /*106*/:
                        this.zzbva = com_google_android_gms_internal_zzsm.readBytes();
                        continue;
                    case 114:
                        this.zzbvc = com_google_android_gms_internal_zzsm.readString();
                        continue;
                    case 120:
                        this.zzbvd = com_google_android_gms_internal_zzsm.zzJe();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD /*130*/:
                        if (this.zzbve == null) {
                            this.zzbve = new zzc();
                        }
                        com_google_android_gms_internal_zzsm.zza(this.zzbve);
                        continue;
                    case 136:
                        this.zzbuS = com_google_android_gms_internal_zzsm.zzJa();
                        continue;
                    case 146:
                        this.zzbvf = com_google_android_gms_internal_zzsm.readBytes();
                        continue;
                    case 152:
                        zzIX = com_google_android_gms_internal_zzsm.zzJb();
                        switch (zzIX) {
                            case DurationDV.DURATION_TYPE /*0*/:
                            case DurationDV.YEARMONTHDURATION_TYPE /*1*/:
                            case DurationDV.DAYTIMEDURATION_TYPE /*2*/:
                                this.zzbvg = zzIX;
                                break;
                            default:
                                continue;
                        }
                    case 160:
                        zzc = zzsx.zzc(com_google_android_gms_internal_zzsm, 160);
                        zzIX = this.zzbvh == null ? 0 : this.zzbvh.length;
                        obj = new int[(zzc + zzIX)];
                        if (zzIX != 0) {
                            System.arraycopy(this.zzbvh, 0, obj, 0, zzIX);
                        }
                        while (zzIX < obj.length - 1) {
                            obj[zzIX] = com_google_android_gms_internal_zzsm.zzJb();
                            com_google_android_gms_internal_zzsm.zzIX();
                            zzIX++;
                        }
                        obj[zzIX] = com_google_android_gms_internal_zzsm.zzJb();
                        this.zzbvh = obj;
                        continue;
                    case 162:
                        int zzmq = com_google_android_gms_internal_zzsm.zzmq(com_google_android_gms_internal_zzsm.zzJf());
                        zzc = com_google_android_gms_internal_zzsm.getPosition();
                        zzIX = 0;
                        while (com_google_android_gms_internal_zzsm.zzJk() > 0) {
                            com_google_android_gms_internal_zzsm.zzJb();
                            zzIX++;
                        }
                        com_google_android_gms_internal_zzsm.zzms(zzc);
                        zzc = this.zzbvh == null ? 0 : this.zzbvh.length;
                        Object obj2 = new int[(zzIX + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzbvh, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzsm.zzJb();
                            zzc++;
                        }
                        this.zzbvh = obj2;
                        com_google_android_gms_internal_zzsm.zzmr(zzmq);
                        continue;
                    case 168:
                        this.zzbuT = com_google_android_gms_internal_zzsm.zzJa();
                        continue;
                    case 176:
                        this.zzbvi = com_google_android_gms_internal_zzsm.zzJa();
                        continue;
                    default:
                        if (!zza(com_google_android_gms_internal_zzsm, zzIX)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzz() {
            int i;
            int i2 = 0;
            int zzz = super.zzz();
            if (this.zzbuR != 0) {
                zzz += zzsn.zzd(1, this.zzbuR);
            }
            if (!this.tag.equals(StringUtils.EMPTY)) {
                zzz += zzsn.zzo(2, this.tag);
            }
            if (this.zzbuW != null && this.zzbuW.length > 0) {
                i = zzz;
                for (zzsu com_google_android_gms_internal_zzsu : this.zzbuW) {
                    if (com_google_android_gms_internal_zzsu != null) {
                        i += zzsn.zzc(3, com_google_android_gms_internal_zzsu);
                    }
                }
                zzz = i;
            }
            if (!Arrays.equals(this.zzbuY, zzsx.zzbuD)) {
                zzz += zzsn.zzb(6, this.zzbuY);
            }
            if (this.zzbvb != null) {
                zzz += zzsn.zzc(7, this.zzbvb);
            }
            if (!Arrays.equals(this.zzbuZ, zzsx.zzbuD)) {
                zzz += zzsn.zzb(8, this.zzbuZ);
            }
            if (this.zzbuX != null) {
                zzz += zzsn.zzc(9, this.zzbuX);
            }
            if (this.zzbuV) {
                zzz += zzsn.zzf(10, this.zzbuV);
            }
            if (this.zzbuU != 0) {
                zzz += zzsn.zzC(11, this.zzbuU);
            }
            if (this.zzob != 0) {
                zzz += zzsn.zzC(12, this.zzob);
            }
            if (!Arrays.equals(this.zzbva, zzsx.zzbuD)) {
                zzz += zzsn.zzb(13, this.zzbva);
            }
            if (!this.zzbvc.equals(StringUtils.EMPTY)) {
                zzz += zzsn.zzo(14, this.zzbvc);
            }
            if (this.zzbvd != 180000) {
                zzz += zzsn.zze(15, this.zzbvd);
            }
            if (this.zzbve != null) {
                zzz += zzsn.zzc(16, this.zzbve);
            }
            if (this.zzbuS != 0) {
                zzz += zzsn.zzd(17, this.zzbuS);
            }
            if (!Arrays.equals(this.zzbvf, zzsx.zzbuD)) {
                zzz += zzsn.zzb(18, this.zzbvf);
            }
            if (this.zzbvg != 0) {
                zzz += zzsn.zzC(19, this.zzbvg);
            }
            if (this.zzbvh != null && this.zzbvh.length > 0) {
                i = 0;
                while (i2 < this.zzbvh.length) {
                    i += zzsn.zzmx(this.zzbvh[i2]);
                    i2++;
                }
                zzz = (zzz + i) + (this.zzbvh.length * 2);
            }
            if (this.zzbuT != 0) {
                zzz += zzsn.zzd(21, this.zzbuT);
            }
            return this.zzbvi != 0 ? zzz + zzsn.zzd(22, this.zzbvi) : zzz;
        }
    }

    public static final class zze extends zzso<zze> {
        private static volatile zze[] zzbvj;
        public String key;
        public String value;

        public zze() {
            zzJH();
        }

        public static zze[] zzJG() {
            if (zzbvj == null) {
                synchronized (zzss.zzbut) {
                    if (zzbvj == null) {
                        zzbvj = new zze[0];
                    }
                }
            }
            return zzbvj;
        }

        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof zze)) {
                return false;
            }
            zze com_google_android_gms_internal_zzsz_zze = (zze) o;
            if (this.key == null) {
                if (com_google_android_gms_internal_zzsz_zze.key != null) {
                    return false;
                }
            } else if (!this.key.equals(com_google_android_gms_internal_zzsz_zze.key)) {
                return false;
            }
            if (this.value == null) {
                if (com_google_android_gms_internal_zzsz_zze.value != null) {
                    return false;
                }
            } else if (!this.value.equals(com_google_android_gms_internal_zzsz_zze.value)) {
                return false;
            }
            if (this.zzbuj == null || this.zzbuj.isEmpty()) {
                return com_google_android_gms_internal_zzsz_zze.zzbuj == null || com_google_android_gms_internal_zzsz_zze.zzbuj.isEmpty();
            } else {
                return this.zzbuj.equals(com_google_android_gms_internal_zzsz_zze.zzbuj);
            }
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.key == null ? 0 : this.key.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (!(this.zzbuj == null || this.zzbuj.isEmpty())) {
                i = this.zzbuj.hashCode();
            }
            return hashCode + i;
        }

        public /* synthetic */ zzsu mergeFrom(zzsm com_google_android_gms_internal_zzsm) throws IOException {
            return zzW(com_google_android_gms_internal_zzsm);
        }

        public void writeTo(zzsn output) throws IOException {
            if (!this.key.equals(StringUtils.EMPTY)) {
                output.zzn(1, this.key);
            }
            if (!this.value.equals(StringUtils.EMPTY)) {
                output.zzn(2, this.value);
            }
            super.writeTo(output);
        }

        public zze zzJH() {
            this.key = StringUtils.EMPTY;
            this.value = StringUtils.EMPTY;
            this.zzbuj = null;
            this.zzbuu = -1;
            return this;
        }

        public zze zzW(zzsm com_google_android_gms_internal_zzsm) throws IOException {
            while (true) {
                int zzIX = com_google_android_gms_internal_zzsm.zzIX();
                switch (zzIX) {
                    case DurationDV.DURATION_TYPE /*0*/:
                        break;
                    case MetaData.DEFAULT_MAX_ADS /*10*/:
                        this.key = com_google_android_gms_internal_zzsm.readString();
                        continue;
                    case ConnectionResult.SERVICE_UPDATING /*18*/:
                        this.value = com_google_android_gms_internal_zzsm.readString();
                        continue;
                    default:
                        if (!zza(com_google_android_gms_internal_zzsm, zzIX)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzz() {
            int zzz = super.zzz();
            if (!this.key.equals(StringUtils.EMPTY)) {
                zzz += zzsn.zzo(1, this.key);
            }
            return !this.value.equals(StringUtils.EMPTY) ? zzz + zzsn.zzo(2, this.value) : zzz;
        }
    }
}
