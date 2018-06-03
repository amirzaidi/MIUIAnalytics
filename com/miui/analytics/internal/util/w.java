package com.miui.analytics.internal.util;

import android.util.Log;
import java.security.Key;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

public class w {
    private static final String a = "RsaUtils";
    private static final String b = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDoI4COg+erTlvGrOnVhcuBOPev\rf9lWITvdGhim/EIkjcV8g6TIgnwNq54ah0NyIKZpVZWg/JAUCmxTS2Wo4YWwefxe\rjA2viipHXwJWH9si+dDLLzdoKfM/fJBG+Sw8mh5qLLggkBL2OqP+Su2q60IytEMa\rCDNzhVfgmrRG9TcupQIDAQAB\r";
    private static final String c = "RSA/ECB/PKCS1Padding";
    private static final String d = "BC";
    private static final String e = "RSA";

    public static byte[] a(byte[] bArr) throws Exception {
        byte[] bArr2 = null;
        try {
            Key b = b(b);
            Cipher instance = Cipher.getInstance(c, d);
            instance.init(1, b);
            bArr2 = instance.doFinal(bArr);
        } catch (Throwable e) {
            Log.e(o.a(a), "RsaUtils encrypt exception:", e);
        }
        return bArr2;
    }

    private static RSAPublicKey b(String str) throws Exception {
        return (RSAPublicKey) KeyFactory.getInstance(e, d).generatePublic(new X509EncodedKeySpec(d.a(str)));
    }

    public static String a(String str) {
        String str2 = "";
        try {
            str2 = m.a(a(str.getBytes()));
        } catch (Throwable e) {
            Log.e(o.a(a), "getEncryptedAesKey exception:", e);
        }
        return str2;
    }
}
