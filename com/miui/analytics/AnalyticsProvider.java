package com.miui.analytics;

import android.annotation.TargetApi;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import com.miui.analytics.internal.collection.a;
import com.miui.analytics.internal.collection.b;
import com.miui.analytics.internal.collection.c;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;

public class AnalyticsProvider extends ContentProvider {
    public static final String METHOD_GET_DEVICE_VALIDATION_TOKEN = "getDeviceValidationToken";
    public static final String METHOD_QUERY_LITERAL = "queryLiteral";
    public static final String METHOD_REGISTER_DEVICE = "registerDevice";
    private static final String TAG = "AnalyticsProvider";
    private static final String URI_AUTHORITY = "com.miui.analytics.server.AnalyticsProvider";
    private static final String URI_GET_AAID = "aaid";
    private static final int URI_GET_AAID_CODE = 1;
    private Context mContext;
    private UriMatcher mMatcher;

    public boolean onCreate() {
        try {
            this.mContext = getContext().getApplicationContext();
            this.mMatcher = new UriMatcher(-1);
            this.mMatcher.addURI(URI_AUTHORITY, "aaid", 1);
            if (this.mContext == null) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            Log.e(o.a(TAG), "AnalyticsProvider onCreate exception:", e);
            return false;
        }
    }

    @TargetApi(19)
    public Bundle call(String str, String str2, Bundle bundle) {
        Bundle bundle2 = new Bundle();
        try {
            String callingPackage = getCallingPackage();
            if (METHOD_GET_DEVICE_VALIDATION_TOKEN.equals(str)) {
                return b.a(this.mContext).a(callingPackage);
            }
            if (METHOD_QUERY_LITERAL.equals(str)) {
                return a.a(this.mContext).a(callingPackage, bundle);
            }
            if (METHOD_REGISTER_DEVICE.equals(str)) {
                return c.a().a(getContext());
            }
            return bundle2;
        } catch (Throwable e) {
            Log.e(o.a(TAG), "call exception: ", e);
            bundle2.putString(f.H, "internal error");
            return bundle2;
        }
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    public String getType(Uri uri) {
        if (uri != null) {
            try {
                if (this.mMatcher != null) {
                    switch (this.mMatcher.match(uri)) {
                        case 1:
                            return n.b(getContext());
                    }
                }
            } catch (Throwable e) {
                Log.e(o.a(TAG), "getType e", e);
            }
        }
        return null;
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    public int delete(Uri uri, String str, String[] strArr) {
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }
}
