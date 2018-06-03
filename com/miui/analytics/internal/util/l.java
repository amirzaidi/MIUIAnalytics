package com.miui.analytics.internal.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class l {
    private static final String a = "FD";
    private static final String b = "com.xiaomi.finddevice.provider";
    private static final Uri c = Uri.parse("content://com.xiaomi.finddevice.provider");

    public static boolean a(Context context) {
        Throwable e;
        Cursor query;
        try {
            query = context.getContentResolver().query(a("isLastStatusOpen"), null, null, null, null);
            if (query != null) {
                try {
                    query.moveToFirst();
                    boolean z = query.getInt(0) > 0;
                    if (query == null) {
                        return z;
                    }
                    query.close();
                    return z;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        o.b(a, "FindDeviceStatusManagerProvider isLastStatusOpen Exception: ", e);
                        if (query != null) {
                            query.close();
                        }
                        return false;
                    } catch (Throwable th) {
                        e = th;
                        if (query != null) {
                            query.close();
                        }
                        throw e;
                    }
                }
            }
            if (query != null) {
                query.close();
            }
            return false;
        } catch (Exception e3) {
            e = e3;
            query = null;
            o.b(a, "FindDeviceStatusManagerProvider isLastStatusOpen Exception: ", e);
            if (query != null) {
                query.close();
            }
            return false;
        } catch (Throwable th2) {
            e = th2;
            query = null;
            if (query != null) {
                query.close();
            }
            throw e;
        }
    }

    private static Uri a(String str) {
        return Uri.withAppendedPath(c, str);
    }
}
