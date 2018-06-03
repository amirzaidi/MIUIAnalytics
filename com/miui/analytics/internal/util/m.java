package com.miui.analytics.internal.util;

import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

public class m {
    private static final String a = "IOUtils";

    public static byte[] a(InputStream inputStream) throws IOException {
        return a(inputStream, 1024);
    }

    public static byte[] a(InputStream inputStream, int i) throws IOException {
        if (inputStream == null) {
            return null;
        }
        if (i < 1) {
            i = 1;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[i];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.close();
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }

    public static byte[] b(InputStream inputStream) {
        Closeable byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            try {
                int read = inputStream.read(bArr, 0, 8192);
                if (read <= 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            } catch (Throwable e) {
                Log.e(o.a(a), "readInputStream e", e);
                bArr = null;
            } finally {
                a((Closeable) inputStream);
                a(byteArrayOutputStream);
            }
        }
        bArr = byteArrayOutputStream.toByteArray();
        return bArr;
    }

    public static byte[] a(String str) {
        Throwable e;
        Throwable th;
        if (str == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Closeable gZIPOutputStream;
        try {
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(str.getBytes());
                a(gZIPOutputStream);
            } catch (Exception e2) {
                e = e2;
                try {
                    Log.e(o.a(a), "doZip e", e);
                    a(gZIPOutputStream);
                    return byteArrayOutputStream.toByteArray();
                } catch (Throwable th2) {
                    e = th2;
                    a(gZIPOutputStream);
                    throw e;
                }
            }
        } catch (Throwable e3) {
            th = e3;
            gZIPOutputStream = null;
            e = th;
            Log.e(o.a(a), "doZip e", e);
            a(gZIPOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable e32) {
            th = e32;
            gZIPOutputStream = null;
            e = th;
            a(gZIPOutputStream);
            throw e;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b : bArr) {
            String toHexString = Integer.toHexString(b & 255);
            while (toHexString.length() < 2) {
                toHexString = "0" + toHexString;
            }
            stringBuilder.append(toHexString);
        }
        return stringBuilder.toString();
    }

    public static void a(Closeable closeable) {
        if (closeable != null && (closeable instanceof Closeable)) {
            try {
                closeable.close();
            } catch (Throwable e) {
                Log.e(o.a(a), "closeSafely e", e);
            }
        }
    }
}
