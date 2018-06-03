package com.miui.analytics.internal.util;

import android.util.Base64;
import android.util.Log;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class b {
    private static final String a = "AES";
    private static final String b = "AES/ECB/PKCS5Padding";
    private static final String c = "AES";
    private static KeyGenerator d;
    private static Cipher e;

    static {
        try {
            d = KeyGenerator.getInstance("AES");
            d.init(128);
            e = Cipher.getInstance(b);
        } catch (Throwable e) {
            Log.e(o.a("AES"), "AesUtils e", e);
        }
    }

    private static byte[] a() {
        return d.generateKey().getEncoded();
    }

    private static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = null;
        try {
            e.init(1, new SecretKeySpec(bArr2, "AES"));
            bArr3 = e.doFinal(bArr);
        } catch (Throwable e) {
            Log.e(o.a("AES"), "encrypt exception:", e);
        }
        return bArr3;
    }

    private static byte[] e(String str, String str2) {
        try {
            Key secretKeySpec = new SecretKeySpec(a(str2), "AES");
            Cipher instance = Cipher.getInstance(b);
            byte[] bytes = str.getBytes("utf-8");
            instance.init(1, secretKeySpec);
            return instance.doFinal(bytes);
        } catch (Throwable e) {
            Log.e(o.a("AES"), "encrypt exception:", e);
            return null;
        }
    }

    private static byte[] a(byte[] bArr, String str) {
        try {
            Key secretKeySpec = new SecretKeySpec(a(str), "AES");
            Cipher instance = Cipher.getInstance(b);
            instance.init(2, secretKeySpec);
            return instance.doFinal(bArr);
        } catch (Throwable e) {
            Log.e(o.a("AES"), "decrypt exception:", e);
            return null;
        }
    }

    private static byte[] a(String str) {
        if (str != null) {
            return str.getBytes();
        }
        return null;
    }

    private static byte[] b(String str) {
        if (str == null || str.length() < 1) {
            return null;
        }
        byte[] bArr = new byte[(str.length() / 2)];
        for (int i = 0; i < str.length() / 2; i++) {
            bArr[i] = (byte) ((Integer.parseInt(str.substring(i * 2, (i * 2) + 1), 16) * 16) + Integer.parseInt(str.substring((i * 2) + 1, (i * 2) + 2), 16));
        }
        return bArr;
    }

    public static String a(String str, String str2) {
        return m.a(e(str, str2));
    }

    public static String b(String str, String str2) {
        return Base64.encodeToString(e(str, str2), 10);
    }

    public static String c(String str, String str2) {
        return new String(a(b(str), str2));
    }

    public static String d(String str, String str2) {
        return new String(a(Base64.decode(str, 10), str2));
    }
}
