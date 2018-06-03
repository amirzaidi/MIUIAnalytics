package com.miui.analytics;

import android.annotation.SuppressLint;
import android.app.job.JobInfo.Builder;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import com.miui.analytics.internal.JobSchedulerService;
import com.miui.analytics.internal.c.c;
import com.miui.analytics.internal.collection.UploadUsageHelper;
import com.miui.analytics.internal.collection.d;
import com.miui.analytics.internal.collection.h;
import com.miui.analytics.internal.collection.j;
import com.miui.analytics.internal.e;
import com.miui.analytics.internal.l;
import com.miui.analytics.internal.m;
import com.miui.analytics.internal.util.ab;
import com.miui.analytics.internal.util.ae;
import com.miui.analytics.internal.util.af;
import com.miui.analytics.internal.util.g;
import com.miui.analytics.internal.util.n;
import com.miui.analytics.internal.util.o;
import com.miui.analytics.internal.util.p;
import com.miui.analytics.internal.util.r;
import com.miui.analytics.internal.util.u;
import com.miui.analytics.internal.util.v;
import miui.external.b;

public class AnalyticsApp extends b {
    private static final String TAG = "AnalyticsApp";
    private BroadcastReceiver mReceiver;

    public void onCreate() {
        try {
            super.onCreate();
            Log.d(o.a(TAG), String.format("onCreate pid:%d, tid:%d", new Object[]{Integer.valueOf(Process.myPid()), Integer.valueOf(Process.myTid())}));
            initAsync();
        } catch (Throwable th) {
            Log.e(o.a(TAG), "analytics app onCreate exception:", th);
        }
    }

    private void initAsync() {
        ab.a(new Runnable() {
            public void run() {
                try {
                    Context applicationContext = AnalyticsApp.this.getApplicationContext();
                    Analytics.initialize(AnalyticsApp.this.getApplicationContext(), e.a(applicationContext), e.b(applicationContext));
                    new com.miui.analytics.internal.b().i(applicationContext);
                    new l().a(applicationContext);
                    AnalyticsApp.this.reportUpgradeStatus(applicationContext);
                    com.miui.analytics.internal.collection.e.a(applicationContext).c();
                    c.a(applicationContext).a("00", "00", true);
                    h.a(applicationContext).b();
                    j.a(applicationContext).a();
                    m.a(applicationContext).a();
                    n.b(applicationContext);
                    if (!n.a(applicationContext, AnalyticsApp.TAG) && af.a()) {
                        p.a(applicationContext);
                    }
                    af.a(applicationContext);
                    AnalyticsApp.this.startJobScheduler();
                    d.a(applicationContext).a();
                    AnalyticsApp.this.registerReceiver();
                    UploadUsageHelper.a(applicationContext).a();
                } catch (Throwable e) {
                    Log.e(o.a(AnalyticsApp.TAG), "initAsync e", e);
                }
            }
        });
    }

    private void reportUpgradeStatus(Context context) {
        try {
            v vVar = new v(context, u.d, u.e);
            int b = vVar.b(u.V, 0);
            String packageName = getPackageName();
            if ("com.miui.analytics".equals(packageName)) {
                int b2 = com.miui.analytics.internal.util.c.b(context, packageName);
                if (b != b2) {
                    vVar.a(u.V, b2);
                    ae.c(context, packageName, String.valueOf(b));
                    return;
                }
                return;
            }
            Log.d(o.a(TAG), "reportUpgradeStatus not support");
        } catch (Throwable e) {
            o.b(TAG, "reportUpgradeStatus exception: ", e);
        }
    }

    @SuppressLint({"NewApi"})
    private void startJobScheduler() {
        try {
            if (VERSION.SDK_INT >= 21) {
                if (Class.forName("android.app.job.JobScheduler") == null) {
                    Log.w(o.a(TAG), "JobScheduler not found, exit startJobScheduler");
                    return;
                }
                JobScheduler jobScheduler = (JobScheduler) getSystemService("jobscheduler");
                jobScheduler.cancel(1);
                Builder builder = new Builder(1, new ComponentName("com.miui.analytics", JobSchedulerService.class.getName()));
                builder.setRequiredNetworkType(1);
                builder.setOverrideDeadline(60000);
                builder.setBackoffCriteria(g.s(), 1);
                Log.i(o.a(TAG), "startJobScheduler goes with code " + jobScheduler.schedule(builder.build()));
            }
        } catch (Throwable th) {
            Log.e(o.a(TAG), "startJobScheduler exception:", th);
        }
    }

    private void registerReceiver() {
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                try {
                    ab.a(new Runnable() {
                        public void run() {
                            try {
                                if (intent != null && !n.a(context, AnalyticsApp.TAG)) {
                                    String action = intent.getAction();
                                    if (!TextUtils.isEmpty(action)) {
                                        o.a(AnalyticsApp.TAG, "action:" + action);
                                        if (action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                                            boolean booleanExtra = intent.getBooleanExtra("noConnectivity", false);
                                            boolean booleanExtra2 = intent.getBooleanExtra("isFailover", false);
                                            if (booleanExtra || booleanExtra2) {
                                                o.a(AnalyticsApp.TAG, "disconnected :" + booleanExtra + " failover :" + booleanExtra2);
                                                return;
                                            }
                                            m.a(context).b();
                                            com.miui.analytics.internal.collection.e.a(context).a();
                                            d.a(context).a();
                                            p.a(context).j();
                                            o.a(AnalyticsApp.TAG, String.format("Receive %s, sticky broadcast %s", new Object[]{action, Boolean.valueOf(AnonymousClass2.this.isInitialStickyBroadcast())}));
                                            c.a(context).a("00", c.m, true);
                                            com.miui.analytics.internal.collection.e.a(context).c();
                                            h.a(context).b();
                                            if (r.a(context)) {
                                                c.a(context).a(c.g, c.p, true);
                                                com.miui.analytics.internal.collection.g.a(context).a();
                                                com.miui.analytics.internal.collection.l.a(context).a();
                                            }
                                        } else if (action.equals("android.intent.action.SCREEN_ON")) {
                                            com.miui.analytics.internal.util.e.a(context).b(true);
                                        } else if (action.equals("android.intent.action.SCREEN_OFF")) {
                                            com.miui.analytics.internal.util.e.a(context).b(false);
                                        }
                                    }
                                }
                            } catch (Throwable e) {
                                Log.e(o.a(AnalyticsApp.TAG), "mReceiver onReceive e", e);
                            }
                        }
                    });
                } catch (Throwable e) {
                    Log.e(o.a(AnalyticsApp.TAG), "mReceiver onReceive e", e);
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.intent.action.SCREEN_ON");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        registerReceiver(this.mReceiver, intentFilter);
    }
}
