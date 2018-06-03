package com.miui.analytics.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.collection.a;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.o;

public class ApkReceiver extends BroadcastReceiver {
    private static final String a = "ApkReceiver";
    private static final String b = "miui.intent.action.PACKAGE_FIRST_LAUNCH";

    public void onReceive(final Context context, final Intent intent) {
        if (intent != null) {
            ab.a(new Runnable(this) {
                final /* synthetic */ ApkReceiver c;

                public void run() {
                    CharSequence action = intent.getAction();
                    o.a(ApkReceiver.a, "onReceive  " + action);
                    if (this.c.isInitialStickyBroadcast()) {
                        Log.i(o.a(ApkReceiver.a), "sticky broadcast, " + action);
                    }
                    String schemeSpecificPart;
                    if (TextUtils.equals("android.intent.action.PACKAGE_REMOVED", action)) {
                        schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                        o.a(ApkReceiver.a, "package removed " + schemeSpecificPart);
                        a.a(context).b(schemeSpecificPart);
                    } else if (TextUtils.equals("android.intent.action.PACKAGE_ADDED", action)) {
                        schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                        o.a(ApkReceiver.a, "package added " + schemeSpecificPart);
                        a.a(context).a(schemeSpecificPart);
                    } else if (TextUtils.equals("android.intent.action.PACKAGE_REPLACED", action)) {
                        schemeSpecificPart = intent.getData().getSchemeSpecificPart();
                        o.a(ApkReceiver.a, "package replaced " + schemeSpecificPart);
                        a.a(context).c(schemeSpecificPart);
                    } else if (TextUtils.equals(ApkReceiver.b, action)) {
                        a.a(context).b(intent);
                    }
                }
            });
        }
    }
}
