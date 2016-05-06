package com.inmobi.commons.core.utilities.p004a;

import android.annotation.SuppressLint;
import android.util.Base64;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.HTTP;

/* renamed from: com.inmobi.commons.core.utilities.a.c */
public class UidEncryptionUtils {
    private static final String f1264a;

    static {
        f1264a = UidEncryptionUtils.class.getSimpleName();
    }

    @SuppressLint({"TrulyRandom"})
    public static String m1459a(String str) {
        String str2 = "RSA/ECB/nopadding";
        str2 = "C10F7968CFE2C76AC6F0650C877806D4514DE58FC239592D2385BCE5609A84B2A0FBDAF29B05505EAD1FDFEF3D7209ACBF34B5D0A806DF18147EA9C0337D6B5B";
        str2 = "010001";
        str2 = "RSA";
        if (str == null || StringUtils.EMPTY.equals(str)) {
            return null;
        }
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger("C10F7968CFE2C76AC6F0650C877806D4514DE58FC239592D2385BCE5609A84B2A0FBDAF29B05505EAD1FDFEF3D7209ACBF34B5D0A806DF18147EA9C0337D6B5B", 16), new BigInteger("010001", 16)));
            Cipher instance = Cipher.getInstance("RSA/ECB/nopadding");
            instance.init(1, rSAPublicKey);
            return new String(Base64.encode(UidEncryptionUtils.m1460a(str.getBytes(HTTP.UTF_8), 1, instance), 0));
        } catch (Throwable e) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1264a, "Erorr in uid encryption.", e);
            return null;
        } catch (Throwable e2) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1264a, "Erorr in uid encryption.", e2);
            return null;
        } catch (Throwable e22) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1264a, "Erorr in uid encryption.", e22);
            return null;
        } catch (Throwable e222) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1264a, "Erorr in uid encryption.", e222);
            return null;
        } catch (Throwable e2222) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1264a, "Erorr in uid encryption.", e2222);
            return null;
        } catch (Throwable e22222) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1264a, "Erorr in uid encryption.", e22222);
            return null;
        } catch (Throwable e222222) {
            Logger.m1441a(InternalLogLevel.INTERNAL, f1264a, "Erorr in uid encryption.", e222222);
            return null;
        }
    }

    private static byte[] m1460a(byte[] bArr, int i, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException {
        int length;
        byte[] bArr2;
        int i2;
        byte[] bArr3 = new byte[0];
        byte[] bArr4 = new byte[0];
        if (i == 1) {
            length = bArr.length;
            bArr2 = new byte[64];
            bArr3 = bArr4;
            i2 = 0;
        } else {
            length = bArr.length;
            bArr2 = new byte[64];
            bArr3 = bArr4;
            i2 = 0;
        }
        while (i2 < length) {
            if (i2 > 0 && i2 % 64 == 0) {
                int i3;
                bArr3 = UidEncryptionUtils.m1461a(bArr3, cipher.doFinal(bArr2));
                if (i2 + 64 > length) {
                    i3 = length - i2;
                } else {
                    i3 = 64;
                }
                bArr2 = new byte[i3];
            }
            bArr2[i2 % 64] = bArr[i2];
            i2++;
        }
        return UidEncryptionUtils.m1461a(bArr3, cipher.doFinal(bArr2));
    }

    private static byte[] m1461a(byte[] bArr, byte[] bArr2) {
        Object obj = new byte[(bArr.length + bArr2.length)];
        System.arraycopy(bArr, 0, obj, 0, bArr.length);
        System.arraycopy(bArr2, 0, obj, bArr.length, bArr2.length);
        return obj;
    }
}
