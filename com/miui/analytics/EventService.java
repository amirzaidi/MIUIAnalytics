package com.miui.analytics;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.LogEvent;
import com.miui.analytics.internal.LogEvent.LogType;
import com.miui.analytics.internal.a;
import com.miui.analytics.internal.k;
import com.miui.analytics.internal.service.i;
import com.miui.analytics.internal.util.o;

public class EventService extends IntentService {
    private static final String TAG = "EventService";

    public EventService() {
        super(TAG);
    }

    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            try {
                String stringExtra = intent.getStringExtra(i.d);
                String stringExtra2 = intent.getStringExtra("content");
                String stringExtra3 = intent.getStringExtra("extra");
                CharSequence stringExtra4 = intent.getStringExtra("appid");
                int intExtra = intent.getIntExtra("type", LogType.TYPE_EVENT.a());
                if (!TextUtils.isEmpty(stringExtra)) {
                    String packageName;
                    LogEvent aVar;
                    Context applicationContext = getApplicationContext();
                    if (TextUtils.isEmpty(stringExtra4)) {
                        packageName = applicationContext.getPackageName();
                    } else {
                        CharSequence packageName2 = stringExtra4;
                    }
                    if (intExtra == LogType.TYPE_AD.a()) {
                        aVar = new a(applicationContext, packageName2, stringExtra, stringExtra2);
                    } else {
                        aVar = new LogEvent(applicationContext, packageName2, stringExtra, stringExtra2);
                    }
                    aVar.b(stringExtra3);
                    k.a(applicationContext).a(aVar);
                }
            } catch (Throwable e) {
                Log.e(o.a(TAG), "onHandleIntent e", e);
            }
        }
    }
}
