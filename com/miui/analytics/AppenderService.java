package com.miui.analytics;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import com.miui.analytics.internal.b.e;
import com.miui.analytics.internal.b.k;
import com.miui.analytics.internal.collection.a;
import com.miui.analytics.internal.f;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.o;

public class AppenderService extends Service {
    public static final String ACTION_DOWNLOAD = "miui.intent.action.analytics.ANALYTICS_DOWNLOAD";
    private static final String ACTION_REPORT_HYBRID_STATE = "miui.intent.action.analytics.REPORT_HYBRID_STATE";
    private static final String TAG = "AppenderService";

    public IBinder onBind(Intent intent) {
        return null;
    }

    public int onStartCommand(final Intent intent, int i, int i2) {
        try {
            ab.a(new Runnable() {
                public void run() {
                    try {
                        Object action = intent.getAction();
                        o.a(AppenderService.TAG, "receive a intent, action: " + action);
                        if (TextUtils.equals(AppenderService.ACTION_DOWNLOAD, action)) {
                            a.a(AppenderService.this).a(intent);
                        } else if (TextUtils.equals("android.intent.action.PACKAGE_FIRST_LAUNCH", action)) {
                            a.a(AppenderService.this).b(intent);
                        } else if (action.equals(AppenderService.ACTION_REPORT_HYBRID_STATE)) {
                            Bundle extras = intent.getExtras();
                            String string = extras.getString("p");
                            long j = extras.getLong(f.J);
                            ComponentName componentName = (ComponentName) extras.getParcelable(e.d);
                            o.a(AppenderService.TAG, "-----------\np:" + string + "\nt:" + j + "\nc:" + (componentName == null ? "" : componentName.toString()));
                            k.a(AppenderService.this).a(string, componentName == null ? "" : componentName.getClassName(), j);
                        }
                    } catch (Throwable e) {
                        o.b(AppenderService.TAG, "onStartCommand thread run exception: ", e);
                    }
                }
            });
        } catch (Throwable e) {
            o.b(TAG, "onStartCommand exception: ", e);
        }
        return 0;
    }
}
